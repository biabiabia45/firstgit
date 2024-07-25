package myproject.wallet.domain.paymentgateway.events;

import java.util.UUID;

public class PaymentInitiatedEvent {

    private UUID paymentId;

    public PaymentInitiatedEvent(UUID paymentId) {
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
        return "PaymentInitiatedEvent{" +
                "paymentId=" + paymentId +
                '}';
    }
}
