package woodiny.socialserver.controller.user;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woodiny.socialserver.model.user.Email;
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

    @PostMapping("/api/user/join")
    public UserRegisterResponse register(@Valid @RequestBody UserRegisterRequest request) {
        userService.register(
                new Email(request.getPrincipal()),
                request.getCredentials()
        );
        return new UserRegisterResponse(true, "가입완료");
    }
}
