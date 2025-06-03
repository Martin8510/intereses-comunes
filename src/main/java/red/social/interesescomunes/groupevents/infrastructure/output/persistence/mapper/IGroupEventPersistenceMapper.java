package red.social.interesescomunes.groupevents.infrastructure.output.persistence.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import red.social.interesescomunes.group.infrastructure.output.persistence.mapper.IGroupPersistenceMapper;
import red.social.interesescomunes.groupevents.domain.model.GroupEvent;
import red.social.interesescomunes.groupevents.infrastructure.output.persistence.mysql.entity.GroupEventEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = {IGroupPersistenceMapper.class})
public interface IGroupEventPersistenceMapper {
    @Mapping(source = "group", target = "group")
    GroupEvent toDomain(GroupEventEntity entity);

    @Mapping(source = "group", target = "group")
    GroupEventEntity toEntity(GroupEvent domain);

    List<GroupEvent> toDomainList(List<GroupEventEntity> entities);
}
