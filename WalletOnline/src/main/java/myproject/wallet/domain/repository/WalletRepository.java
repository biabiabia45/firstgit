package myproject.wallet.domain.repository;

import myproject.wallet.domain.transaction.valueobject.WalletId;
import myproject.wallet.domain.wallet.entity.Wallet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WalletRepository{
    List<Wallet> findByUserId(UUID userId);

    Optional<Wallet> findById(WalletId walletId);

    Wallet save(Wallet wallet);

    boolean existsById(UUID id);

    void deleteById(UUID id);

    List<Wallet> findAll();
}
