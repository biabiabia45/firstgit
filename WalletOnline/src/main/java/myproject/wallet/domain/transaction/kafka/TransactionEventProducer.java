package myproject.wallet.domain.transaction.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TransactionEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public TransactionEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTransactionCreatedEvent(String message) {
        kafkaTemplate.send("transaction-created-topic", message);
    }

    public void sendTransactionUpdatedEvent(String message) {
        kafkaTemplate.send("transaction-updated-topic", message);
    }

    public void sendTransactionDeletedEvent(String message) {
        kafkaTemplate.send("transaction-deleted-topic", message);
    }
}
