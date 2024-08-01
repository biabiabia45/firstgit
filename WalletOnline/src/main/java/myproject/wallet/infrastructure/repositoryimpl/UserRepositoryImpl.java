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
    public Optional<User> findById(UUID id) {
        return userMapper.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            userMapper.insert(user);
        } else {
            userMapper.update(user);
        }
        return user;
    }

    @Override
    public boolean existsById(UUID id) {
        return userMapper.existsById(id);
    }

    @Override
    public void deleteById(UUID id) {
        userMapper.deleteById(id);
    }
}
