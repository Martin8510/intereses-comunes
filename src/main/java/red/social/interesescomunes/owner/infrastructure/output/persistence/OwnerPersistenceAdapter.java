package red.social.interesescomunes.owner.infrastructure.output.persistence;

import org.springframework.stereotype.Component;
import red.social.interesescomunes.owner.application.output.IOwnerPersistencePort;
import red.social.interesescomunes.owner.domain.model.Owner;
import red.social.interesescomunes.owner.infrastructure.output.persistence.mapper.IOwnerPersistenceMapper;
import red.social.interesescomunes.owner.infrastructure.output.persistence.mysql.entity.OwnerEntity;
import red.social.interesescomunes.owner.infrastructure.output.persistence.mysql.repository.IOwnerJpaRepository;
import java.util.List;
import java.util.Optional;

@Component
public class OwnerPersistenceAdapter implements IOwnerPersistencePort {
    private final IOwnerJpaRepository jpaRepository;
    private final IOwnerPersistenceMapper mapper;

    public OwnerPersistenceAdapter(IOwnerJpaRepository jpaRepository, IOwnerPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Owner> findAll() {
        List<OwnerEntity>  ownerEntities = (List<OwnerEntity>) this.jpaRepository.findAll();
        return this.mapper.toDomainList(ownerEntities);
    }

    @Override
    public Optional<Owner> findById(Long id) {
        return this.jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Owner> findByUserId(Long id) {
        return jpaRepository.findByUserId(id)
                .map(mapper::toDomain);
    }

    @Override
    public Owner save(Owner owner) {
        OwnerEntity ownerEntity = this.mapper.toEntity(owner);
        OwnerEntity savedOwner = this.jpaRepository.save(ownerEntity);
        return this.mapper.toDomain(savedOwner);
    }

    @Override
    public void delete(Long id) {
        this.jpaRepository.deleteById(id);
    }
}
