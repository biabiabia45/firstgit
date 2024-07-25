package myproject.wallet.domain.notification.events;

import java.util.UUID;

public class NotificationDeletedEvent {
    private final UUID notificationId;

    public NotificationDeletedEvent(UUID notificationId) {
        this.notificationId = notificationId;
    }

    public UUID getNotificationId() {
        return notificationId;
    }
}
