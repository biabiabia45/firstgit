package mycode.WalletOnline.WalletApplication.service;

import mycode.WalletOnline.WalletApplication.dto.TransactionDto;
import mycode.WalletOnline.WalletApplication.model.Transaction;
import mycode.WalletOnline.WalletApplication.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction onlinePayment(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setUserId(transactionDto.getUserId());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setType("ONLINE_PAYMENT");
        transaction.setStatus("SUCCESS");
        transaction.setCreatedAt(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

    public Transaction offlinePayment(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setUserId(transactionDto.getUserId());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setType("OFFLINE_PAYMENT");
        transaction.setStatus("SUCCESS");
        transaction.setCreatedAt(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }
}


