package myproject.wallet.domain.wallet;

import lombok.extern.slf4j.Slf4j;
import myproject.wallet.domain.wallet.events.*;
import myproject.wallet.domain.wallet.expection.InsufficientFundsException;
import myproject.wallet.domain.wallet.expection.InvalidAmountException;
import myproject.wallet.domain.wallet.expection.WalletNotFoundException;
import myproject.wallet.domain.wallet.kafka.WalletEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
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


    public Wallet createWallet(Wallet wallet) {
        if (wallet == null) {
            throw new IllegalArgumentException("Wallet cannot be null.");
        }
        Wallet savedWallet = walletRepository.save(wallet);

        try {
            WalletCreatedEvent event = new WalletCreatedEvent(savedWallet.getId());
            eventPublisher.publishEvent(event);
            walletEventProducer.sendWalletCreatedEvent(event.toString());
        } catch (Exception e) {
            log.error("Failed to publish event or send message", e);
        }

        return savedWallet;
    }

    public Wallet updateWallet(Wallet wallet) {
        if (walletRepository.existsById(wallet.getId())) {
            Wallet updatedWallet = walletRepository.save(wallet);
            try {
                WalletUpdatedEvent event = new WalletUpdatedEvent(wallet.getId());
                eventPublisher.publishEvent(event);
                walletEventProducer.sendWalletUpdatedEvent(event.toString());
            } catch (Exception e) {
                log.error("Failed to publish event or send message", e);
            }
            return updatedWallet;
        } else {
            throw new WalletNotFoundException("Wallet with id " + wallet.getId() + " not found.");
        }
    }

    public void deleteWallet(UUID id) {
        if (walletRepository.existsById(id)) {
            walletRepository.deleteById(id);
            try {
                WalletDeletedEvent event = new WalletDeletedEvent(id);
                eventPublisher.publishEvent(event);
                walletEventProducer.sendWalletDeletedEvent(event.toString());
            } catch (Exception e) {
                log.error("Failed to publish event or send message", e);
            }
        } else {
            throw new WalletNotFoundException("Wallet with id " + id + " not found.");
        }
    }

    // Additional methods to manage balance
    public void deposit(UUID walletId, BigDecimal amount) {
        Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new WalletNotFoundException("Wallet with id " + walletId + " not found"));

        // Ensure the amount is positive
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive");
        }

        // Update wallet balance
        wallet.setBalance(wallet.getBalance().add(amount));
        walletRepository.save(wallet);

        // Publish an event
        WalletDepositedEvent event = new WalletDepositedEvent(walletId, amount);
        try {
            eventPublisher.publishEvent(event);
            walletEventProducer.sendWalletDepositedEvent(event.toString());
        } catch (Exception e) {
            log.error("Failed to publish event or send message", e);
        }
    }

    public void withdraw(UUID walletId, BigDecimal amount) {
        Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new WalletNotFoundException("Wallet with id " + walletId + " not found"));

        // Ensure the amount is positive and the wallet has sufficient funds
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive");
        }
        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        // Update wallet balance
        wallet.setBalance(wallet.getBalance().subtract(amount));
        walletRepository.save(wallet);

        // Publish an event
        WalletWithdrawnEvent event = new WalletWithdrawnEvent(walletId, amount);
        try {
            eventPublisher.publishEvent(event);
            walletEventProducer.sendWalletWithdrawnEvent(event.toString());
        } catch (Exception e) {
            log.error("Failed to publish event or send message", e);
        }
    }
}
