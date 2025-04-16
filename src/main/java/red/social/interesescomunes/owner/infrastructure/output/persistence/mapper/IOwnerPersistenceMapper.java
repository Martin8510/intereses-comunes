package red.social.interesescomunes.owner.infrastructure.output.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import red.social.interesescomunes.owner.domain.model.Owner;
import red.social.interesescomunes.owner.infrastructure.output.persistence.mysql.entity.OwnerEntity;
import red.social.interesescomunes.role.infrastructure.output.persistence.mapper.IRolePersistenceMapper;
import red.social.interesescomunes.user.infrastructure.output.persistence.mapper.IUserPersistenceMapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {IUserPersistenceMapper.class, IRolePersistenceMapper.class})
public interface IOwnerPersistenceMapper {
    @Mapping(source = "user" , target = "user")
    @Mapping(source = "user.roles" , target = "user.roles")
    Owner toDomain(OwnerEntity entity);

    @Mapping(source = "user" , target = "user")
    @Mapping(source = "user.roles" , target = "user.roles")
    OwnerEntity toEntity(Owner domain);
    List<Owner> toDomainList(List<OwnerEntity> entities);
}
