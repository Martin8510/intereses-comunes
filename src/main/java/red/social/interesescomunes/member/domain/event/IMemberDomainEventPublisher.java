package red.social.interesescomunes.member.domain.event;

import red.social.interesescomunes.member.domain.model.Member;

public interface IMemberDomainEventPublisher {
    void publishMemberCreated(Member member);
    void publishMemberUpdated(Member member);
    void publishMemberDeleted(Member member);
}
