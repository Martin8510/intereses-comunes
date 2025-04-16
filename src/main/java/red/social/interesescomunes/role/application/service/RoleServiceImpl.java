package red.social.interesescomunes.role.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import red.social.interesescomunes.role.application.input.IRoleServicePort;
import red.social.interesescomunes.role.application.output.IRolePersistencePort;
import red.social.interesescomunes.role.domain.enums.TypeRole;
import red.social.interesescomunes.role.domain.event.IRoleDomainEventPublisher;
import red.social.interesescomunes.role.domain.exception.RoleNameAlreadyExistsException;
import red.social.interesescomunes.role.domain.exception.RoleNotFoundException;
import red.social.interesescomunes.role.domain.model.Role;
import red.social.interesescomunes.user.domain.exception.UserNameAlreadyExistsException;

import java.util.Arrays;
import java.util.List;


@Service
/**
 * Servicio de aplicación que implementa los casos de uso relacionados con los roles.
 */
class RoleServiceImpl implements IRoleServicePort {
    private final IRolePersistencePort repository;
    private final IRoleDomainEventPublisher eventPublisher;

    public RoleServiceImpl(IRolePersistencePort repositoy, IRoleDomainEventPublisher eventPublisher) {
        this.repository = repositoy;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public List<Role> findAllRoles() {
        return this.repository.findAll();
    }

    @Override
    public Role findRoleById(Long id) {
        return this.repository.findById(id)
            .orElseThrow(() -> new RoleNotFoundException("No se encontro un rol con el id " + id));
    }

    @Override
    public Role findRoleByType(String name) {
        TypeRole typeRole = validateAndConvertRoleType(name);

        return repository.findByType(typeRole)
            .orElseThrow(() -> new RoleNotFoundException("No se encontró un rol con el tipo " + typeRole.name().toString()));
    }

    @Override
    @Transactional
    public Role createRole(Role role) {
        if(this.repository.existsByName(role.getName())){
            throw new RoleNameAlreadyExistsException("Este tipo rol ya existe :" + role.getName().name());
        }

        Role roleCreated = this.repository.save(role);
        roleCreated.create(this.eventPublisher);
        return roleCreated;
    }

    @Override
    @Transactional
    public Role updateRole(Long id, Role role) {
        Role existingRole = this.repository.findById(id)
            .orElseThrow(() -> new RoleNotFoundException("No se encontro un rol con el id " + id));
        // actualizamos los datos
        existingRole.setName(role.getName());
        existingRole.setPermits(role.getPermits());
        existingRole.setDescription(role.getDescription());
        // guardamos y publica el evento
        Role roleUpdated = this.repository.save(existingRole);
        roleUpdated.update(this.eventPublisher);
        return roleUpdated;
    }

    @Override
    @Transactional
    public void deleteRoleById(Long id) {
        Role existingRole = this.repository.findById(id)
            .orElseThrow(() -> new RoleNotFoundException("No se encontro un rol con el id " + id));
        // eliminamos y publica el evento
        this.repository.delete(existingRole.getId());
        existingRole.delete(this.eventPublisher);
    }

    private TypeRole validateAndConvertRoleType(String name) {
        return Arrays.stream(TypeRole.values())
                .filter(type -> type.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new RoleNotFoundException("Tipo de rol no válido: " + name));
    }
}
