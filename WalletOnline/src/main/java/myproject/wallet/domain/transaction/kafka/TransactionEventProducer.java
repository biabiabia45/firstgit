package myproject.wallet.domain.transaction.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@Slf4j
public class TransactionEventProducer {

    private final KafkaProducer<String, String> kafkaProducer;
    private final String transactionTransferTopic;

    public TransactionEventProducer(
            @Value("${spring.kafka.bootstrap-servers}") String bootstrapServers,
            @Value("${kafka.transaction-transfer-topic}") String transactionTransferTopic) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        this.kafkaProducer = new KafkaProducer<>(properties);
        this.transactionTransferTopic = transactionTransferTopic;
    }

    public void sendTransactionEvent(String transactionId, String sourceWalletId, String targetWalletId, String amount) {
        String message = String.format("Transaction ID: %s, Source Wallet ID: %s, Target Wallet ID: %s, Amount: %s",
                transactionId, sourceWalletId, targetWalletId, amount);
        ProducerRecord<String, String> record = new ProducerRecord<>(transactionTransferTopic, message);
        kafkaProducer.send(record, (metadata, exception) -> {
            if (exception != null) {
                log.error("Failed to send message to topic {} with exception: {}", transactionTransferTopic, exception.getMessage());
            } else {
                log.info("Sent message to topic {} with offset {} and partition {}", metadata.topic(), metadata.offset(), metadata.partition());
            }
        });
    }
}
