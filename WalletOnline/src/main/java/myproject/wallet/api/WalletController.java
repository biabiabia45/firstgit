package myproject.wallet.api;

import lombok.extern.slf4j.Slf4j;
import myproject.wallet.domain.exceptions.InsufficientFundsException;
import myproject.wallet.domain.exceptions.InvalidAmountException;
import myproject.wallet.domain.exceptions.WalletNotFoundException;
import myproject.wallet.domain.wallet.entity.Wallet;
import myproject.wallet.domain.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/wallet-online/users/{userId}/wallets")
public class WalletController {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping
    public ResponseEntity<List<Wallet>> getAllWallets() {
        List<Wallet> wallets = walletService.getAllWallets();
        return ResponseEntity.ok(wallets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wallet> getWalletById(@PathVariable UUID id) {
        Optional<Wallet> wallet = walletService.getWalletById(id);
        return wallet.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Wallet> createWallet(@PathVariable UUID userId, @RequestBody Wallet wallet) {
        try {
            wallet.setUserId(userId);
            Wallet createdWallet = walletService.createWallet(wallet);
            return new ResponseEntity<>(createdWallet, HttpStatus.CREATED); // 201 Created
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        } catch (Exception e) {
            log.error("Error creating wallet", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Wallet> updateWallet(@PathVariable UUID userId, @PathVariable UUID id, @RequestBody Wallet wallet) {
        wallet.setId(id);
        wallet.setUserId(userId);
        try {
            Wallet updatedWallet = walletService.updateWallet(wallet);
            return ResponseEntity.ok(updatedWallet); // 200 OK
        } catch (WalletNotFoundException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        } catch (Exception e) {
            log.error("Error updating wallet", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWallet(@PathVariable UUID userId, @PathVariable UUID id) {
        try {
            walletService.deleteWallet(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (WalletNotFoundException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        } catch (Exception e) {
            log.error("Error deleting wallet", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<Void> deposit(@PathVariable UUID userId, @PathVariable UUID id, @RequestParam BigDecimal amount) {
        try {
            walletService.deposit(id, amount);
            return ResponseEntity.ok().build(); // 200 OK
        } catch (WalletNotFoundException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        } catch (InvalidAmountException e) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        } catch (Exception e) {
            log.error("Error depositing to wallet", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<Void> withdraw(@PathVariable UUID userId, @PathVariable UUID id, @RequestParam BigDecimal amount) {
        try {
            walletService.withdraw(id, amount);
            return ResponseEntity.ok().build(); // 200 OK
        } catch (WalletNotFoundException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        } catch (InvalidAmountException e) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        } catch (InsufficientFundsException e) {
            return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).build(); // 402 Payment Required
        } catch (Exception e) {
            log.error("Error withdrawing from wallet", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }
}

