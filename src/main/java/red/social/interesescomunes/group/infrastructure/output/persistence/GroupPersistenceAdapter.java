package red.social.interesescomunes.group.infrastructure.output.persistence;

import org.springframework.stereotype.Component;
import red.social.interesescomunes.group.application.output.IGroupPersistencePort;
import red.social.interesescomunes.group.domain.model.Group;
import red.social.interesescomunes.group.infrastructure.output.persistence.mapper.IGroupPersistenceMapper;
import red.social.interesescomunes.group.infrastructure.output.persistence.mysql.entity.GroupEntity;
import red.social.interesescomunes.group.infrastructure.output.persistence.mysql.repository.IGrouppaRepository;

import java.util.List;
import java.util.Optional;

@Component
public class GroupPersistenceAdapter implements IGroupPersistencePort {
    private final IGrouppaRepository jpaRepository;
    private final IGroupPersistenceMapper mapper;

    public GroupPersistenceAdapter(IGrouppaRepository jpaRepository, IGroupPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Group> findAll() {
        List<GroupEntity> groupEntities = (List<GroupEntity>) this.jpaRepository.findAll();
        return this.mapper.toDomainList(groupEntities);
    }

    @Override
    public Optional<Group> findById(Long id) {
        return this.jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Group save(Group group) {
        GroupEntity groupEntity = this.mapper.toEntity(group);
        GroupEntity savedGroup = this.jpaRepository.save(groupEntity);
        return this.mapper.toDomain(savedGroup);
    }

    @Override
    public void delete(Long id) {
        this.jpaRepository.deleteById(id);
    }
}