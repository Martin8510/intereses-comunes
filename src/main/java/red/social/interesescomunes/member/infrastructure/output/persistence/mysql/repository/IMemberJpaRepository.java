package red.social.interesescomunes.member.infrastructure.output.persistence.mysql.repository;

import org.springframework.data.repository.CrudRepository;
import red.social.interesescomunes.member.infrastructure.output.persistence.mysql.entity.MemberEntity;

public interface IMemberJpaRepository extends CrudRepository<MemberEntity,Long> {
}
