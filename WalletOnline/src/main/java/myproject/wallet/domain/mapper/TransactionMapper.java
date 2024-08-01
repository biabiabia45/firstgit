package myproject.wallet.domain.mapper;

import myproject.wallet.domain.transaction.entity.Transaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
public interface TransactionMapper {

    List<Transaction> findBySourceWalletId(@Param("sourceWalletId") UUID sourceWalletId);

    List<Transaction> findByTargetWalletId(@Param("targetWalletId") UUID targetWalletId);

    Optional<Transaction> findByIdAndSourceWalletId(@Param("transactionId") UUID transactionId,
                                                    @Param("sourceWalletId") UUID sourceWalletId);

    Optional<Transaction> findByIdAndTargetWalletId(@Param("transactionId") UUID transactionId,
                                                    @Param("targetWalletId") UUID targetWalletId);

    boolean existsByIdAndSourceWalletId(@Param("transactionId") UUID transactionId,
                                        @Param("sourceWalletId") UUID sourceWalletId);

    boolean existsByIdAndTargetWalletId(@Param("transactionId") UUID transactionId,
                                        @Param("targetWalletId") UUID targetWalletId);

    void save(Transaction transaction);
}
