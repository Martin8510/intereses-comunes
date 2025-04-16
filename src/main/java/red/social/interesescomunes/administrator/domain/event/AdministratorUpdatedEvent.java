package red.social.interesescomunes.administrator.domain.event;

import red.social.interesescomunes.administrator.domain.model.Administrator;

public class AdministratorUpdatedEvent {
    private final AdministratorEventDTO administrator;

    public AdministratorUpdatedEvent(Administrator administrator){
        this.administrator = AdministratorEventDTO.create(administrator);
    }

    public AdministratorEventDTO getAdministrator(){
        return this.administrator;
    }
}
