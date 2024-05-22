package woodiny.socialserver.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import woodiny.socialserver.dto.UserRegisterRequest;
import woodiny.socialserver.dto.UserRegisterResponse;
import woodiny.socialserver.model.User;
import woodiny.socialserver.repository.UserRepository;

import java.util.List;

@Slf4j
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

    public void register(UserRegisterRequest request) {
        userRepository.save(request);
    }
}
