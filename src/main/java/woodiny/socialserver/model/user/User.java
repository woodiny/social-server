package woodiny.socialserver.model.user;

import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

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
        this.createAt = ensureCreateAt(createAt);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(seq);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return Objects.equals(seq, user.seq);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("seq=" + seq)
                .add("email=" + email)
                .add("loginCount=" + loginCount)
                .add("lastLoginAt=" + lastLoginAt)
                .add("createAt" + createAt)
                .toString();
    }

    private LocalDateTime ensureCreateAt(LocalDateTime localDateTime) {
        return localDateTime == null ? LocalDateTime.now() : null;
    }
}
