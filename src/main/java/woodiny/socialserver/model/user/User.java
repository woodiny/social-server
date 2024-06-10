package woodiny.socialserver.model.user;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class User {
    private final Long seq;
    private final Email email;
    private String passwd;
    private int loginCount;
    private LocalDateTime lastLoginAt;
    private final LocalDateTime createAt;

    public User(Email email, String passwd) {
        this(null, email, passwd, 0, null, null);
    }

    public User(Long seq, Email email, String passwd, int loginCount, LocalDateTime lastLoginAt, LocalDateTime createAt) {
        this.seq = seq;
        this.email = email;
        this.passwd = passwd;
        this.loginCount = loginCount;
        this.lastLoginAt = lastLoginAt;
        this.createAt = createAt;
    }
}
