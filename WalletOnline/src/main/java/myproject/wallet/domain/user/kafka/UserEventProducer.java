package myproject.wallet.domain.user.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class UserEventProducer {

    private final KafkaProducer<String, String> kafkaProducer;
    private final String userCreatedTopic;
    private final String userUpdatedTopic;
    private final String userDeletedTopic;

    public UserEventProducer(
            @Value("${spring.kafka.bootstrap-servers}") String bootstrapServers,
            @Value("${kafka.user-created-topic}") String userCreatedTopic,
            @Value("${kafka.user-updated-topic}") String userUpdatedTopic,
            @Value("${kafka.user-deleted-topic}") String userDeletedTopic) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        this.kafkaProducer = new KafkaProducer<>(properties);
        this.userCreatedTopic = userCreatedTopic;
        this.userUpdatedTopic = userUpdatedTopic;
        this.userDeletedTopic = userDeletedTopic;
    }

    public void sendUserCreatedEvent(String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(userCreatedTopic, "USER_CREATED", message);
        kafkaProducer.send(record);
    }

    public void sendUserUpdatedEvent(String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(userUpdatedTopic, "USER_UPDATED", message);
        kafkaProducer.send(record);
    }

    public void sendUserDeletedEvent(String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(userDeletedTopic, "USER_DELETED", message);
        kafkaProducer.send(record);
    }

}
