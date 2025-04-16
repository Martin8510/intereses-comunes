package red.social.interesescomunes.auth.infrastructure.output;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import red.social.interesescomunes.auth.application.output.IAuthRepositoryAdapter;
import red.social.interesescomunes.auth.infrastructure.input.api.dto.request.AuthUserRequest;
import red.social.interesescomunes.auth.infrastructure.input.api.dto.response.AuthUserResponse;
import red.social.interesescomunes.security.service.api.IAuthSecurityService;
import red.social.interesescomunes.user.infrastructure.input.api.dto.request.UserRequest;

import javax.naming.AuthenticationException;

@Component
public class AuthRepositoryAdapter implements IAuthRepositoryAdapter {
    private PasswordEncoder passwordEncoder;
    private final IAuthSecurityService userDetailService;

    public AuthRepositoryAdapter(IAuthSecurityService userDetailService, PasswordEncoder passwordEncoder) {
        this.userDetailService = userDetailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthUserResponse authenticateUser(AuthUserRequest authUser) throws AuthenticationException {
        return this.userDetailService.authenticateAndGenerateToken(authUser);
    }

    @Override
    public AuthUserResponse closeSession(String token) {
        return null;
    }

    @Override
    public UserRequest setPasswordHash(UserRequest userRequest) {
        return this.encryptUserPassword(userRequest);
    }

    @Override
    public AuthUserResponse generateRegistryToken(String userName) {
        System.out.println("AuthRepositoryAdapter " + userName);
        return this.userDetailService.generatePostRegistrationToken(userName);
    }

    public UserRequest encryptUserPassword(UserRequest user) {
        String passwordHash =  this.passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordHash);
        return user;
    }
}
