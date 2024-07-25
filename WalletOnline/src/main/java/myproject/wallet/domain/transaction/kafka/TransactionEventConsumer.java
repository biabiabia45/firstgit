package myproject.wallet.domain.transaction.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionEventConsumer {

    @KafkaListener(topics = "transaction-created", groupId = "transaction-group")
    public void listenTransactionCreated(String message) {
        // Handle the transaction created event
        System.out.println("Received Transaction Created Event: " + message);
        // Implement event handling logic
    }

    @KafkaListener(topics = "transaction-updated", groupId = "transaction-group")
    public void listenTransactionUpdated(String message) {
        // Handle the transaction updated event
        System.out.println("Received Transaction Updated Event: " + message);
        // Implement event handling logic
    }

    @KafkaListener(topics = "transaction-deleted", groupId = "transaction-group")
    public void listenTransactionDeleted(String message) {
        // Handle the transaction deleted event
        System.out.println("Received Transaction Deleted Event: " + message);
        // Implement event handling logic
    }
}
