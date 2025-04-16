package red.social.interesescomunes.owner.domain.event;

import red.social.interesescomunes.owner.domain.model.Owner;

public interface IOwnerDomainEventPublisher {
    void publishOwnerCreated(Owner owner);
    void publishOwnerUpdated(Owner owner);
    void publishOwnerDeleted(Owner owner);
}
