package mycode.WalletOnline.WalletApplication.service;

import mycode.WalletOnline.WalletApplication.dto.TransactionDto;
import mycode.WalletOnline.WalletApplication.model.Transaction;
import mycode.WalletOnline.WalletApplication.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Object getTransactions(Long userId) {
        return transactionRepository.findByUserId(userId);
    }
}
