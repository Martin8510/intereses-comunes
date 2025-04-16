package red.social.interesescomunes.administrator.domain.event;

import red.social.interesescomunes.administrator.domain.model.Administrator;

public class AdministratorCreatedEvent {
    private final AdministratorEventDTO administrator;

    public AdministratorCreatedEvent(Administrator administrator){
        this.administrator = AdministratorEventDTO.create(administrator);
    }

    public AdministratorEventDTO getAdministrator(){
        return this.administrator;
    }
}
