package red.social.interesescomunes.auth.application.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import red.social.interesescomunes.auth.application.input.IAuthServicePort;
import red.social.interesescomunes.auth.application.output.IAuthRepositoryAdapter;
import red.social.interesescomunes.auth.domain.event.IAuthUserDomainEventPublisher;
import red.social.interesescomunes.auth.domain.exception.AuthException;
import red.social.interesescomunes.auth.domain.model.AuthUser;
import red.social.interesescomunes.auth.infrastructure.input.api.dto.request.AuthUserRequest;
import red.social.interesescomunes.auth.infrastructure.input.api.dto.response.AuthUserResponse;
import red.social.interesescomunes.user.domain.model.User;
import red.social.interesescomunes.user.infrastructure.input.api.dto.request.UserRequest;
import red.social.interesescomunes.user.infrastructure.input.api.dto.response.UserResponse;

import javax.naming.AuthenticationException;

@Service
public class AuthServiceImpl implements IAuthServicePort {
    private final IAuthRepositoryAdapter authRepositoryAdapter;
    private final IAuthUserDomainEventPublisher eventPublisher;

    public AuthServiceImpl(IAuthRepositoryAdapter authRepositoryAdapter, IAuthUserDomainEventPublisher eventPublisher) {
        this.authRepositoryAdapter = authRepositoryAdapter;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public AuthUserResponse userLogin(AuthUserRequest authUserRequest) throws AuthenticationException {
        System.out.println("service userLogin :" + authUserRequest);
        AuthUserResponse authUserResponse = this.authRepositoryAdapter.authenticateUser(authUserRequest);
        AuthUser authUser = AuthUser.builder()
            .userName(authUserResponse.getUserName())
            .password(authUserResponse.getPassword())
            .token(authUserResponse.getToken())
            .build();

        authUser.singIn(this.eventPublisher);
        return this.authRepositoryAdapter.authenticateUser(authUserRequest);
    }

    @Override
    public AuthUserResponse userLogout(String jwtToken) {
        return null;
    }

    @Override
    public UserRequest encryptUserPassword(UserRequest userRequest) {
        return this.authRepositoryAdapter.setPasswordHash(userRequest);
    }

    @Override
    public AuthUserResponse createRegistrationToken(UserResponse user) {
        String userName = user.getUserName();
        System.out.println("AuthServiceImpl " + userName);

        return this.authRepositoryAdapter.generateRegistryToken(userName);
    }
}
