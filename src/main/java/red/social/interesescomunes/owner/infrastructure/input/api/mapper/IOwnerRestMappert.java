package red.social.interesescomunes.owner.infrastructure.input.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import red.social.interesescomunes.owner.domain.model.Owner;
import red.social.interesescomunes.owner.infrastructure.input.api.dto.request.OwnerRequest;
import red.social.interesescomunes.owner.infrastructure.input.api.dto.response.OwnerResponse;
import red.social.interesescomunes.role.infrastructure.input.api.mapper.IRoleRestMapper;
import red.social.interesescomunes.user.infrastructure.input.api.mapper.IUserRestMappert;


import java.util.List;

@Mapper(componentModel = "spring", uses = {IUserRestMappert.class, IRoleRestMapper.class})
public interface IOwnerRestMappert {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startDate", ignore = true)
    @Mapping(target = "endDate", ignore = true)
    @Mapping(source = "user", target = "user")
    Owner toDomain(OwnerRequest request);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "user.roles" , target = "user.roles")
    OwnerResponse toUserResponse(Owner owner);

    List<OwnerResponse> toUserResponseList(List<Owner> owners);
}
