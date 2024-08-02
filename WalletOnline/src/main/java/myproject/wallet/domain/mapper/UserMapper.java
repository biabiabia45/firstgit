package myproject.wallet.domain.mapper;

import myproject.wallet.domain.user.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    @Select("SELECT id, username, email, password, address FROM user WHERE id = #{id}")
    Optional<User> findUserById(@Param("id") Long id);

    @Select("SELECT id, username, email, password, address FROM user")
    List<User> findAllUser();

    @Insert("INSERT INTO user (username, email, password, address) VALUES (#{username}, #{email}, #{password}, #{address})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(User user);

    @Update("UPDATE user SET username=#{username}, email=#{email}, password=#{password}, address=#{address} WHERE id=#{id}")
    void updateUser(User user);

    @Select("SELECT EXISTS(SELECT 1 FROM user WHERE id = #{id})")
    boolean existsUserById(@Param("id") Long id);

    @Delete("DELETE FROM user WHERE id = #{id}")
    void deleteUserById(@Param("id") Long id);
}
