package red.social.interesescomunes.membergroup.infrastructure.input.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.group.infrastructure.input.api.dto.response.GroupResponse;
import red.social.interesescomunes.member.infrastructure.input.api.dto.response.MemberResponse;
import red.social.interesescomunes.membergroup.domain.enums.MemberGroupStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberGroupResponse {
    private Long id;
    private MemberResponse member;
    private GroupResponse group;
    private LocalDate joinedAt;
    private LocalDate leftAt;
    private MemberGroupStatus status;
}
