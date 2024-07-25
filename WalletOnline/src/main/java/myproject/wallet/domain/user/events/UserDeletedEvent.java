package myproject.wallet.domain.user.events;

import java.util.UUID;

public class UserDeletedEvent {
    private final UUID userId;

    public UserDeletedEvent(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "UserDeletedEvent{" +
                "userId=" + userId +
                '}';
    }
}
