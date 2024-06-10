package woodiny.socialserver.dto;

import lombok.Getter;
import woodiny.socialserver.model.user.Email;

@Getter
public class UserRegisterRequest {
    private final Email principal;
    private final String credentials;

    public UserRegisterRequest(Email principal, String credentials) {
        this.principal = principal;
        this.credentials = credentials;
    }
}
