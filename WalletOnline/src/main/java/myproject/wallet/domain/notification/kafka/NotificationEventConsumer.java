package myproject.wallet.domain.notification.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationEventConsumer {

    @KafkaListener(topics = "notification-created", groupId = "mygroup")
    public void consumeNotificationCreatedEvent(String event) {
        // 处理 NotificationCreatedEvent
    }

    @KafkaListener(topics = "notification-updated", groupId = "mygroup")
    public void consumeNotificationUpdatedEvent(String event) {
        // 处理 NotificationUpdatedEvent
    }

    @KafkaListener(topics = "notification-deleted", groupId = "mygroup")
    public void consumeNotificationDeletedEvent(String event) {
        // 处理 NotificationDeletedEvent
    }
}
