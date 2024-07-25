package myproject.wallet.domain.paymentgateway;

import myproject.wallet.domain.paymentgateway.events.*;
import myproject.wallet.domain.paymentgateway.kafka.PaymentGatewayEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class PaymentGatewayService {
    private final PaymentGatewayRepository paymentGatewayRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final PaymentGatewayEventProducer paymentGatewayEventProducer;

    @Autowired
    public PaymentGatewayService(PaymentGatewayRepository paymentGatewayRepository, ApplicationEventPublisher eventPublisher, PaymentGatewayEventProducer paymentGatewayEventProducer) {
        this.paymentGatewayRepository = paymentGatewayRepository;
        this.eventPublisher = eventPublisher;
        this.paymentGatewayEventProducer = paymentGatewayEventProducer;
    }

    public Optional<PaymentGateway> getPaymentGatewayById(UUID id) {
        return paymentGatewayRepository.findById(id);
    }

    public List<PaymentGateway> getAllPaymentGateways() {
        return paymentGatewayRepository.findAll();
    }

    public void createPayment(PaymentGateway payment) {
        paymentGatewayRepository.save(payment);
        PaymentGatewayCreatedEvent event = new PaymentGatewayCreatedEvent(payment.getId());
        eventPublisher.publishEvent(event);
        paymentGatewayEventProducer.sendPaymentInitiatedEvent(event.toString());
    }

    public void updatePayment(PaymentGateway payment) {
        if (paymentGatewayRepository.existsById(payment.getId())) {
            paymentGatewayRepository.save(payment);
            PaymentGatewayUpdatedEvent event = new PaymentGatewayUpdatedEvent(payment.getId());
            eventPublisher.publishEvent(event);
            paymentGatewayEventProducer.sendPaymentInitiatedEvent(event.toString());
        }
    }

    public void deletePayment(UUID paymentId) {
        if (paymentGatewayRepository.existsById(paymentId)) {
            paymentGatewayRepository.deleteById(paymentId);
            PaymentGatewayDeletedEvent event = new PaymentGatewayDeletedEvent(paymentId);
            eventPublisher.publishEvent(event);
            paymentGatewayEventProducer.sendPaymentInitiatedEvent(event.toString());
        }
    }
    public void initiatePayment(PaymentGateway payment) {
        payment.setStatus("initiated");
        paymentGatewayRepository.save(payment);
        PaymentInitiatedEvent event = new PaymentInitiatedEvent(payment.getId());
        eventPublisher.publishEvent(event);
        paymentGatewayEventProducer.sendPaymentInitiatedEvent(event.toString());
    }

    public void completePayment(PaymentGateway payment) {
        if (paymentGatewayRepository.existsById(payment.getId())) {
            payment.setStatus("completed");
            paymentGatewayRepository.save(payment);
            PaymentCompletedEvent event = new PaymentCompletedEvent(payment.getId());
            eventPublisher.publishEvent(event);
            paymentGatewayEventProducer.sendPaymentCompletedEvent(event.toString());
        }
    }

    public void failPayment(PaymentGateway payment) {
        if (paymentGatewayRepository.existsById(payment.getId())) {
            payment.setStatus("failed");
            paymentGatewayRepository.save(payment);
            PaymentFailedEvent event = new PaymentFailedEvent(payment.getId());
            eventPublisher.publishEvent(event);
            paymentGatewayEventProducer.sendPaymentFailedEvent(event.toString());
        }
    }
}
