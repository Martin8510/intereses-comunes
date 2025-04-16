package red.social.interesescomunes.auth.domain.event;

import red.social.interesescomunes.auth.domain.model.AuthUser;

public class AuthUserSignOutEvent {

    private final AuthUser authUser;

    public AuthUserSignOutEvent(AuthUser authUser) {
        this.authUser = authUser;
    }

    public AuthUser getAuthUser() {
        return this.authUser;
    }
}
