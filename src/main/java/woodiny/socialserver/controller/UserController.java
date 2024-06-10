package woodiny.socialserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import woodiny.socialserver.dto.UserRegisterRequest;
import woodiny.socialserver.dto.UserRegisterResponse;
import woodiny.socialserver.model.user.User;
import woodiny.socialserver.service.UserService;

import java.util.List;

@Slf4j
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/api/users/{userId}")
    public User getUser(@PathVariable("userId") Long userId) {
        return userService.getUser(userId);
    }

    @PostMapping("/api/users/join")
    public UserRegisterResponse register(@RequestBody UserRegisterRequest request) {
        userService.register(request);
        return new UserRegisterResponse(true, "가입완료");
    }
}
