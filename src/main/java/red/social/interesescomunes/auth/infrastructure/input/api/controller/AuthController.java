package red.social.interesescomunes.auth.infrastructure.input.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import red.social.interesescomunes.auth.application.input.IAuthServicePort;
import red.social.interesescomunes.auth.infrastructure.input.api.dto.request.AuthUserRequest;
import red.social.interesescomunes.auth.infrastructure.input.api.dto.response.AuthUserResponse;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private IAuthServicePort authService;

    public AuthController(IAuthServicePort authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthUserResponse> userLogin(@RequestBody AuthUserRequest authUserRequest) throws AuthenticationException {
        AuthUserResponse authUserResponse = this.authService.userLogin(authUserRequest);
        return ResponseEntity.status(HttpStatus.OK).body(authUserResponse);
    }

    @PostMapping("/sign-out")
    public ResponseEntity<?> userLogout(){
        return null;
    }

}
