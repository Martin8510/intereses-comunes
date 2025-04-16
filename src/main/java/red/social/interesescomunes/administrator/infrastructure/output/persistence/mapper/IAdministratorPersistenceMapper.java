package red.social.interesescomunes.administrator.infrastructure.output.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import red.social.interesescomunes.administrator.domain.model.Administrator;
import red.social.interesescomunes.administrator.infrastructure.output.persistence.mysql.entity.AdministratorEntity;
import red.social.interesescomunes.role.infrastructure.output.persistence.mapper.IRolePersistenceMapper;
import red.social.interesescomunes.user.infrastructure.output.persistence.mapper.IUserPersistenceMapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {IUserPersistenceMapper.class, IRolePersistenceMapper.class})
public interface IAdministratorPersistenceMapper {
    @Mapping(source = "user" , target = "user")
    @Mapping(source = "user.roles" , target = "user.roles")
    Administrator toDomain(AdministratorEntity entity);

    @Mapping(source = "user" , target = "user")
    @Mapping(source = "user.roles" , target = "user.roles")
    AdministratorEntity toEntity(Administrator domain);

    List<Administrator> toDomainList(List<AdministratorEntity> entities);
}
