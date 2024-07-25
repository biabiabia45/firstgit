package myproject.wallet.domain.paymentgateway.events;

import java.util.UUID;

public class PaymentCompletedEvent {

    private UUID paymentId;

    public PaymentCompletedEvent(UUID paymentId) {
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
        return "PaymentCompletedEvent{" +
                "paymentId=" + paymentId +
                '}';
    }
}
