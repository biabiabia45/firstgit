package myproject.wallet.infrastructure.common.db.repositories.persistence.impl;

import myproject.wallet.domain.entities.Wallet;
import myproject.wallet.domain.repository.WalletRepository;

import java.util.List;
import java.util.UUID;

public class WalletRepositoryImpl implements WalletRepository {
    @Override
    public List<Wallet> findByUserId(UUID userId) {
        return null;
    }
}
