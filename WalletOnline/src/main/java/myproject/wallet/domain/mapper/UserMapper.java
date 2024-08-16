package myproject.wallet.domain.mapper;

import myproject.wallet.domain.user.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    @Select("SELECT id, username, email, password, address FROM user")
    List<User> findAllUser();

    @Select("SELECT id, username, email, password, address FROM user WHERE username = #{username} ")
    Optional<User> findByUsername(String username);

    @Select("SELECT id, username, email, password, address FROM user WHERE email = #{email} ")
    Optional<User> findByEmail(String email);

    @Insert("INSERT INTO user (username, password, email, address) VALUES (#{username}, #{password}, #{contactInfo.email}, #{contactInfo.address})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(User user);

    @Update("UPDATE user SET email=#{contactInfo.email}, address=#{contactInfo.address} WHERE username=#{username}")
    void updateUser(User user);

    @Update("UPDATE user SET password = #{password} WHERE username=#{username}")
    void changePassword(String username, String password);

    @Delete("DELETE FROM user WHERE username = #{username}")
    void deleteUserByUserName(String username);

    @Select("SELECT EXISTS(SELECT 1 FROM user WHERE id = #{id})")
    boolean existsUserById(@Param("id") Long id);

    @Select("SELECT EXISTS(SELECT 1 FROM user WHERE username=#{username})")
    boolean existsUserByUserName(String username);

}
