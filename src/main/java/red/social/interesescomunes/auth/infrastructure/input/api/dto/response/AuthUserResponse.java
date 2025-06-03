package red.social.interesescomunes.auth.infrastructure.input.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUserResponse {
    private String userName;
    private String password;
    private String token;
    private String msg;
    private Long idUser;
    private Long idMember;
    private Long idOwner;
    private Long idAdmin;
}
