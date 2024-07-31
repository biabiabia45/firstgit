package myproject.wallet.infrastructure.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.MessageListener;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
@EnableKafka
public class NotificationKafkaConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${kafka.notification-created-topic}")
    private String notificationCreatedTopic;

    @Value("${kafka.notification-updated-topic}")
    private String notificationUpdatedTopic;

    @Value("${kafka.notification-deleted-topic}")
    private String notificationDeletedTopic;
    @Bean(name = "notificationProducerFactory")
    public ProducerFactory<String, String> notificationProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean(name = "notificationKafkaTemplate")
    public KafkaTemplate<String, String> notificationKafkaTemplate() {
        return new KafkaTemplate<>(notificationProducerFactory());
    }

    @Bean(name = "notificationConsumerFactory")
    public ConsumerFactory<String, String> notificationConsumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "notification-group");
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean(name = "notificationMessageListenerContainer")
    public ConcurrentMessageListenerContainer<String, String> notificationMessageListenerContainer() {
        ContainerProperties containerProperties = new ContainerProperties(notificationCreatedTopic, notificationUpdatedTopic, notificationDeletedTopic);
        ConcurrentMessageListenerContainer<String, String> container = new ConcurrentMessageListenerContainer<>(notificationConsumerFactory(), containerProperties);
        container.setupMessageListener((MessageListener<String, String>) record -> {
            log.info("Received message: {}", record.value());
        });
        return container;
    }
}
