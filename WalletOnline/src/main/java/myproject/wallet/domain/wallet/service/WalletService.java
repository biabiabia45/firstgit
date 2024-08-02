package myproject.wallet.domain.wallet.service;

import lombok.extern.slf4j.Slf4j;
import myproject.wallet.domain.exceptions.WalletNotFoundException;
import myproject.wallet.domain.repository.WalletRepository;
import myproject.wallet.domain.valueobject.Money;
import myproject.wallet.domain.wallet.entity.Wallet;
import myproject.wallet.domain.wallet.events.*;
import myproject.wallet.domain.wallet.kafka.WalletEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Wallet> getWalletById(Long id) {
        return walletRepository.findById(id);
    }

    public List<Wallet> getWalletsByUserId(Long userId) {
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

    public void deleteWallet(Long id) {
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

    // Methods to manage balance using the new Wallet methods
    public void deposit(Long walletId, Money amount) {
        Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new WalletNotFoundException("Wallet with id " + walletId + " not found"));

        wallet.deposit(amount);
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

    public void withdraw(Long walletId, Money amount) {
        Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new WalletNotFoundException("Wallet with id " + walletId + " not found"));

        wallet.withdraw(amount);
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
