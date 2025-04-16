package red.social.interesescomunes.group.infrastructure.input.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import red.social.interesescomunes.category.infrastructure.input.api.mapper.ICategoryRestMapper;
import red.social.interesescomunes.group.domain.model.Group;
import red.social.interesescomunes.group.infrastructure.input.api.dto.request.GroupRequest;
import red.social.interesescomunes.group.infrastructure.input.api.dto.response.GroupResponse;
import red.social.interesescomunes.owner.infrastructure.input.api.mapper.IOwnerRestMappert;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ICategoryRestMapper.class, IOwnerRestMappert.class})
public interface IGroupRestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "category", target = "category")
    @Mapping(source = "owner", target = "owner")
    Group toDomain(GroupRequest request);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "owner", target = "owner")
    GroupResponse toResponse(Group group);

    List<GroupResponse> toResponseList(List<Group> groups);
}