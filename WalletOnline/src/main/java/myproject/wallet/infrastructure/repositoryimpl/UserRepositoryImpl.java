package myproject.wallet.infrastructure.repositoryimpl;

import myproject.wallet.domain.mapper.UserMapper;
import myproject.wallet.domain.repository.UserRepository;
import myproject.wallet.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserMapper userMapper;

    @Autowired
    public UserRepositoryImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAllUser();
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            userMapper.insertUser(user);
        } else {
            userMapper.updateUser(user);
        }
        return user;
    }

    @Override
    public boolean existsById(Long id) {
        return userMapper.existsUserById(id);
    }

    @Override
    public boolean existsByUserName(String username) {
        return userMapper.existsUserByUserName(username);
    }

    @Override
    public void deleteByUsername(String username) {
        userMapper.deleteUserByUserName(username);
    }

    @Override
    public void changePassword(String username, String newPassword) {
        userMapper.changePassword(username, newPassword);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userMapper.findByEmail(email);
    }
}
