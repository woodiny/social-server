package woodiny.socialserver.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import woodiny.socialserver.model.user.Email;
import woodiny.socialserver.model.user.User;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@Transactional
@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void findByUserId() {
        Email email = new Email("tester1@gmail.com");
        String passwd = "1234";

        long userId = userService.register(email, passwd);
        Optional<User> findUser = userService.findByUserId(userId);

        assertTrue(findUser.isPresent());
        assertThat(userId).isEqualTo(findUser.get().getSeq());
    }

    @Test
    public void findByUserEmail() {
        Email email = new Email("tester1@gmail.com");
        String passwd = "1234";

        long userId = userService.register(email, passwd);
        Optional<User> findUser = userService.findByUserEmail(email);

        assertTrue(findUser.isPresent());
        assertThat(userId).isEqualTo(findUser.get().getSeq());
    }

    @Test
    public void registerNewUser_mustReturnValidUserId() {
        Email email = new Email("tester1@gmail.com");
        String passwd = "1234";

        long userId = userService.register(email, passwd);
        Optional<User> findUser = userService.findByUserId(userId);

        assertTrue(findUser.isPresent());
        assertThat(userId).isGreaterThan(0);
        assertThat(userId).isEqualTo(findUser.get().getSeq());
    }

    @Test
    public void updateEmail() {
        Email email = new Email("tester1@gmail.com");
        String passwd = "1234";
        long userId = userService.register(email, passwd);
        User findUser = userService.findByUserId(userId).get();
        
        Email newEmail = new Email("newTester1@gmail.com");

        User user = new User(
                findUser.getSeq(),
                newEmail,
                findUser.getPasswd(),
                findUser.getLoginCount(),
                findUser.getLastLoginAt(),
                findUser.getCreateAt());
        userService.update(user);

        User updatedUser = userService.findByUserId(userId).get();

        assertThat(updatedUser).isEqualTo(findUser);
        assertThat(updatedUser.getEmail().getAddress())
                .isEqualTo(newEmail.getAddress());
        assertThat(updatedUser.getPasswd())
                .isEqualTo(findUser.getPasswd());
        assertThat(updatedUser.getLoginCount())
                .isEqualTo(findUser.getLoginCount());
        assertThat(updatedUser.getLastLoginAt())
                .isEqualTo(findUser.getLastLoginAt());
        assertThat(updatedUser.getCreateAt())
                .isEqualTo(findUser.getCreateAt());
    }

    @Test
    public void updatePassword() {
        Email email = new Email("tester1@gmail.com");
        String passwd = "1234";
        long userId = userService.register(email, passwd);
        User findUser = userService.findByUserId(userId).get();

        String newPasswd = "5678";

        User user = new User(
                findUser.getSeq(),
                findUser.getEmail(),
                newPasswd,
                findUser.getLoginCount(),
                findUser.getLastLoginAt(),
                findUser.getCreateAt());
        userService.update(user);

        User updatedUser = userService.findByUserId(userId).get();

        assertThat(updatedUser).isEqualTo(findUser);
        assertThat(updatedUser.getEmail().getAddress())
                .isEqualTo(findUser.getEmail().getAddress());
        assertThat(updatedUser.getPasswd())
                .isEqualTo(newPasswd);
        assertThat(updatedUser.getLoginCount())
                .isEqualTo(findUser.getLoginCount());
        assertThat(updatedUser.getLastLoginAt())
                .isEqualTo(findUser.getLastLoginAt());
        assertThat(updatedUser.getCreateAt())
                .isEqualTo(findUser.getCreateAt());
    }
}
