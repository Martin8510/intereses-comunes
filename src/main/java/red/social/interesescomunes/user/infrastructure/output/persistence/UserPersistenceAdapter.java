package red.social.interesescomunes.user.infrastructure.output.persistence;


import org.springframework.stereotype.Component;
import red.social.interesescomunes.user.application.output.IUserPersistencePort;
import red.social.interesescomunes.user.domain.model.User;
import red.social.interesescomunes.user.infrastructure.output.persistence.mapper.IUserPersistenceMapper;
import red.social.interesescomunes.user.infrastructure.output.persistence.mysql.entity.UserEntity;
import red.social.interesescomunes.user.infrastructure.output.persistence.mysql.repository.IUserJpaRepository;

import java.util.List;
import java.util.Optional;

@Component
public class UserPersistenceAdapter implements IUserPersistencePort {
    private final IUserJpaRepository jpaRepository;
    private final IUserPersistenceMapper mapper;

    public UserPersistenceAdapter(IUserJpaRepository jpaRepository, IUserPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<User> findAll() {
        List<UserEntity> userEntities = (List<UserEntity>) this.jpaRepository.findAll();
        return this.mapper.toDomainList(userEntities);
    }

    @Override
    public Optional<User> findById(Long id) {
        return this.jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return this.jpaRepository.findByUserName(userName)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.jpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return this.jpaRepository.existsByUserName(userName);
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = this.mapper.toEntity(user);
        UserEntity savedUser = this.jpaRepository.save(userEntity);
        return this.mapper.toDomain(savedUser);
    }

    @Override
    public void delete(Long id) {
        this.jpaRepository.deleteById(id);
    }
}
