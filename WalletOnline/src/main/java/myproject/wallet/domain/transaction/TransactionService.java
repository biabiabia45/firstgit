package myproject.wallet.domain.transaction;

import myproject.wallet.domain.transaction.kafka.TransactionEventProducer;
import myproject.wallet.domain.transaction.events.TransactionCreatedEvent;
import myproject.wallet.domain.transaction.events.TransactionUpdatedEvent;
import myproject.wallet.domain.transaction.events.TransactionDeletedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final TransactionEventProducer transactionEventProducer;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, ApplicationEventPublisher eventPublisher, TransactionEventProducer transactionEventProducer) {
        this.transactionRepository = transactionRepository;
        this.eventPublisher = eventPublisher;
        this.transactionEventProducer = transactionEventProducer;
    }

    public Optional<Transaction> getTransactionById(UUID id) {
        return transactionRepository.findById(id);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public void createTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
        TransactionCreatedEvent event = new TransactionCreatedEvent(transaction.getId());
        eventPublisher.publishEvent(event);
        transactionEventProducer.sendTransactionCreatedEvent(event.toString());
    }

    public void updateTransaction(Transaction transaction) {
        if (transactionRepository.existsById(transaction.getId())) {
            transactionRepository.save(transaction);
            TransactionUpdatedEvent event = new TransactionUpdatedEvent(transaction.getId());
            eventPublisher.publishEvent(event);
            transactionEventProducer.sendTransactionUpdatedEvent(event.toString());
        }
    }

    public void deleteTransaction(UUID id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
            TransactionDeletedEvent event = new TransactionDeletedEvent(id);
            eventPublisher.publishEvent(event);
            transactionEventProducer.sendTransactionDeletedEvent(event.toString());
        }
    }
}
