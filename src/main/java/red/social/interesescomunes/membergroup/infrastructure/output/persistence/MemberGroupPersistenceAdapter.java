package red.social.interesescomunes.membergroup.infrastructure.output.persistence;

import org.springframework.stereotype.Component;
import red.social.interesescomunes.membergroup.application.output.IMemberGroupPersistencePort;
import red.social.interesescomunes.membergroup.domain.model.MemberGroup;
import red.social.interesescomunes.membergroup.infrastructure.output.persistence.mapper.IMemberGroupPersistenceMapper;
import red.social.interesescomunes.membergroup.infrastructure.output.persistence.mysql.repository.IMemberGroupJpaRepository;

import java.util.Optional;

@Component
public class MemberGroupPersistenceAdapter implements IMemberGroupPersistencePort {

    private final IMemberGroupJpaRepository repository;
    private final IMemberGroupPersistenceMapper mapper;

    public MemberGroupPersistenceAdapter(IMemberGroupJpaRepository repository,
                                         IMemberGroupPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public MemberGroup save(MemberGroup memberGroup) {
        return mapper.toDomain(repository.save(mapper.toEntity(memberGroup)));
    }

    @Override
    public boolean existsByMemberAndGroup(Long memberId, Long groupId) {
        return repository.existsByMemberIdAndGroupId(memberId, groupId);
    }

    @Override
    public Optional<MemberGroup> findByMemberAndGroup(Long memberId, Long groupId) {
        return repository.findByMemberIdAndGroupId(memberId, groupId)
                .map(mapper::toDomain);
    }
}
