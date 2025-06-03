package red.social.interesescomunes.membergroup.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.group.domain.model.Group;
import red.social.interesescomunes.member.domain.model.Member;
import red.social.interesescomunes.membergroup.domain.enums.MemberGroupStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberGroup {
    private Long id;
    private Member member;
    private Group group;
    private LocalDate joinedAt;
    private LocalDate leftAt;
    private MemberGroupStatus status;
}
