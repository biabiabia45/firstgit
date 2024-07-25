package myproject.wallet.domain.paymentgateway.events;

import java.util.UUID;

public class PaymentGatewayCreatedEvent {

    private UUID paymentId;

    public PaymentGatewayCreatedEvent(UUID paymentId) {
        this.paymentId = paymentId;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public String toString() {
        return "PaymentGatewayCreatedEvent{" +
                "paymentId=" + paymentId +
                '}';
    }
}
