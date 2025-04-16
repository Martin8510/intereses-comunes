package red.social.interesescomunes.role.infrastructure.output.persistence;

import org.springframework.stereotype.Component;
import red.social.interesescomunes.role.application.output.IRolePersistencePort;
import red.social.interesescomunes.role.domain.enums.TypeRole;
import red.social.interesescomunes.role.domain.model.Role;
import red.social.interesescomunes.role.infrastructure.output.persistence.mapper.IRolePersistenceMapper;
import red.social.interesescomunes.role.infrastructure.output.persistence.mysql.entity.RoleEntity;
import red.social.interesescomunes.role.infrastructure.output.persistence.mysql.repository.IRoleJpaRepository;

import java.util.List;
import java.util.Optional;


/**
 *
 * Componente de persistencia para realizar operaciones con datos relacionados con la tabla rol.
 */
@Component
public class RolePersistenceAdapter implements IRolePersistencePort {
    private final IRoleJpaRepository jpaRepository;
    private final IRolePersistenceMapper mapper;

    public RolePersistenceAdapter(IRoleJpaRepository repository, IRolePersistenceMapper mapper) {
        this.jpaRepository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Role> findAll() {
        List<RoleEntity> roleEntities = (List<RoleEntity>) this.jpaRepository.findAll();
        return mapper.toDomainList(roleEntities);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return  this.jpaRepository.findById(id)
            .map(mapper::toDomain);
    }

    @Override
    public Optional<Role> findByType(TypeRole name) {
        return this.jpaRepository.findByName(name)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsByName(TypeRole name) {
        return this.jpaRepository.existsByName(name);
    }

    @Override
    public Role save(Role role) {
        RoleEntity roleEntity = mapper.toEntity(role);
        RoleEntity savedRole = this.jpaRepository.save(roleEntity);
        return mapper.toDomain(savedRole);
    }

    @Override
    public void delete(Long id) {
        this.jpaRepository.deleteById(id);
    }
}
