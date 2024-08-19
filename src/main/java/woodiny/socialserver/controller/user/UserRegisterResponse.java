package woodiny.socialserver.controller.user;

import lombok.Getter;
import woodiny.socialserver.model.user.User;

@Getter
public class UserRegisterResponse {
    private final String jwtToken;
    private final long userId;

    public UserRegisterResponse(String jwtToken, long userId) {
        this.jwtToken = jwtToken;
        this.userId = userId;
    }
}
