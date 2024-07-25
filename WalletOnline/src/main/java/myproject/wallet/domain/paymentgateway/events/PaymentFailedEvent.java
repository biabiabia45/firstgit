package myproject.wallet.domain.paymentgateway.events;

import java.util.UUID;

public class PaymentFailedEvent {

    private UUID paymentId;

    public PaymentFailedEvent(UUID paymentId) {
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
        return "PaymentFailedEvent{" +
                "paymentId=" + paymentId +
                '}';
    }
}
