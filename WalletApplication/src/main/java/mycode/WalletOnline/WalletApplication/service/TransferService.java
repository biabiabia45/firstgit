package mycode.WalletOnline.WalletApplication.service;

import mycode.WalletOnline.WalletApplication.dto.PaymentDto;
import mycode.WalletOnline.WalletApplication.dto.TransferDto;
import mycode.WalletOnline.WalletApplication.model.Transaction;
import mycode.WalletOnline.WalletApplication.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransferService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction transfer(TransferDto transferDto) {
        Transaction transaction = new Transaction();
        transaction.setUserId(transferDto.getSenderUserId());
        transaction.setAmount(transferDto.getAmount());
        transaction.setType("TRANSFER");
        transaction.setStatus("SUCCESS");
        transaction.setCreatedAt(LocalDateTime.now());
        // Here we could also add a record for the receiver, if necessary
        return transactionRepository.save(transaction);
    }

    public Transaction bankTransfer(TransferDto transferDto) {
        Transaction transaction = new Transaction();
        transaction.setUserId(transferDto.getSenderUserId());
        transaction.setAmount(transferDto.getAmount());
        transaction.setType("BANK_TRANSFER");
        transaction.setStatus("SUCCESS");
        transaction.setCreatedAt(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }
}

