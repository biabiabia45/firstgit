package myproject.wallet.domain.user.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
public class UserKafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${kafka.user-created-topic}")
    private String userCreatedTopic;

    @Value("${kafka.user-updated-topic}")
    private String userUpdatedTopic;

    @Value("${kafka.user-deleted-topic}")
    private String userDeletedTopic;

    @Bean(name = "userProducerFactory")
    public ProducerFactory<String, String> userProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean(name = "userKafkaTemplate")
    @Primary
    public KafkaTemplate<String, String> userKafkaTemplate() {
        return new KafkaTemplate<>(userProducerFactory());
    }

    @Bean(name = "userConsumerFactory")
    public ConsumerFactory<String, String> userConsumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "user-group");
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean(name = "userMessageListenerContainer")
    public ConcurrentMessageListenerContainer<String, String> userMessageListenerContainer() {
        ContainerProperties containerProperties = new ContainerProperties(userCreatedTopic, userUpdatedTopic, userDeletedTopic);
        ConcurrentMessageListenerContainer<String, String> container = new ConcurrentMessageListenerContainer<>(userConsumerFactory(), containerProperties);
        container.setupMessageListener((MessageListener<String, String>) record -> {
            log.info("Received message: {}", record.value());
        });
        return container;

    }
}
