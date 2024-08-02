package myproject.wallet.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "转账信息")
@RequestMapping("/wallet-online/wallets/{walletId}/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/source")
    @ApiOperation("查找转账账户的转账信息")
    @ApiImplicitParam(name = "walletId", value = "付款方账户", required = true)
    public ResponseEntity<List<Transaction>> getAllTransactionsBySourceWalletId(@PathVariable Long walletId) {
        List<Transaction> transactions = transactionService.getAllTransactionsBySourceWalletId(walletId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/target")
    @ApiOperation("查找收款方账户的转账信息")
    @ApiImplicitParam(name = "walletId", value = "收款方账户", required = true)
    public ResponseEntity<List<Transaction>> getAllTransactionsByTargetWalletId(@PathVariable Long walletId) {
        List<Transaction> transactions = transactionService.getAllTransactionsByTargetWalletId(walletId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{transactionId}")
    @ApiOperation("查找转账信息")
    @ApiImplicitParam(name = "id", value = "转账记录", required = true)
    public ResponseEntity<Transaction> getTransactionById(
            @PathVariable Long walletId, @PathVariable Long transactionId) {
        Optional<Transaction> transaction = transactionService.getTransactionByIdAndSourceWalletId(transactionId, walletId);
        if (transaction.isEmpty()) {
            transaction = transactionService.getTransactionByIdAndTargetWalletId(transactionId, walletId);
        }
        return transaction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/transfer")
    @ApiOperation("转账操作")
    public ResponseEntity<Void> transfer(
            @PathVariable Long walletId,
            @RequestParam Long targetWalletId,
            @RequestParam Money amount) {
        try {
            transactionService.transfer(walletId, targetWalletId, amount);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // Handle errors (e.g., insufficient funds, invalid wallets)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

