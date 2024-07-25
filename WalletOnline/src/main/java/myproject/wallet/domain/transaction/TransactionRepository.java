package myproject.wallet.domain.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findBySourceWalletId(UUID sourceWalletId);

    List<Transaction> findByTargetWalletId(UUID targetWalletId);

    Optional<Transaction> findByIdAndSourceWalletId(UUID transactionId, UUID sourceWalletId);

    Optional<Transaction> findByIdAndTargetWalletId(UUID transactionId, UUID targetWalletId);

    boolean existsByIdAndSourceWalletId(UUID transactionId, UUID sourceWalletId);

    boolean existsByIdAndTargetWalletId(UUID transactionId, UUID targetWalletId);
}
