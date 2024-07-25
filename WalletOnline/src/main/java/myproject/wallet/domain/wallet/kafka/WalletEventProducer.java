package myproject.wallet.domain.wallet.kafka;

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
    private final String walletTopic;

    public WalletEventProducer(
            @Value("${kafka.bootstrap-servers}") String bootstrapServers,
            @Value("${kafka.wallet-topic}") String walletTopic) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        this.kafkaProducer = new KafkaProducer<>(properties);
        this.walletTopic = walletTopic;
    }

    public void sendWalletCreatedEvent(String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(walletTopic, "WALLET_CREATED", message);
        kafkaProducer.send(record);
    }

    public void sendWalletUpdatedEvent(String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(walletTopic, "WALLET_UPDATED", message);
        kafkaProducer.send(record);
    }

    public void sendWalletDeletedEvent(String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(walletTopic, "WALLET_DELETED", message);
        kafkaProducer.send(record);
    }

    public void sendWalletDepositedEvent(String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(walletTopic, "WALLET_DEPOSITED", message);
        kafkaProducer.send(record);
    }

    public void sendWalletWithdrawnEvent(String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(walletTopic, "WALLET_WITHDRAWN", message);
        kafkaProducer.send(record);
    }
}
