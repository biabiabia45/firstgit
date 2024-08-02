package myproject.wallet.domain.user.events;

import java.util.UUID;

public class UserUpdatedEvent {
    private final Long userId;

    public UserUpdatedEvent(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "UserUpdatedEvent{" +
                "userId=" + userId +
                '}';
    }
}
