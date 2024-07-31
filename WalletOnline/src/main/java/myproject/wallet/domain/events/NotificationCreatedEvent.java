package myproject.wallet.domain.events;

import java.util.UUID;

public class NotificationCreatedEvent {
    private final UUID notificationId;

    public NotificationCreatedEvent(UUID notificationId) {
        this.notificationId = notificationId;
    }

    public UUID getNotificationId() {
        return notificationId;
    }
}
