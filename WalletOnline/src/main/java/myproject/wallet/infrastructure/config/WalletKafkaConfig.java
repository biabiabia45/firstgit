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
public class WalletKafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${kafka.wallet-created-topic}")
    private String walletCreatedTopic;

    @Value("${kafka.wallet-updated-topic}")
    private String walletUpdatedTopic;

    @Value("${kafka.wallet-deleted-topic}")
    private String walletDeletedTopic;

    @Value("${kafka.wallet-deposited-topic}")
    private String walletDepositedTopic;
    @Value("${kafka.wallet-withdrawn-topic}")
    private String walletWithdrawnTopic;

    @Bean(name = "walletProducerFactory")
    public ProducerFactory<String, String> walletProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean(name = "walletKafkaTemplate")
    public KafkaTemplate<String, String> walletKafkaTemplate() {
        return new KafkaTemplate<>(walletProducerFactory());
    }

    @Bean(name = "walletConsumerFactory")
    public ConsumerFactory<String, String> walletConsumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "wallet-group");
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean(name = "walletMessageListenerContainer")
    public ConcurrentMessageListenerContainer<String, String> walletMessageListenerContainer() {
        ContainerProperties containerProperties = new ContainerProperties(walletCreatedTopic, walletUpdatedTopic, walletDeletedTopic, walletDepositedTopic, walletWithdrawnTopic);
        ConcurrentMessageListenerContainer<String, String> container = new ConcurrentMessageListenerContainer<>(walletConsumerFactory(), containerProperties);
        container.setupMessageListener((MessageListener<String, String>) record -> {
            System.out.println("Received message: " + record.value());
        });
        return container;
    }
}
