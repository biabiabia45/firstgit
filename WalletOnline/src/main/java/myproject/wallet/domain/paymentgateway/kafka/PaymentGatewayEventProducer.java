package myproject.wallet.domain.paymentgateway.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public PaymentGatewayEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPaymentInitiatedEvent(String event) {
        kafkaTemplate.send("payment-initiated", event);
    }

    public void sendPaymentCompletedEvent(String event) {
        kafkaTemplate.send( "payment-completed", event);
    }

    public void sendPaymentFailedEvent(String event) {
        kafkaTemplate.send("payment-failed", event);
    }
}
