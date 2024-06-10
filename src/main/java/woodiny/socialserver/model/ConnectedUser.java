package woodiny.socialserver.model;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ConnectedUser {
    private final Long seq;
    private final Email email;
    private final LocalDateTime grantedAt;

    public ConnectedUser(Long seq, Email email, LocalDateTime grantedAt) {
        this.seq = seq;
        this.email = email;
        this.grantedAt = grantedAt;
    }
}
