package mycode.WalletOnline.WalletApplication.service;

import mycode.WalletOnline.WalletApplication.dto.TransactionDto;
import mycode.WalletOnline.WalletApplication.model.Transaction;
import mycode.WalletOnline.WalletApplication.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WalletService {
    @Autowired
    private TransactionRepository transactionRepository;
    public Double getBalance(Long userId) {
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        return transactions.stream().mapToDouble(Transaction::getAmount).sum();
    }

    public List<Transaction> getTransactions(Long userId) {
        return transactionRepository.findByUserId(userId);
    }

    public Transaction deposit(Long userId, TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setUserId(userId);
        transaction.setAmount(transactionDto.getAmount());
        transaction.setType("DEPOSIT");
        transaction.setStatus("SUCCESS");
        transaction.setCreatedAt(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

    public Transaction withdraw(Long userId, TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setUserId(userId);
        transaction.setAmount(transactionDto.getAmount());
        transaction.setType("WITHDRAW");
        transaction.setStatus("SUCCESS");
        transaction.setCreatedAt(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }
}
