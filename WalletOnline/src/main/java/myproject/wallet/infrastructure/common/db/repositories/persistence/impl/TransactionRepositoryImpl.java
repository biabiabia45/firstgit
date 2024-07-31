package myproject.wallet.infrastructure.common.db.repositories.persistence.impl;

import myproject.wallet.domain.entities.Transaction;
import myproject.wallet.domain.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TransactionRepositoryImpl implements TransactionRepository {
    @Override
    public List<Transaction> findBySourceWalletId(UUID sourceWalletId) {
        return null;
    }

    @Override
    public List<Transaction> findByTargetWalletId(UUID targetWalletId) {
        return null;
    }

    @Override
    public Optional<Transaction> findByIdAndSourceWalletId(UUID transactionId, UUID sourceWalletId) {
        return Optional.empty();
    }

    @Override
    public Optional<Transaction> findByIdAndTargetWalletId(UUID transactionId, UUID targetWalletId) {
        return Optional.empty();
    }

    @Override
    public boolean existsByIdAndSourceWalletId(UUID transactionId, UUID sourceWalletId) {
        return false;
    }

    @Override
    public boolean existsByIdAndTargetWalletId(UUID transactionId, UUID targetWalletId) {
        return false;
    }
}
