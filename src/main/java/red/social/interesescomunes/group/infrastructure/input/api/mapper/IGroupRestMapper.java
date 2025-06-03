package red.social.interesescomunes.group.infrastructure.input.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import red.social.interesescomunes.category.domain.model.Category;
import red.social.interesescomunes.category.infrastructure.input.api.mapper.ICategoryRestMapper;
import red.social.interesescomunes.group.domain.model.Group;
import red.social.interesescomunes.group.infrastructure.input.api.dto.request.CategoryIdRequest;
import red.social.interesescomunes.group.infrastructure.input.api.dto.request.GroupRequest;
import red.social.interesescomunes.group.infrastructure.input.api.dto.response.GroupResponse;
import red.social.interesescomunes.owner.infrastructure.input.api.mapper.IOwnerRestMappert;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ICategoryRestMapper.class, IOwnerRestMappert.class})
public interface IGroupRestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", expression = "java(mapCategory(request.getCategory()))")
    @Mapping(source = "owner", target = "owner")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Group toDomain(GroupRequest request);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "owner", target = "owner")
    GroupResponse toResponse(Group group);

    default Category mapCategory(CategoryIdRequest categoryIdRequest) {
        if (categoryIdRequest == null) {
            return null;
        }
        Category category = new Category();
        category.setId(categoryIdRequest.getId());
        return category;
    }

    List<GroupResponse> toResponseList(List<Group> groups);
}