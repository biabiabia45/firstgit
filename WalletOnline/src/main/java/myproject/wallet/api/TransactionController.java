package myproject.wallet.api;

import myproject.wallet.domain.transaction.entity.Transaction;
import myproject.wallet.domain.transaction.service.TransactionService;
import myproject.wallet.domain.valueobject.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/wallet-online/wallets/{walletId}/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/source")
    public ResponseEntity<List<Transaction>> getAllTransactionsBySourceWalletId(@PathVariable Long walletId) {
        List<Transaction> transactions = transactionService.getAllTransactionsBySourceWalletId(walletId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/target")
    public ResponseEntity<List<Transaction>> getAllTransactionsByTargetWalletId(@PathVariable Long walletId) {
        List<Transaction> transactions = transactionService.getAllTransactionsByTargetWalletId(walletId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(
            @PathVariable Long walletId, @PathVariable Long transactionId) {
        Optional<Transaction> transaction = transactionService.getTransactionByIdAndSourceWalletId(transactionId, walletId);
        if (transaction.isEmpty()) {
            transaction = transactionService.getTransactionByIdAndTargetWalletId(transactionId, walletId);
        }
        return transaction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> transfer(
            @PathVariable Long walletId,
            @RequestParam Long targetWalletId,
            @RequestParam Money amount) {
        try {
            Money money = amount;
            transactionService.transfer(walletId, targetWalletId, money);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // Handle errors (e.g., insufficient funds, invalid wallets)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

