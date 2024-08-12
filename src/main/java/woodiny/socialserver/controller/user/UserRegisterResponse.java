package woodiny.socialserver.controller.user;

import lombok.Getter;
import woodiny.socialserver.model.user.User;

@Getter
public class UserRegisterResponse {
    private final long userId;

    public UserRegisterResponse(long userId) {
        this.userId = userId;
    }
}
