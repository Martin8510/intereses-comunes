package red.social.interesescomunes.member.infrastructure.input.api.dto.request;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.owner.domain.enums.OwnerStatus;
import red.social.interesescomunes.user.infrastructure.input.api.dto.request.UserRequest;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequest {
    @Valid
    private UserRequest user;
    private OwnerStatus status;
}
