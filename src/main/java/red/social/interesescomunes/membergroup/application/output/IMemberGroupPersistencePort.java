package red.social.interesescomunes.membergroup.application.output;

import red.social.interesescomunes.membergroup.domain.model.MemberGroup;

import java.util.Optional;

public interface IMemberGroupPersistencePort {
    MemberGroup save(MemberGroup memberGroup);
    boolean existsByMemberAndGroup(Long memberId, Long groupId);
    Optional<MemberGroup> findByMemberAndGroup(Long memberId, Long groupId);
}