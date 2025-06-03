package red.social.interesescomunes.membergroup.infrastructure.input.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberGroupRequest {
    private Long memberId;
    private Long groupId;
}
