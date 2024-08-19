package woodiny.socialserver.security;

import lombok.ToString;
import woodiny.socialserver.model.user.Email;

@ToString
public class UserAuthentication {
    private final Long userId;
    private final Email email;

    public UserAuthentication(Long userId, Email email) {
        this.userId = userId;
        this.email = email;
    }
}
