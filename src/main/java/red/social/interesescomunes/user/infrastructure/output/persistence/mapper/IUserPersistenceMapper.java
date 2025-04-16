package red.social.interesescomunes.user.infrastructure.output.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import red.social.interesescomunes.role.infrastructure.output.persistence.mapper.IRolePersistenceMapper;
import red.social.interesescomunes.user.domain.model.User;
import red.social.interesescomunes.user.infrastructure.output.persistence.mysql.entity.UserEntity;

import java.util.List;

@Mapper(componentModel = "spring" , uses = {IRolePersistenceMapper.class, IRolePersistenceMapper.class})
public interface IUserPersistenceMapper {
    @Mapping(source = "roles" , target = "roles")
    User toDomain(UserEntity entity);

    @Mapping(source = "roles" , target = "roles")
    UserEntity toEntity(User domain);

    @Mapping(source = "roles" , target = "roles")
    List<User> toDomainList(List<UserEntity> entities);
}
