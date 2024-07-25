package myproject.wallet.domain.user.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public UserEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserCreatedEvent(String event) {
        kafkaTemplate.send("user-created", event);
    }

    public void sendUserUpdatedEvent(String event) {
        kafkaTemplate.send("user-updated", event);
    }

    public void sendUserDeletedEvent(String event) {
        kafkaTemplate.send("user-deleted", event);
    }
}
