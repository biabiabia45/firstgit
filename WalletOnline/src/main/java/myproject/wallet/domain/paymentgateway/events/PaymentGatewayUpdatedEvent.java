package myproject.wallet.domain.paymentgateway.events;

import java.util.UUID;

public class PaymentGatewayUpdatedEvent {

    private UUID paymentId;

    public PaymentGatewayUpdatedEvent(UUID paymentId) {
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
        return "PaymentGatewayUpdatedEvent{" +
                "paymentId=" + paymentId +
                '}';
    }
}
