package red.social.interesescomunes.administrator.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.administrator.domain.enums.AdministratorStatus;
import red.social.interesescomunes.administrator.domain.event.IAdministratorDomainEventPublisher;
import red.social.interesescomunes.user.domain.model.User;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Administrator {
    private Long id;
    private User user;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private AdministratorStatus status;

    public void create(IAdministratorDomainEventPublisher eventPublisher){
        eventPublisher.publishAdministratorCreated(this);
    }

    public void update(IAdministratorDomainEventPublisher eventPublisher){
        eventPublisher.publishAdministratorUpdated(this);
    }

    public void delete(IAdministratorDomainEventPublisher eventPublisher){
        eventPublisher.publishAdministratorDeleted(this);
    }

}
