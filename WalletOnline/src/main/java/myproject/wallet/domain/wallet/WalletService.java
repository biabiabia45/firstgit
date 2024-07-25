package myproject.wallet.domain.wallet;

import myproject.wallet.domain.wallet.events.WalletCreatedEvent;
import myproject.wallet.domain.wallet.events.WalletUpdatedEvent;
import myproject.wallet.domain.wallet.events.WalletDeletedEvent;
import myproject.wallet.domain.wallet.kafka.WalletEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final WalletEventProducer walletEventProducer;

    @Autowired
    public WalletService(WalletRepository walletRepository, ApplicationEventPublisher eventPublisher, WalletEventProducer walletEventProducer) {
        this.walletRepository = walletRepository;
        this.eventPublisher = eventPublisher;
        this.walletEventProducer = walletEventProducer;
    }

    public Optional<Wallet> getWalletById(UUID id) {
        return walletRepository.findById(id);
    }

    public List<Wallet> getWalletsByUserId(UUID userId) {
        return walletRepository.findByUserId(userId);
    }

    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    public void createWallet(Wallet wallet) {
        walletRepository.save(wallet);
        WalletCreatedEvent event = new WalletCreatedEvent(wallet.getId());
        eventPublisher.publishEvent(event);
        walletEventProducer.sendWalletCreatedEvent(event.toString());
    }

    public void updateWallet(Wallet wallet) {
        if (walletRepository.existsById(wallet.getId())) {
            walletRepository.save(wallet);
            WalletUpdatedEvent event = new WalletUpdatedEvent(wallet.getId());
            eventPublisher.publishEvent(event);
            walletEventProducer.sendWalletUpdatedEvent(event.toString());
        }
    }

    public void deleteWallet(UUID id) {
        if (walletRepository.existsById(id)) {
            walletRepository.deleteById(id);
            WalletDeletedEvent event = new WalletDeletedEvent(id);
            eventPublisher.publishEvent(event);
            walletEventProducer.sendWalletDeletedEvent(event.toString());
        }
    }

    // Additional methods to manage balance
    public void deposit(UUID walletId, BigDecimal amount) {
        Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new RuntimeException("Wallet not found"));

        // Ensure the amount is positive
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }

        // Update wallet balance
        wallet.setBalance(wallet.getBalance().add(amount));
        walletRepository.save(wallet);
    }

    public void withdraw(UUID walletId, BigDecimal amount) {
        Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new RuntimeException("Wallet not found"));
        // Ensure the amount is positive and the wallet has sufficient funds
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        // Update wallet balance
        wallet.setBalance(wallet.getBalance().subtract(amount));
        walletRepository.save(wallet);
    }
}
