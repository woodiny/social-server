package woodiny.socialserver.model.post;

import lombok.Getter;
import woodiny.socialserver.model.user.Email;

@Getter
public class Writer {
    private final Email email;
    private final String name;

    public Writer(Email email) {
        this(email, null);
    }

    public Writer(Email email, String name) {
        this.email = email;
        this.name = name;
    }
}
