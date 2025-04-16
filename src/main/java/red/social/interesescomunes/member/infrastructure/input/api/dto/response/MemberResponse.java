package red.social.interesescomunes.member.infrastructure.input.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.owner.domain.enums.OwnerStatus;
import red.social.interesescomunes.user.infrastructure.input.api.dto.response.UserResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponse {
    private Long id;
    private UserResponse user;
    private OwnerStatus status;
}
