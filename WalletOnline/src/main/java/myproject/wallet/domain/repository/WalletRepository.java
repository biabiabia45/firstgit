package myproject.wallet.domain.repository;

import myproject.wallet.domain.wallet.entity.Wallet;

import java.util.List;
import java.util.Optional;

public interface WalletRepository{
    List<Wallet> findByUserId(Long userId);

    Optional<Wallet> findById(Long walletId);

    Wallet save(Wallet wallet);

    boolean existsById(Long id);

    void deleteById(Long id);

    List<Wallet> findAll();
}
