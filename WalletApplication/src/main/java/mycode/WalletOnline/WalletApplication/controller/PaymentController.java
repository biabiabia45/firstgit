package mycode.WalletOnline.WalletApplication.controller;

import mycode.WalletOnline.WalletApplication.dto.PaymentDto;
import mycode.WalletOnline.WalletApplication.dto.TransactionDto;
import mycode.WalletOnline.WalletApplication.model.Transaction;
import mycode.WalletOnline.WalletApplication.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/online")
    public ResponseEntity<Transaction> onlinePayment(@RequestBody TransactionDto transactionDto) {
        Transaction transaction = paymentService.onlinePayment(transactionDto);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/offline")
    public ResponseEntity<Transaction> offlinePayment(@RequestBody TransactionDto transactionDto) {
        Transaction transaction = paymentService.offlinePayment(transactionDto);
        return ResponseEntity.ok(transaction);
    }
}
