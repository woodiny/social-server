package woodiny.socialserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woodiny.socialserver.dto.UserRegisterRequest;
import woodiny.socialserver.dto.UserRegisterResponse;
import woodiny.socialserver.model.user.User;
import woodiny.socialserver.service.UserService;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<User> getUser(@PathVariable("userId") Long userId) {
        Optional<User> user = userService.findByUserId(userId);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/api/users/join")
    public UserRegisterResponse register(@RequestBody UserRegisterRequest request) {
        userService.register(request.getPrincipal(), request.getCredentials());
        return new UserRegisterResponse(true, "가입완료");
    }
}
