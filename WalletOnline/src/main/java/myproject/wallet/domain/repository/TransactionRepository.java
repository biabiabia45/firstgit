package myproject.wallet.domain.repository;

import myproject.wallet.domain.transaction.entity.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository{
    List<Transaction> findBySourceWalletId(UUID sourceWalletId);

    List<Transaction> findByTargetWalletId(UUID targetWalletId);

    Optional<Transaction> findByIdAndSourceWalletId(UUID transactionId, UUID sourceWalletId);

    Optional<Transaction> findByIdAndTargetWalletId(UUID transactionId, UUID targetWalletId);

    boolean existsByIdAndSourceWalletId(UUID transactionId, UUID sourceWalletId);

    boolean existsByIdAndTargetWalletId(UUID transactionId, UUID targetWalletId);
}
