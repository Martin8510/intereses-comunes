package red.social.interesescomunes.auth.application.output;

import red.social.interesescomunes.auth.domain.model.AuthUser;
import red.social.interesescomunes.auth.infrastructure.input.api.dto.request.AuthUserRequest;
import red.social.interesescomunes.auth.infrastructure.input.api.dto.response.AuthUserResponse;
import red.social.interesescomunes.user.infrastructure.input.api.dto.request.UserRequest;

import javax.naming.AuthenticationException;

public interface IAuthRepositoryAdapter {
   AuthUserResponse authenticateUser(AuthUserRequest authUser) throws AuthenticationException;
   AuthUserResponse closeSession(String token);
   UserRequest setPasswordHash(UserRequest userRequest);
   AuthUserResponse generateRegistryToken(String userName);
}
