package woodiny.socialserver.controller.user;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import woodiny.socialserver.controller.ApiResponse;
import woodiny.socialserver.model.user.Email;
import woodiny.socialserver.model.user.Role;
import woodiny.socialserver.model.user.User;
import woodiny.socialserver.security.Claims;
import woodiny.socialserver.security.Jwt;
import woodiny.socialserver.service.UserService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class UserController {
    private final Jwt jwt;
    private final UserService userService;

    public UserController(Jwt jwt, UserService userService) {
        this.jwt = jwt;
        this.userService = userService;
    }

    @PostMapping("/api/user/join")
    public ApiResponse<UserRegisterResponse> register(@Valid @RequestBody UserRegisterRequest request) {
        Email email = new Email(request.getPrincipal());
        long userId = userService.register(
                email,
                request.getCredentials()
        );

        String jwtToken = jwt.generate(new Claims(userId, email, new String[]{Role.USER.getValue()}));

        return ApiResponse.OK(
                new UserRegisterResponse(jwtToken, userId),
                "user successfully registered."
        );
    }
}
