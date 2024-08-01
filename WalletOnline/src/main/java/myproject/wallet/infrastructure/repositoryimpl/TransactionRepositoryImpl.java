package myproject.wallet.infrastructure.repositoryimpl;

import myproject.wallet.domain.mapper.TransactionMapper;
import myproject.wallet.domain.repository.TransactionRepository;
import myproject.wallet.domain.transaction.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    private final TransactionMapper transactionMapper;

    @Autowired
    public TransactionRepositoryImpl(TransactionMapper transactionMapper) {
        this.transactionMapper = transactionMapper;
    }

    @Override
    public List<Transaction> findBySourceWalletId(UUID sourceWalletId) {
        return transactionMapper.findBySourceWalletId(sourceWalletId);
    }

    @Override
    public List<Transaction> findByTargetWalletId(UUID targetWalletId) {
        return transactionMapper.findByTargetWalletId(targetWalletId);
    }

    @Override
    public Optional<Transaction> findByIdAndSourceWalletId(UUID transactionId, UUID sourceWalletId) {
        return Optional.ofNullable(transactionMapper.findByIdAndSourceWalletId(transactionId, sourceWalletId).orElse(null));
    }

    @Override
    public Optional<Transaction> findByIdAndTargetWalletId(UUID transactionId, UUID targetWalletId) {
        return Optional.ofNullable(transactionMapper.findByIdAndTargetWalletId(transactionId, targetWalletId).orElse(null));
    }

    @Override
    public boolean existsByIdAndSourceWalletId(UUID transactionId, UUID sourceWalletId) {
        return transactionMapper.existsByIdAndSourceWalletId(transactionId, sourceWalletId);
    }

    @Override
    public boolean existsByIdAndTargetWalletId(UUID transactionId, UUID targetWalletId) {
        return transactionMapper.existsByIdAndTargetWalletId(transactionId, targetWalletId);
    }

    @Override
    public void save(Transaction transaction) {
        transactionMapper.save(transaction);
    }
}
