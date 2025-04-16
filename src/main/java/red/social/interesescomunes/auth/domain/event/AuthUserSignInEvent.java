package red.social.interesescomunes.auth.domain.event;

import red.social.interesescomunes.auth.domain.model.AuthUser;

public class AuthUserSignInEvent {

    private final AuthUser authUser;

    public AuthUserSignInEvent(AuthUser authUser) {
        this.authUser = authUser;
    }

    public AuthUser getAuthUser() {
        return this.authUser;
    }
}
