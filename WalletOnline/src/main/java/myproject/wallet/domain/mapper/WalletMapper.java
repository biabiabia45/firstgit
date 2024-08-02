package myproject.wallet.domain.mapper;

import myproject.wallet.domain.wallet.entity.Wallet;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
public interface WalletMapper {

    @Select("SELECT * FROM wallet WHERE user_id = #{userId}")
    List<Wallet> findByUserId(Long userId);

    @Select("SELECT * FROM wallet WHERE id = #{walletId}")
    Optional<Wallet> findById(Long walletId);

    @Insert("INSERT INTO wallet (id, user_id, balance) VALUES (#{id}, #{userId}, #{balance})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Wallet wallet);

    @Select("SELECT COUNT(*) > 0 FROM wallet WHERE id = #{id}")
    boolean existsById(Long id);

    @Delete("DELETE FROM wallet WHERE id = #{id}")
    void deleteById(Long id);

    @Select("SELECT * FROM wallet")
    List<Wallet> findAll();
}
