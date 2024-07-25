package myproject.wallet.domain.notification.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class NotificationEventConsumer {

    private static final Logger logger = LoggerFactory.getLogger(NotificationEventConsumer.class);

    @KafkaListener(topics = "${kafka.notification-created-topic}", groupId = "${kafka.notification-group-id}")
    public void consumeNotificationCreatedEvent(String event) {
        logger.info("Received NotificationCreatedEvent: {}", event);
        // 处理 NotificationCreatedEvent
    }

    @KafkaListener(topics = "${kafka.notification-updated-topic}", groupId = "${kafka.notification-group-id}")
    public void consumeNotificationUpdatedEvent(String event) {
        logger.info("Received NotificationUpdatedEvent: {}", event);
        // 处理 NotificationUpdatedEvent
    }

    @KafkaListener(topics = "${kafka.notification-deleted-topic}", groupId = "${kafka.notification-group-id}")
    public void consumeNotificationDeletedEvent(String event) {
        logger.info("Received NotificationDeletedEvent: {}", event);
        // 处理 NotificationDeletedEvent
    }
}
