package red.social.interesescomunes.owner.domain.event;

import red.social.interesescomunes.owner.domain.model.Owner;

public class OwnerDeletedEvent {
    private final OwnerEventDTO owner;

    public OwnerDeletedEvent(Owner owner){
        this.owner = OwnerEventDTO.create(owner);
    }

    public OwnerEventDTO getOwner(){
        return this.owner;
    }
}
