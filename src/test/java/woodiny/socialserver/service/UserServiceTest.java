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

@Slf4j
@Transactional
@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

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
}