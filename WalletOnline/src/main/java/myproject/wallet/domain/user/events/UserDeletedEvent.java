package myproject.wallet.domain.user.events;

public class UserDeletedEvent {
    private final String username;


    public UserDeletedEvent(String username) {
        this.username = username;
    }

    public String getUserName() {
        return username;
    }

    @Override
    public String toString() {
        return "UserDeletedEvent{" +
                "username=" + username +
                '}';
    }
}
