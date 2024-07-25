package myproject.wallet.domain.paymentgateway.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayEventConsumer {

    @KafkaListener(topics = "payment-initiated", groupId = "paymentgateway-group")
    public void listenPaymentInitiated(String message) {
        // Handle the payment initiated event
        System.out.println("Received Payment Initiated Event: " + message);
        // Implement event handling logic
    }

    @KafkaListener(topics = "payment-completed", groupId = "paymentgateway-group")
    public void listenPaymentCompleted(String message) {
        // Handle the payment completed event
        System.out.println("Received Payment Completed Event: " + message);
        // Implement event handling logic
    }

    @KafkaListener(topics = "payment-failed", groupId = "paymentgateway-group")
    public void listenPaymentFailed(String message) {
        // Handle the payment failed event
        System.out.println("Received Payment Failed Event: " + message);
        // Implement event handling logic
    }
}
