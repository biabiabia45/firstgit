package myproject.wallet.domain.transaction.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransactionEventConsumer {

    @KafkaListener(topics = "${kafka.transaction-transfer-topic}", groupId = "${kafka.transaction-group-id}")
    public void listenTransactionTransfer(String message) {
        log.info("Received Transaction Transfer Event: {}", message);
        // Implement event handling logic here
        // e.g., parse the message and process the transaction
    }

}
