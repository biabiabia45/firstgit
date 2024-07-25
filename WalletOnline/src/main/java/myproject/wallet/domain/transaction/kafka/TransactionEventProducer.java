package myproject.wallet.domain.transaction.kafka;

import lombok.extern.slf4j.Slf4j;
import myproject.wallet.domain.transaction.event.TransactionTreansferedEvent;
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

    public void sendTransactionTransferedEvent(TransactionTreansferedEvent event) {
        String key = event.getTransactionId().toString();
        String value = String.format("Source Wallet: %s, Target Wallet: %s, Amount: %s",
                event.getSourceWalletId(), event.getTargetWalletId(), event.getAmount());

        ProducerRecord<String, String> record = new ProducerRecord<>(transactionTransferTopic, key, value);
        kafkaProducer.send(record);
    }
}
