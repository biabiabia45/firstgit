package myproject.wallet.infrastructure.common.db.repositories.persistence.messaging;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class WalletEventProducer {

    private final KafkaProducer<String, String> kafkaProducer;
    private final String walletCreatedTopic;
    private final String walletUpdatedTopic;
    private final String walletDeletedTopic;
    private final String walletDepositedTopic;
    private final String walletWithdrawnTopic;

    public WalletEventProducer(
            @Value("${spring.kafka.bootstrap-servers}") String bootstrapServers,
            @Value("${kafka.wallet-created-topic}") String walletCreatedTopic,
            @Value("${kafka.wallet-updated-topic}") String walletUpdatedTopic,
            @Value("${kafka.wallet-deleted-topic}") String walletDeletedTopic,
            @Value("${kafka.wallet-deposited-topic}") String walletDepositedTopic,
            @Value("${kafka.wallet-withdrawn-topic}") String walletWithdrawnTopic) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        this.kafkaProducer = new KafkaProducer<>(properties);
        this.walletCreatedTopic = walletCreatedTopic;
        this.walletUpdatedTopic = walletUpdatedTopic;
        this.walletDeletedTopic = walletDeletedTopic;
        this.walletDepositedTopic = walletDepositedTopic;
        this.walletWithdrawnTopic = walletWithdrawnTopic;
    }

    public void sendWalletCreatedEvent(String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(walletCreatedTopic, "WALLET_CREATED", message);
        kafkaProducer.send(record);
    }

    public void sendWalletUpdatedEvent(String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(walletUpdatedTopic, "WALLET_UPDATED", message);
        kafkaProducer.send(record);
    }

    public void sendWalletDeletedEvent(String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(walletDeletedTopic, "WALLET_DELETED", message);
        kafkaProducer.send(record);
    }

    public void sendWalletDepositedEvent(String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(walletDepositedTopic, "WALLET_DEPOSITED", message);
        kafkaProducer.send(record);
    }

    public void sendWalletWithdrawnEvent(String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(walletWithdrawnTopic, "WALLET_WITHDRAWN", message);
        kafkaProducer.send(record);
    }
}
