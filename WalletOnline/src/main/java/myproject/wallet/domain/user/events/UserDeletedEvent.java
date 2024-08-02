package myproject.wallet.domain.user.events;

import java.util.UUID;

public class UserDeletedEvent {
    private final Long userId;

    public UserDeletedEvent(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "UserDeletedEvent{" +
                "userId=" + userId +
                '}';
    }
}
