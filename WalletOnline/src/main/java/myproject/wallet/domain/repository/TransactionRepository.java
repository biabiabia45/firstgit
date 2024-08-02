package myproject.wallet.domain.repository;

import myproject.wallet.domain.transaction.entity.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository{
    List<Transaction> findBySourceWalletId(Long sourceWalletId);

    List<Transaction> findByTargetWalletId(Long targetWalletId);

    Optional<Transaction> findByIdAndSourceWalletId(Long transactionId, Long sourceWalletId);

    Optional<Transaction> findByIdAndTargetWalletId(Long transactionId, Long targetWalletId);

    boolean existsByIdAndSourceWalletId(Long transactionId, Long sourceWalletId);

    boolean existsByIdAndTargetWalletId(Long transactionId, Long targetWalletId);

    void save(Transaction transaction);
}
