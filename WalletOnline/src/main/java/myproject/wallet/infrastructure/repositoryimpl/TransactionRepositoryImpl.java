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
    public List<Transaction> findBySourceWalletId(Long sourceWalletId) {
        return transactionMapper.findBySourceWalletId(sourceWalletId);
    }

    @Override
    public List<Transaction> findByTargetWalletId(Long targetWalletId) {
        return transactionMapper.findByTargetWalletId(targetWalletId);
    }

    @Override
    public Optional<Transaction> findByIdAndSourceWalletId(Long transactionId, Long sourceWalletId) {
        return Optional.ofNullable(transactionMapper.findByIdAndSourceWalletId(transactionId, sourceWalletId).orElse(null));
    }

    @Override
    public Optional<Transaction> findByIdAndTargetWalletId(Long transactionId, Long targetWalletId) {
        return Optional.ofNullable(transactionMapper.findByIdAndTargetWalletId(transactionId, targetWalletId).orElse(null));
    }

    @Override
    public boolean existsByIdAndSourceWalletId(Long transactionId, Long sourceWalletId) {
        return transactionMapper.existsByIdAndSourceWalletId(transactionId, sourceWalletId);
    }

    @Override
    public boolean existsByIdAndTargetWalletId(Long transactionId, Long targetWalletId) {
        return transactionMapper.existsByIdAndTargetWalletId(transactionId, targetWalletId);
    }

    @Override
    public void save(Transaction transaction) {
        transactionMapper.save(transaction);
    }
}
