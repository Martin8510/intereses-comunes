package red.social.interesescomunes.auth.application.input;

import red.social.interesescomunes.auth.domain.model.AuthUser;
import red.social.interesescomunes.auth.infrastructure.input.api.dto.request.AuthUserRequest;
import red.social.interesescomunes.auth.infrastructure.input.api.dto.response.AuthUserResponse;
import red.social.interesescomunes.user.domain.model.User;
import red.social.interesescomunes.user.infrastructure.input.api.dto.request.UserRequest;
import red.social.interesescomunes.user.infrastructure.input.api.dto.response.UserResponse;

import javax.naming.AuthenticationException;

public interface IAuthServicePort {
    AuthUserResponse userLogin(AuthUserRequest authUserRequest) throws AuthenticationException;
    AuthUserResponse userLogout(String jwtToken);
    UserRequest encryptUserPassword(UserRequest userRequest);
    AuthUserResponse createRegistrationToken(UserResponse user);
}
