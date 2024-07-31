package myproject.wallet.domain.events;

import java.util.UUID;

public class UserUpdatedEvent {
    private final UUID userId;

    public UserUpdatedEvent(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "UserUpdatedEvent{" +
                "userId=" + userId +
                '}';
    }
}
