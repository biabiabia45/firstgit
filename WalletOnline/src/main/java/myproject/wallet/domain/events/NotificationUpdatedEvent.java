package myproject.wallet.domain.events;

import java.util.UUID;

public class NotificationUpdatedEvent {
    private final UUID notificationId;

    public NotificationUpdatedEvent(UUID notificationId) {
        this.notificationId = notificationId;
    }

    public UUID getNotificationId() {
        return notificationId;
    }
}
