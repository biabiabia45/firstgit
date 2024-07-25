package myproject.wallet.domain.security.events;

import java.util.UUID;

public class UserAuthenticatedEvent {
    private final UUID userId;

    public UserAuthenticatedEvent(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }
}
