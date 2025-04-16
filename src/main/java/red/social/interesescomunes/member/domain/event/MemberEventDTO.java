package red.social.interesescomunes.member.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.member.domain.enums.MemberStatus;
import red.social.interesescomunes.member.domain.model.Member;
import red.social.interesescomunes.owner.domain.enums.OwnerStatus;
import red.social.interesescomunes.user.domain.model.User;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberEventDTO {
    private Long id;
    private User user;
    private MemberStatus status;

    public static MemberEventDTO create(Member member){
        return MemberEventDTO.builder()
                .id(member.getId())
                .user(member.getUser())
                .status(member.getStatus())
                .build();
    }
}

