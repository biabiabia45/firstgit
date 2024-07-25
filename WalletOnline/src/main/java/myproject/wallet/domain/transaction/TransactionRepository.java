package myproject.wallet.domain.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findByWalletId(UUID walletId);

    Optional<Transaction> findByIdAndWalletId(UUID transactionId, UUID walletId);

    boolean existsByIdAndWalletId(UUID transactionId, UUID walletId);
}
