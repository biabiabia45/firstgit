package myproject.wallet.domain.security.events;

import java.util.UUID;

public class UserAuthorizationEvent {
    private final UUID userId;
    private final String role;

    public UserAuthorizationEvent(UUID userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }
}
