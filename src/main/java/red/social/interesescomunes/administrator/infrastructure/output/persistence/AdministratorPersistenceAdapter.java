package red.social.interesescomunes.administrator.infrastructure.output.persistence;

import org.springframework.stereotype.Component;
import red.social.interesescomunes.administrator.application.output.IAdministratorPersistencePort;
import red.social.interesescomunes.administrator.domain.model.Administrator;
import red.social.interesescomunes.administrator.infrastructure.output.persistence.mapper.IAdministratorPersistenceMapper;
import red.social.interesescomunes.administrator.infrastructure.output.persistence.mysql.entity.AdministratorEntity;
import red.social.interesescomunes.administrator.infrastructure.output.persistence.mysql.repository.IAdministratorJpaRepository;

import java.util.List;
import java.util.Optional;

@Component
public class AdministratorPersistenceAdapter implements IAdministratorPersistencePort {
    private final IAdministratorJpaRepository jpaRepository;
    private final IAdministratorPersistenceMapper mapper;

    public AdministratorPersistenceAdapter(IAdministratorJpaRepository jpaRepository, IAdministratorPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Administrator> findAll() {
        List<AdministratorEntity> adminEntities = (List<AdministratorEntity>) this.jpaRepository.findAll();
        return this.mapper.toDomainList(adminEntities);
    }

    @Override
    public Optional<Administrator> findById(Long id) {
        return this.jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Administrator save(Administrator administrator) {
        AdministratorEntity adminEntity = this.mapper.toEntity(administrator);
        AdministratorEntity savedAdmin = this.jpaRepository.save(adminEntity);
        return this.mapper.toDomain(savedAdmin);
    }

    @Override
    public void delete(Long id) {
        this.jpaRepository.deleteById(id);
    }
}
