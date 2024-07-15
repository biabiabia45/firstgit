package mycode.WalletOnline.WalletApplication.controller;

import mycode.WalletOnline.WalletApplication.dto.TransactionDto;
import mycode.WalletOnline.WalletApplication.model.Transaction;
import mycode.WalletOnline.WalletApplication.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {
    @Autowired
    private WalletService walletService;

    @GetMapping("/{userId}/balance")
    public ResponseEntity<Double> getBalance(@PathVariable Long userId) {
        Double balance = walletService.getBalance(userId);
        return ResponseEntity.ok(balance);
    }

    @GetMapping("/{userId}/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable Long userId) {
        List<Transaction> transactions = walletService.getTransactions(userId);
        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/{userId}/deposit")
    public ResponseEntity<Transaction> deposit(@PathVariable Long userId, @RequestBody TransactionDto transactionDto) {
        Transaction transaction = walletService.deposit(userId, transactionDto);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/{userId}/withdraw")
    public ResponseEntity<Transaction> withdraw(@PathVariable Long userId, @RequestBody TransactionDto transactionDto) {
        Transaction transaction = walletService.withdraw(userId, transactionDto);
        return ResponseEntity.ok(transaction);
    }
}
