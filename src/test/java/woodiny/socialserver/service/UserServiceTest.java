package woodiny.socialserver.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import woodiny.socialserver.model.user.Email;

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

        Assertions.assertThat(userId).isGreaterThan(0);
    }
}