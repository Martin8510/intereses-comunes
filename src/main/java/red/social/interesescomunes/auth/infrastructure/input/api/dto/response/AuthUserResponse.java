package red.social.interesescomunes.auth.infrastructure.input.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserResponse {
    private String userName;
    private String password;
    private String token;
    private String msg;
}
