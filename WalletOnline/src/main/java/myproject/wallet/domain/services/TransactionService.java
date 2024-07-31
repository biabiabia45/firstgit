package myproject.wallet.domain.services;

import myproject.wallet.domain.entities.Transaction;
import myproject.wallet.domain.events.TransactionTreansferedEvent;
import myproject.wallet.domain.exceptions.InsufficientFundsException;
import myproject.wallet.domain.exceptions.WalletNotFoundException;
import myproject.wallet.domain.transaction.exception.InsufficientFundsException;
import myproject.wallet.domain.transaction.exception.WalletNotFoundException;
import myproject.wallet.infrastructure.common.db.repositories.persistence.messaging.TransactionEventProducer;
import myproject.wallet.domain.repository.TransactionRepository;
import myproject.wallet.domain.repository.UserRepository;
import myproject.wallet.domain.entities.Wallet;
import myproject.wallet.domain.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final TransactionEventProducer transactionEventProducer;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, WalletRepository walletRepository,
                              UserRepository userRepository, TransactionEventProducer transactionEventProducer) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
        this.transactionEventProducer = transactionEventProducer;
    }

    public List<Transaction> getAllTransactionsBySourceWalletId(UUID sourceWalletId) {
        return transactionRepository.findBySourceWalletId(sourceWalletId);
    }

    public List<Transaction> getAllTransactionsByTargetWalletId(UUID targetWalletId) {
        return transactionRepository.findByTargetWalletId(targetWalletId);
    }

    public Optional<Transaction> getTransactionByIdAndSourceWalletId(UUID transactionId, UUID sourceWalletId) {
        return transactionRepository.findByIdAndSourceWalletId(transactionId, sourceWalletId);
    }

    public Optional<Transaction> getTransactionByIdAndTargetWalletId(UUID transactionId, UUID targetWalletId) {
        return transactionRepository.findByIdAndTargetWalletId(transactionId, targetWalletId);
    }

    @Transactional
    public void transfer(UUID sourceWalletId, UUID targetWalletId, BigDecimal amount) {
        validateTransferAmount(amount);

        Wallet sourceWallet = findWalletById(sourceWalletId);
        Wallet targetWallet = findWalletById(targetWalletId);

        validateSufficientFunds(sourceWallet, amount);

        executeTransfer(sourceWallet, targetWallet, amount);

        // Create and save a transaction record
        Transaction transaction = createTransaction(sourceWalletId, targetWalletId, amount);

        // Create and publish a domain event
        publishTransactionCreatedEvent(transaction);
    }

    private void validateTransferAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }
    }

    private Wallet findWalletById(UUID walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found with ID: " + walletId));
    }

    private void validateSufficientFunds(Wallet sourceWallet, BigDecimal amount) {
        if (sourceWallet.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds in source wallet");
        }
    }

    private void executeTransfer(Wallet sourceWallet, Wallet targetWallet, BigDecimal amount) {
        sourceWallet.setBalance(sourceWallet.getBalance().subtract(amount));
        targetWallet.setBalance(targetWallet.getBalance().add(amount));

        walletRepository.save(sourceWallet);
        walletRepository.save(targetWallet);
    }

    private Transaction createTransaction(UUID sourceWalletId, UUID targetWalletId, BigDecimal amount) {
        Transaction transaction = new Transaction();
        transaction.setSourceWalletId(sourceWalletId);
        transaction.setTargetWalletId(targetWalletId);
        transaction.setAmount(amount);
        transactionRepository.save(transaction);
        return transaction;
    }

    private void publishTransactionCreatedEvent(Transaction transaction) {
        TransactionTreansferedEvent event = new TransactionTreansferedEvent(
                transaction.getId(),
                transaction.getSourceWalletId(),
                transaction.getTargetWalletId(),
                transaction.getAmount()
        );
        transactionEventProducer.sendTransactionTransferedEvent(event);
    }
}
