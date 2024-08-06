package woodiny.socialserver.controller.user;

import lombok.Getter;

@Getter
public class UserRegisterResponse {
    private final boolean success;
    private final String response;

    public UserRegisterResponse(boolean success, String response) {
        this.success = success;
        this.response = response;
    }
}
