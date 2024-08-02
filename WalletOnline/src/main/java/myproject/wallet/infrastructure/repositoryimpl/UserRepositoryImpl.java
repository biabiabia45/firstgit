package myproject.wallet.infrastructure.repositoryimpl;

import myproject.wallet.domain.mapper.UserMapper;
import myproject.wallet.domain.repository.UserRepository;
import myproject.wallet.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserMapper userMapper;

    @Autowired
    public UserRepositoryImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userMapper.findUserById(id);
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
    public void deleteById(Long id) {
        userMapper.deleteUserById(id);
    }
}
