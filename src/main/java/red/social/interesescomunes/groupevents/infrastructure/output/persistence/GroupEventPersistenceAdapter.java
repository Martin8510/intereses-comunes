package red.social.interesescomunes.groupevents.infrastructure.output.persistence;

import org.springframework.stereotype.Component;
import red.social.interesescomunes.groupevents.application.output.IGroupEventPersistencePort;
import red.social.interesescomunes.groupevents.domain.model.GroupEvent;
import red.social.interesescomunes.groupevents.infrastructure.output.persistence.mapper.IGroupEventPersistenceMapper;
import red.social.interesescomunes.groupevents.infrastructure.output.persistence.mysql.entity.GroupEventEntity;
import red.social.interesescomunes.groupevents.infrastructure.output.persistence.mysql.repository.IGroupEventJpaRepository;

import java.util.List;
import java.util.Optional;

@Component
public class GroupEventPersistenceAdapter implements IGroupEventPersistencePort {
    private final IGroupEventJpaRepository repository;
    private final IGroupEventPersistenceMapper mapper;

    public GroupEventPersistenceAdapter(IGroupEventJpaRepository repository,
                                        IGroupEventPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<GroupEvent> findAll() {
        List<GroupEventEntity> entities = (List<GroupEventEntity>) repository.findAll();
        return mapper.toDomainList(entities);
    }

    @Override
    public Optional<GroupEvent> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public GroupEvent save(GroupEvent event) {
        GroupEventEntity entity = mapper.toEntity(event);
        GroupEventEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public List<GroupEvent> findByGroupId(Long groupId) {
        List<GroupEventEntity> entities = repository.findByGroupId(groupId);
        return mapper.toDomainList(entities);
    }
}
