package red.social.interesescomunes.member.infrastructure.output.persistence.mysql.repository;

import org.springframework.data.repository.CrudRepository;
import red.social.interesescomunes.member.infrastructure.output.persistence.mysql.entity.MemberEntity;

import java.util.Optional;

public interface IMemberJpaRepository extends CrudRepository<MemberEntity,Long> {
    Optional<MemberEntity> findByUserId(Long userId);
}
