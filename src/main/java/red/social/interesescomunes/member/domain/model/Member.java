package red.social.interesescomunes.member.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.member.domain.enums.MemberStatus;
import red.social.interesescomunes.member.domain.event.IMemberDomainEventPublisher;
import red.social.interesescomunes.user.domain.model.User;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    private Long id;
    private User user;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private MemberStatus status;

    public void create(IMemberDomainEventPublisher eventPublisher){
        eventPublisher.publishMemberCreated(this);
    }

    public void update(IMemberDomainEventPublisher eventPublisher){
        eventPublisher.publishMemberUpdated(this);
    }

    public void delete(IMemberDomainEventPublisher eventPublisher){
        eventPublisher.publishMemberDeleted(this);
    }
}
