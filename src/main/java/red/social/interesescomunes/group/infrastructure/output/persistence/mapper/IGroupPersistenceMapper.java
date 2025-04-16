package red.social.interesescomunes.group.infrastructure.output.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import red.social.interesescomunes.category.infrastructure.output.persistence.mapper.ICategoryPersistenceMapper;
import red.social.interesescomunes.group.domain.enums.GroupStatus;
import red.social.interesescomunes.group.domain.model.Group;
import red.social.interesescomunes.group.infrastructure.output.persistence.mysql.entity.GroupEntity;
import red.social.interesescomunes.owner.infrastructure.output.persistence.mapper.IOwnerPersistenceMapper;
import red.social.interesescomunes.role.domain.enums.TypeRole;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ICategoryPersistenceMapper.class, IOwnerPersistenceMapper.class})
public interface IGroupPersistenceMapper {
    @Mapping(source = "category", target = "category")
    @Mapping(source = "owner", target = "owner")
    @Mapping(source = "status", target = "status", qualifiedByName = "stringToTypeGroupStatus")
    Group toDomain(GroupEntity entity);

    @Mapping(source = "category", target = "category")
    @Mapping(source = "owner", target = "owner")
    @Mapping(source = "status", target = "status", qualifiedByName = "typeGroupStatusToString")
    GroupEntity toEntity(Group domain);

    List<Group> toDomainList(List<GroupEntity> entities);

    @Named("stringToTypeGroupStatus")
    default GroupStatus stringToTypeGroupStatus(String name) {
        return GroupStatus.valueOf(name.toUpperCase()); // Asegura que coincida con el enum
    }

    //  Metodo para convertir TypeRole a String
    @Named("typeGroupStatusToString")
    default String typeGroupStatusToString(GroupStatus status) {
        return status.name(); // Devuelve el nombre del enum
    }
}
