package myproject.wallet.domain.notification.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificationEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public NotificationEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotificationCreatedEvent(String message) {
        kafkaTemplate.send("notification-created", message);
    }

    public void sendNotificationUpdatedEvent(String message) {
        kafkaTemplate.send("notification-updated", message);
    }

    public void sendNotificationDeletedEvent(String message) {
        kafkaTemplate.send("notification-deleted", message);
    }
}
