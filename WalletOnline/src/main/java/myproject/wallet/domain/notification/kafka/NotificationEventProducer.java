package myproject.wallet.domain.notification.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class NotificationEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public NotificationEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotificationCreatedEvent(String event) {
        kafkaTemplate.send("notification-created", event);
    }

    public void sendNotificationUpdatedEvent(String event) {
        kafkaTemplate.send("notification-updated", event);
    }

    public void sendNotificationDeletedEvent(String event) {
        kafkaTemplate.send("notification-deleted", event);
    }
}
