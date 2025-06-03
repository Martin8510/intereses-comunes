package red.social.interesescomunes.user.infrastructure.input.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import red.social.interesescomunes.role.infrastructure.input.api.mapper.IRoleRestMapper;
import red.social.interesescomunes.user.domain.model.User;
import red.social.interesescomunes.user.infrastructure.input.api.dto.request.UserRequest;
import red.social.interesescomunes.user.infrastructure.input.api.dto.response.UserResponse;

import java.util.List;

@Mapper(componentModel = "spring", uses = {IRoleRestMapper.class})
public interface IUserRestMappert {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "accountNoExpired", ignore = true)
    @Mapping(target = "accountNoLocked", ignore = true)
    @Mapping(target = "credentialNoExpired", ignore = true)

    @Mapping(source = "roles", target = "roles")
    User toDomain(UserRequest request);

    @Mapping(source = "id", target = "id")
    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponseList(List<User> users);
}
