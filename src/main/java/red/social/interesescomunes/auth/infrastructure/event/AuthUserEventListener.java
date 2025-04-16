package red.social.interesescomunes.auth.infrastructure.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import red.social.interesescomunes.auth.domain.event.AuthUserSignInEvent;
import red.social.interesescomunes.auth.domain.event.AuthUserSignOutEvent;

@Component
public class AuthUserEventListener {

    @EventListener
    public void handleAuthUserSignIn(AuthUserSignInEvent event){
        System.out.println("Usuario autenticado en el sistema: " + event.getAuthUser().getUserName());

    }

    @EventListener
    public void handleAuthUserSignOut(AuthUserSignOutEvent event){
        System.out.println("Usuario saliendo del sistema: " + event.getAuthUser().getUserName());
    }

}
