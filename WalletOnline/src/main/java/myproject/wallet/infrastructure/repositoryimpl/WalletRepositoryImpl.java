package myproject.wallet.infrastructure.repositoryimpl;

import myproject.wallet.domain.mapper.WalletMapper;
import myproject.wallet.domain.repository.WalletRepository;
import myproject.wallet.domain.wallet.entity.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class WalletRepositoryImpl implements WalletRepository {

    private final WalletMapper walletMapper;

    @Autowired
    public WalletRepositoryImpl(WalletMapper walletMapper) {
        this.walletMapper = walletMapper;
    }

    @Override
    public List<Wallet> findByUserId(Long userId) {
        return walletMapper.findByUserId(userId);
    }

    @Override
    public Optional<Wallet> findById(Long walletId) {
        return walletMapper.findById(walletId);
    }

    @Override
    public Wallet save(Wallet wallet) {
        walletMapper.save(wallet);
        return wallet;
    }

    @Override
    public boolean existsById(Long id) {
        return walletMapper.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        walletMapper.deleteById(id);
    }

    @Override
    public List<Wallet> findAll() {
        return walletMapper.findAll();
    }
}
