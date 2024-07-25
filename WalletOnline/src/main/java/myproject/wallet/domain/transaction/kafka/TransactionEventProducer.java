package myproject.wallet.domain.transaction.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TransactionEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String transactionCreatedTopic = "transaction-created";
    private final String transactionUpdatedTopic = "transaction-updated";
    private final String transactionDeletedTopic = "transaction-deleted";

    public TransactionEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTransactionCreatedEvent(String event) {
        kafkaTemplate.send(transactionCreatedTopic, event);
    }

    public void sendTransactionUpdatedEvent(String event) {
        kafkaTemplate.send(transactionUpdatedTopic, event);
    }

    public void sendTransactionDeletedEvent(String event) {
        kafkaTemplate.send(transactionDeletedTopic, event);
    }
}
