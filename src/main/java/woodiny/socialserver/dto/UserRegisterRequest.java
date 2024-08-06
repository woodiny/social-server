package woodiny.socialserver.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserRegisterRequest {
    @NotBlank(message = "Email must be provided.")
    @Email(message = "Not valid email.")
    private final String principal;

    @NotBlank(message = "Password must be provided.")
    private final String credentials;

    public UserRegisterRequest(String principal, String credentials) {
        this.principal = principal;
        this.credentials = credentials;
    }
}
