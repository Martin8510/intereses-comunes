package red.social.interesescomunes.user.infrastructure.output.persistence.mysql.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import red.social.interesescomunes.user.infrastructure.output.persistence.mysql.entity.UserEntity;

import java.util.Optional;

@Repository
public interface IUserJpaRepository  extends CrudRepository<UserEntity,Long> {
    Optional<UserEntity> findByUserName(String userName);
    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);
}