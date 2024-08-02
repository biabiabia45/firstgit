package myproject.wallet.domain.mapper;

import myproject.wallet.domain.transaction.entity.Transaction;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TransactionMapper {
    @Select("SELECT * FROM transactions WHERE source_wallet_id = #{sourceWalletId}")
    List<Transaction> findBySourceWalletId(@Param("sourceWalletId") Long sourceWalletId);

    @Select("SELECT * FROM transactions WHERE target_wallet_id = #{targetWalletId}")
    List<Transaction> findByTargetWalletId(@Param("targetWalletId") Long targetWalletId);

    @Select("SELECT * FROM transactions WHERE id = #{transactionId} AND source_wallet_id = #{sourceWalletId}")
    Optional<Transaction> findByIdAndSourceWalletId(@Param("transactionId") Long transactionId, @Param("sourceWalletId") Long sourceWalletId);

    @Select("SELECT * FROM transactions WHERE id = #{transactionId} AND target_wallet_id = #{targetWalletId}")
    Optional<Transaction> findByIdAndTargetWalletId(@Param("transactionId") Long transactionId, @Param("targetWalletId") Long targetWalletId);

    @Select("SELECT COUNT(*) > 0 FROM transactions WHERE id = #{transactionId} AND source_wallet_id = #{sourceWalletId}")
    boolean existsByIdAndSourceWalletId(@Param("transactionId") Long transactionId, @Param("sourceWalletId") Long sourceWalletId);

    @Select("SELECT COUNT(*) > 0 FROM transactions WHERE id = #{transactionId} AND target_wallet_id = #{targetWalletId}")
    boolean existsByIdAndTargetWalletId(@Param("transactionId") Long transactionId, @Param("targetWalletId") Long targetWalletId);

    @Insert("INSERT INTO transactions (id, source_wallet_id, target_wallet_id, amount, timestamp) VALUES (#{id}, #{sourceWalletId}, #{targetWalletId}, #{amount}, #{timestamp})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Transaction transaction);
}
