package myproject.wallet.domain.transaction;

import myproject.wallet.domain.transaction.kafka.TransactionEventProducer;
import myproject.wallet.domain.user.UserRepository;
import myproject.wallet.domain.wallet.Wallet;
import myproject.wallet.domain.wallet.WalletRepository;
import myproject.wallet.domain.wallet.expection.InsufficientFundsException;
import myproject.wallet.domain.wallet.expection.WalletNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final UserRepository userRepository; // Added UserRepository for user validation
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

    public void transfer(UUID sourceWalletId, UUID targetWalletId, BigDecimal amount) {
        Wallet sourceWallet = walletRepository.findById(sourceWalletId)
                .orElseThrow(() -> new WalletNotFoundException("Source wallet not found"));
        Wallet targetWallet = walletRepository.findById(targetWalletId)
                .orElseThrow(() -> new WalletNotFoundException("Target wallet not found"));

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }
        if (sourceWallet.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds in source wallet");
        }

        sourceWallet.setBalance(sourceWallet.getBalance().subtract(amount));
        targetWallet.setBalance(targetWallet.getBalance().add(amount));

        walletRepository.save(sourceWallet);
        walletRepository.save(targetWallet);

        // Create and save a transaction record
        Transaction transaction = new Transaction();
        transaction.setSourceWalletId(sourceWalletId);
        transaction.setTargetWalletId(targetWalletId);
        transaction.setAmount(amount);
        transactionRepository.save(transaction);

        // Send an event with the transaction details
        transactionEventProducer.sendTransactionEvent(transaction.getId().toString(), sourceWalletId.toString(), targetWalletId.toString(), amount.toString());
    }

}
