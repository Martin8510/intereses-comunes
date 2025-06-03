package red.social.interesescomunes.membergroup.infrastructure.output.persistence.mysql.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import red.social.interesescomunes.membergroup.infrastructure.output.persistence.mysql.entity.MemberGroupEntity;

import java.util.Optional;

@Repository
public interface IMemberGroupJpaRepository extends CrudRepository<MemberGroupEntity, Long> {
    boolean existsByMemberIdAndGroupId(Long memberId, Long groupId);
    Optional<MemberGroupEntity> findByMemberIdAndGroupId(Long memberId, Long groupId);
}
