package red.social.interesescomunes.administrator.domain.event;

import red.social.interesescomunes.administrator.domain.model.Administrator;

public class AdministratorDeletedEvent {
    private final AdministratorEventDTO administrator;

    public AdministratorDeletedEvent(Administrator administrator){
        this.administrator = AdministratorEventDTO.create(administrator);
    }

    public AdministratorEventDTO getAdministrator(){
        return this.administrator;
    }
}
