package mycode.WalletOnline.WalletApplication.controller;

import mycode.WalletOnline.WalletApplication.dto.PaymentMethodDto;
import mycode.WalletOnline.WalletApplication.model.PaymentMethod;
import mycode.WalletOnline.WalletApplication.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment-methods")
public class PaymentMethodController {
    @Autowired
    private PaymentMethodService paymentMethodService;

    @PostMapping
    public ResponseEntity<PaymentMethod> addPaymentMethod(@RequestBody PaymentMethodDto paymentMethodDto) {
        PaymentMethod paymentMethod = paymentMethodService.addPaymentMethod(paymentMethodDto);
        return ResponseEntity.ok(paymentMethod);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<PaymentMethod>> getPaymentMethods(@PathVariable Long userId) {
        List<PaymentMethod> paymentMethods = paymentMethodService.getPaymentMethods(userId);
        return ResponseEntity.ok(paymentMethods);
    }

    @PutMapping("/{paymentMethodId}")
    public ResponseEntity<PaymentMethod> updatePaymentMethod(@PathVariable Long paymentMethodId, @RequestBody PaymentMethodDto paymentMethodDto) {
        PaymentMethod updatedPaymentMethod = paymentMethodService.updatePaymentMethod(paymentMethodId, paymentMethodDto);
        return ResponseEntity.ok(updatedPaymentMethod);
    }

    @DeleteMapping("/{paymentMethodId}")
    public ResponseEntity<Void> deletePaymentMethod(@PathVariable Long paymentMethodId) {
        paymentMethodService.deletePaymentMethod(paymentMethodId);
        return ResponseEntity.noContent().build();
    }
}

