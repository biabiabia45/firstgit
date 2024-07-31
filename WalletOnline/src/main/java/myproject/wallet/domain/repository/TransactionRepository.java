package myproject.wallet.domain.repository;

import myproject.wallet.domain.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository {
    List<Transaction> findBySourceWalletId(UUID sourceWalletId);

    List<Transaction> findByTargetWalletId(UUID targetWalletId);

    Optional<Transaction> findByIdAndSourceWalletId(UUID transactionId, UUID sourceWalletId);

    Optional<Transaction> findByIdAndTargetWalletId(UUID transactionId, UUID targetWalletId);

    boolean existsByIdAndSourceWalletId(UUID transactionId, UUID sourceWalletId);

    boolean existsByIdAndTargetWalletId(UUID transactionId, UUID targetWalletId);
}
