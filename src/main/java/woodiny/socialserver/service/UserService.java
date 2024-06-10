package woodiny.socialserver.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woodiny.socialserver.dto.UserRegisterRequest;
import woodiny.socialserver.model.user.Email;
import woodiny.socialserver.model.user.User;
import woodiny.socialserver.repository.UserRepository;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long userId) {
        return userRepository.find(userId);
    }

    @Transactional
    public long register(Email email, String passwd) {
        User user = new User(email, passwd);
        return userRepository.save(user);
    }
}
