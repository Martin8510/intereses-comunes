package red.social.interesescomunes.groupevents.infrastructure.input.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import red.social.interesescomunes.groupevents.domain.model.GroupEvent;
import red.social.interesescomunes.groupevents.infrastructure.input.api.dto.request.GroupEventRequest;
import red.social.interesescomunes.groupevents.infrastructure.input.api.dto.response.GroupEventResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IGroupEventRestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "group", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    GroupEvent toDomain(GroupEventRequest request);

    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "status", target = "status")
    GroupEventResponse toResponse(GroupEvent event);

    List<GroupEventResponse> toResponseList(List<GroupEvent> events);
}
