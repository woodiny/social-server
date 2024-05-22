package woodiny.socialserver.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class User {
    private Long seq;
    private String email;
    private String passwd;
    private int loginCount;
    private LocalDateTime lastLoginAt;
    private LocalDateTime createAt;

    public User(Long seq, String email, String passwd, int loginCount, LocalDateTime lastLoginAt, LocalDateTime createAt) {
        this.seq = seq;
        this.email = email;
        this.passwd = passwd;
        this.loginCount = loginCount;
        this.lastLoginAt = lastLoginAt;
        this.createAt = createAt;
    }
}
