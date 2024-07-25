package myproject.wallet.domain.notification.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class NotificationEventProducer {

    private final KafkaProducer<String, String> kafkaProducer;
    private final String notificationCreatedTopic;
    private final String notificationUpdatedTopic;
    private final String notificationDeletedTopic;

    public NotificationEventProducer(
            @Value("${spring.kafka.bootstrap-servers}") String bootstrapServers,
            @Value("${kafka.notification-created-topic}") String notificationCreatedTopic,
            @Value("${kafka.notification-updated-topic}") String notificationUpdatedTopic,
            @Value("${kafka.notification-deleted-topic}") String notificationDeletedTopic) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        this.kafkaProducer = new KafkaProducer<>(properties);
        this.notificationCreatedTopic = notificationCreatedTopic;
        this.notificationUpdatedTopic = notificationUpdatedTopic;
        this.notificationDeletedTopic = notificationDeletedTopic;
    }

    public void sendNotificationCreatedEvent(String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(notificationCreatedTopic, "NOTIFICATION_CREATED", message);
        kafkaProducer.send(record);
    }

    public void sendNotificationUpdatedEvent(String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(notificationUpdatedTopic, "NOTIFICATION_UPDATED", message);
        kafkaProducer.send(record);
    }

    public void sendNotificationDeletedEvent(String message) {
        ProducerRecord<String, String> record = new ProducerRecord<>(notificationDeletedTopic, "NOTIFICATION_DELETED", message);
        kafkaProducer.send(record);
    }
}
