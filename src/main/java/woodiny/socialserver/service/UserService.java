package woodiny.socialserver.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import woodiny.socialserver.model.user.ConnectedUser;
import woodiny.socialserver.model.user.Email;
import woodiny.socialserver.model.user.User;
import woodiny.socialserver.repository.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional(readOnly = true)
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findByUserId(Long userId) {
        return userRepository.findBySeq(userId);
    }

    public Optional<User> findByUserEmail(Email email) {
        return userRepository.findByEmail(email);
    }

    public List<ConnectedUser> findAllConnectedUser(Long userId) {
        return userRepository.findAllConnectedUser(userId);
    }

    public List<Long> findConnectedIds(Long userId) {
        return userRepository.findSeqFromAllConnectedUser(userId);
    }

    @Transactional
    public long register(Email email, String passwd) {
        User user = new User(email, passwordEncoder.encode(passwd));
        return userRepository.save(user);
    }

    @Transactional
    public void update(User user) {
        userRepository.update(user);
    }
}
