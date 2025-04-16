package red.social.interesescomunes.owner.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import red.social.interesescomunes.owner.domain.enums.OwnerStatus;
import red.social.interesescomunes.owner.domain.event.IOwnerDomainEventPublisher;
import red.social.interesescomunes.owner.domain.event.OwnerCreatedEvent;
import red.social.interesescomunes.owner.domain.event.OwnerDeletedEvent;
import red.social.interesescomunes.owner.domain.event.OwnerUpdatedEvent;
import red.social.interesescomunes.user.domain.model.User;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Owner {
    private Long id;
    private User user;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private OwnerStatus status;

    public void create(IOwnerDomainEventPublisher eventPublisher){
        eventPublisher.publishOwnerCreated(this);
    }

    public void update(IOwnerDomainEventPublisher eventPublisher){
        eventPublisher.publishOwnerUpdated(this);
    }

    public void delete(IOwnerDomainEventPublisher eventPublisher){
        eventPublisher.publishOwnerDeleted(this);
    }
}
