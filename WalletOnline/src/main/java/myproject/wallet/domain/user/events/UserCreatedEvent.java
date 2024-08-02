package myproject.wallet.domain.user.events;

public class UserCreatedEvent {
    private final Long userId;

    public UserCreatedEvent(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "UserCreatedEvent{" +
                "userId=" + userId +
                '}';
    }
}
