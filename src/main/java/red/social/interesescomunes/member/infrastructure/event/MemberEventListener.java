package red.social.interesescomunes.member.infrastructure.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import red.social.interesescomunes.member.domain.event.MemberCreatedEvent;
import red.social.interesescomunes.member.domain.event.MemberDeletedEvent;
import red.social.interesescomunes.member.domain.event.MemberUpdatedEvent;

@Component
public class MemberEventListener {
    @EventListener
    public void handleMemberCreated(MemberCreatedEvent event) {
        System.out.println("Miembro creado: " + event.getMember().getUser().getFirstName() + " " + event.getMember().getUser().getLastName());
    }

    @EventListener
    public void handleMemberUpdated(MemberUpdatedEvent event) {
        System.out.println("Miembro actualizado: " + event.getMember().getUser().getFirstName() + " " + event.getMember().getUser().getLastName());
    }

    @EventListener
    public void handleMemberDeleted(MemberDeletedEvent event) {
        System.out.println("Miembro eliminado: " + event.getMember().getUser().getFirstName() + " " + event.getMember().getUser().getLastName());
    }
}
