package myproject.wallet.domain.transaction.service;

import myproject.wallet.domain.exceptions.WalletNotFoundException;
import myproject.wallet.domain.repository.TransactionRepository;
import myproject.wallet.domain.repository.UserRepository;
import myproject.wallet.domain.repository.WalletRepository;
import myproject.wallet.domain.transaction.entity.Transaction;
import myproject.wallet.domain.transaction.event.TransactionTreansferedEvent;
import myproject.wallet.domain.transaction.kafka.TransactionEventProducer;
import myproject.wallet.domain.valueobject.Money;
import myproject.wallet.domain.wallet.entity.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void transfer(UUID sourceWalletId, UUID targetWalletId, Money amount) {
        Wallet sourceWallet = findWalletById(sourceWalletId);
        Wallet targetWallet = findWalletById(targetWalletId);

        Transaction transaction = new Transaction(UUID.randomUUID(), sourceWalletId, targetWalletId, amount);

        transaction.validateTransferAmount();
        transaction.executeTransfer(sourceWallet, targetWallet);

        walletRepository.save(sourceWallet);
        walletRepository.save(targetWallet);
        transactionRepository.save(transaction);

        publishTransactionCreatedEvent(transaction);
    }

    private Wallet findWalletById(UUID walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found with ID: " + walletId));
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
