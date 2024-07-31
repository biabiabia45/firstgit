package myproject.wallet.domain.events;

import java.util.UUID;

public class UserCreatedEvent {
    private final UUID userId;

    public UserCreatedEvent(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "UserCreatedEvent{" +
                "userId=" + userId +
                '}';
    }
}
