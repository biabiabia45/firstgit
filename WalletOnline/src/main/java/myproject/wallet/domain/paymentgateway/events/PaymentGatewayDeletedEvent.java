package myproject.wallet.domain.paymentgateway.events;

import java.util.UUID;

public class PaymentGatewayDeletedEvent {

    private UUID paymentId;

    public PaymentGatewayDeletedEvent(UUID paymentId) {
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
        return "PaymentGatewayDeletedEvent{" +
                "paymentId=" + paymentId +
                '}';
    }
}
