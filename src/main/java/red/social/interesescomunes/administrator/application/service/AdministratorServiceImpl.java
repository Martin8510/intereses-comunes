package red.social.interesescomunes.administrator.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import red.social.interesescomunes.administrator.application.input.IAdministratorServicePort;
import red.social.interesescomunes.administrator.application.output.IAdministratorPersistencePort;
import red.social.interesescomunes.administrator.domain.enums.AdministratorStatus;
import red.social.interesescomunes.administrator.domain.event.IAdministratorDomainEventPublisher;
import red.social.interesescomunes.administrator.domain.model.Administrator;
import red.social.interesescomunes.role.application.input.IRoleServicePort;
import red.social.interesescomunes.role.domain.enums.TypeRole;
import red.social.interesescomunes.role.domain.model.Role;
import red.social.interesescomunes.user.application.input.IUserServicePort;
import red.social.interesescomunes.user.domain.exception.UserNotFoundException;
import red.social.interesescomunes.user.domain.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdministratorServiceImpl implements IAdministratorServicePort {
    private final IAdministratorPersistencePort repository;
    private final IAdministratorDomainEventPublisher eventPublisher;
    private final IUserServicePort userService;

    public AdministratorServiceImpl(IAdministratorPersistencePort repository,
                                    IAdministratorDomainEventPublisher eventPublisher,
                                    IUserServicePort userService){
        this.repository = repository;
        this.eventPublisher = eventPublisher;
        this.userService = userService;
    }

    @Override
    public List<Administrator> findAllAdministrators() {
        return this.repository.findAll();
    }

    @Override
    public Administrator findAdministratorById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No se encontro un usuario con el id: " + id));
    }

    @Override
    @Transactional
    public Administrator createAdministrator(Administrator administrator) {
        List<String> rolesName = List.of(TypeRole.ADMINISTRADOR.name());
        User userSave = this.userService.createUser(administrator.getUser(), rolesName);
        administrator.setUser(userSave);
        administrator.setStatus(AdministratorStatus.ACTIVO);
        // Guardamos y publicamos el evento
        Administrator administratorCreated = this.repository.save(administrator);
        administratorCreated.create(this.eventPublisher);
        return administratorCreated;
    }

    @Override
    @Transactional
    public Administrator updateAdministrator(Long id, Administrator administrator) {
        Administrator existinAdmin = this.repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No se encontró el administrador con el id: " + id));
        // 2. Actualizar solo los campos necesarios del usuario
        User existingUser = existinAdmin.getUser();
        User updateData = administrator.getUser();
        this.userService.updateInternalUser(existingUser, updateData );
        //3. Guardamos y publicamos el evento
        Administrator administratorUpdated = this.repository.save(existinAdmin);
        administratorUpdated.update(this.eventPublisher);
        return administratorUpdated;
    }

    @Override
    public void deleteAdministratorById(Long id) {
        Administrator existingAdministrator = this.repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No se encontro un usuario con el id: " + id));

        // Eliminamos y publicamos el evento
        this.repository.delete(existingAdministrator.getId());
        existingAdministrator.delete(this.eventPublisher);
    }
}
