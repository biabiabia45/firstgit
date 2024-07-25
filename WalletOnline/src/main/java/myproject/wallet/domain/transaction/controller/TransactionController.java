package myproject.wallet.domain.transaction.controller;

import myproject.wallet.domain.transaction.Transaction;
import myproject.wallet.domain.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/wallet-online/wallets/{walletId}/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/source")
    public ResponseEntity<List<Transaction>> getAllTransactionsBySourceWalletId(@PathVariable UUID walletId) {
        List<Transaction> transactions = transactionService.getAllTransactionsBySourceWalletId(walletId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/target")
    public ResponseEntity<List<Transaction>> getAllTransactionsByTargetWalletId(@PathVariable UUID walletId) {
        List<Transaction> transactions = transactionService.getAllTransactionsByTargetWalletId(walletId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(
            @PathVariable UUID walletId, @PathVariable UUID transactionId) {
        Optional<Transaction> transaction = transactionService.getTransactionByIdAndSourceWalletId(transactionId, walletId);
        if (transaction.isEmpty()) {
            transaction = transactionService.getTransactionByIdAndTargetWalletId(transactionId, walletId);
        }
        return transaction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> transfer(
            @PathVariable UUID walletId,
            @RequestParam UUID targetWalletId,
            @RequestParam BigDecimal amount) {
        try {
            transactionService.transfer(walletId, targetWalletId, amount);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // Handle errors (e.g., insufficient funds, invalid wallets)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

