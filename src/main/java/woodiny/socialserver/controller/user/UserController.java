package woodiny.socialserver.controller.user;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woodiny.socialserver.controller.ApiResponse;
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

    @PostMapping("/api/user/join")
    public ApiResponse<UserRegisterResponse> register(@Valid @RequestBody UserRegisterRequest request) {
        long userId = userService.register(
                new Email(request.getPrincipal()),
                request.getCredentials()
        );

        return ApiResponse.OK(
                new UserRegisterResponse(userId),
                "user successfully registered."
        );
    }
}
