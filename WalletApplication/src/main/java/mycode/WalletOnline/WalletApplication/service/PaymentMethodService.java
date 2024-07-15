package mycode.WalletOnline.WalletApplication.service;

import jakarta.persistence.EntityNotFoundException;
import mycode.WalletOnline.WalletApplication.dto.PaymentMethodDto;
import mycode.WalletOnline.WalletApplication.model.PaymentMethod;
import mycode.WalletOnline.WalletApplication.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService {
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    public PaymentMethod addPaymentMethod(PaymentMethodDto paymentMethodDto) {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setUserId(paymentMethodDto.getUserId());
        paymentMethod.setType(paymentMethodDto.getType());
        paymentMethod.setDetails(paymentMethodDto.getDetails());
        return paymentMethodRepository.save(paymentMethod);
    }

    public List<PaymentMethod> getPaymentMethods(Long userId) {
        return paymentMethodRepository.findByUserId(userId);
    }

    public PaymentMethod updatePaymentMethod(Long paymentMethodId, PaymentMethodDto paymentMethodDto) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentMethodId)
                .orElseThrow(() -> new EntityNotFoundException("Payment method not found"));
        paymentMethod.setDetails(paymentMethodDto.getDetails());
        return paymentMethodRepository.save(paymentMethod);
    }

    public void deletePaymentMethod(Long paymentMethodId) {
        paymentMethodRepository.deleteById(paymentMethodId);
    }
}