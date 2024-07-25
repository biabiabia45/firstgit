package myproject.wallet.api.paymentgateway;

import myproject.wallet.domain.paymentgateway.PaymentGateway;
import myproject.wallet.domain.paymentgateway.PaymentGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
public class PaymentGatewayController {

    private final PaymentGatewayService paymentGatewayService;

    @Autowired
    public PaymentGatewayController(PaymentGatewayService paymentGatewayService) {
        this.paymentGatewayService = paymentGatewayService;
    }

    // Create a new payment
    @PostMapping
    public ResponseEntity<PaymentGateway> createPayment(@RequestBody PaymentGateway payment) {
        paymentGatewayService.createPayment(payment);
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }

    // Get a payment by ID
    @GetMapping("/{id}")
    public ResponseEntity<PaymentGateway> getPaymentById(@PathVariable UUID id) {
        Optional<PaymentGateway> payment = paymentGatewayService.getPaymentGatewayById(id);
        return payment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all payments
    @GetMapping
    public ResponseEntity<List<PaymentGateway>> getAllPayments() {
        List<PaymentGateway> payments = paymentGatewayService.getAllPaymentGateways();
        return ResponseEntity.ok(payments);
    }

    // Update an existing payment
    @PutMapping("/{id}")
    public ResponseEntity<PaymentGateway> updatePayment(@PathVariable UUID id, @RequestBody PaymentGateway payment) {
        payment.setId(id);
        paymentGatewayService.updatePayment(payment);
        return ResponseEntity.ok(payment);
    }

    // Delete a payment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable UUID id) {
        paymentGatewayService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

    // Initiate payment
    @PostMapping("/{id}/initiate")
    public ResponseEntity<Void> initiatePayment(@PathVariable UUID id) {
        Optional<PaymentGateway> paymentOpt = paymentGatewayService.getPaymentGatewayById(id);
        if (paymentOpt.isPresent()) {
            paymentGatewayService.initiatePayment(paymentOpt.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Complete payment
    @PostMapping("/{id}/complete")
    public ResponseEntity<Void> completePayment(@PathVariable UUID id) {
        Optional<PaymentGateway> paymentOpt = paymentGatewayService.getPaymentGatewayById(id);
        if (paymentOpt.isPresent()) {
            paymentGatewayService.completePayment(paymentOpt.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Fail payment
    @PostMapping("/{id}/fail")
    public ResponseEntity<Void> failPayment(@PathVariable UUID id) {
        Optional<PaymentGateway> paymentOpt = paymentGatewayService.getPaymentGatewayById(id);
        if (paymentOpt.isPresent()) {
            paymentGatewayService.failPayment(paymentOpt.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
