package red.social.interesescomunes.auth.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import red.social.interesescomunes.auth.domain.model.AuthUser;
import red.social.interesescomunes.auth.infrastructure.input.api.dto.request.AuthUserRequest;
import red.social.interesescomunes.auth.infrastructure.input.api.dto.response.AuthUserResponse;

@Mapper(componentModel = "spring")
public interface IAuthMappert {
    @Mapping(target = "token", ignore = true)
    AuthUser toDomain(AuthUserRequest authUserRequest);

    @Mapping(target = "msg", ignore = true)
    AuthUserResponse toResponse(AuthUser authUser);
}
