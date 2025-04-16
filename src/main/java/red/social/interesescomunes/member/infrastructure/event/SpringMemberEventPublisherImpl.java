package red.social.interesescomunes.member.infrastructure.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import red.social.interesescomunes.member.domain.event.IMemberDomainEventPublisher;
import red.social.interesescomunes.member.domain.event.MemberCreatedEvent;
import red.social.interesescomunes.member.domain.event.MemberDeletedEvent;
import red.social.interesescomunes.member.domain.event.MemberUpdatedEvent;
import red.social.interesescomunes.member.domain.model.Member;

@Component
public class SpringMemberEventPublisherImpl implements IMemberDomainEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public SpringMemberEventPublisherImpl(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void publishMemberCreated(Member member) {
        eventPublisher.publishEvent(new MemberCreatedEvent(member));
    }

    @Override
    public void publishMemberUpdated(Member member) {
        eventPublisher.publishEvent(new MemberUpdatedEvent(member));
    }

    @Override
    public void publishMemberDeleted(Member member) {
        eventPublisher.publishEvent(new MemberDeletedEvent(member));
    }
}
