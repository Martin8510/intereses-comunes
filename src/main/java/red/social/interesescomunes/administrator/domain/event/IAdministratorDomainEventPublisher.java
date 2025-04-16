package red.social.interesescomunes.administrator.domain.event;

import red.social.interesescomunes.administrator.domain.model.Administrator;

public interface IAdministratorDomainEventPublisher {
    void publishAdministratorCreated(Administrator administrator);
    void publishAdministratorUpdated(Administrator administrator);
    void publishAdministratorDeleted(Administrator administrator);
}
