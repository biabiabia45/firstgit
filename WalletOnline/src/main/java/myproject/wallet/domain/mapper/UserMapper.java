package myproject.wallet.domain.mapper;

import myproject.wallet.domain.user.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE id = #{id}")
    Optional<User> findById(@Param("id") UUID id);

    @Select("SELECT * FROM users")
    List<User> findAll();

    @Insert("INSERT INTO users (id, username, email, password, address) VALUES (#{id}, #{username}, #{email}, #{password}, #{address})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Update("UPDATE users SET username=#{username}, email=#{email}, password=#{password}, address=#{address} WHERE id=#{id}")
    void update(User user);

    @Select("SELECT EXISTS(SELECT 1 FROM users WHERE id = #{id})")
    boolean existsById(@Param("id") UUID id);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void deleteById(@Param("id") UUID id);
}
