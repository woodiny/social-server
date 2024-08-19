package woodiny.socialserver.security;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;
import woodiny.socialserver.model.user.Email;

import java.util.Date;

public class Claims {
    @Getter
    private Long userId;
    @Getter
    private Email email;
    @Getter
    private String[] roles;
    private Date issuedAt;
    private Date expiredAt;

    private Claims() {}

    public Claims(Long userId, Email email, String[] roles) {
        this.userId = userId;
        this.email = email;
        this.roles = roles;
    }

    public Claims(DecodedJWT decodedJWT) {
        Claim userId = decodedJWT.getClaim("userId");
        if (!userId.isNull()) {
            this.userId = userId.asLong();
        }

        Claim email = decodedJWT.getClaim("email");
        if (!email.isNull()) {
            this.email = new Email(email.asString());
        }

        Claim roles = decodedJWT.getClaim("roles");
        if (!roles.isNull()) {
            this.roles = roles.asArray(String.class);
        }

        this.issuedAt = decodedJWT.getIssuedAt();
        this.expiredAt = decodedJWT.getExpiresAt();
    }

    public void expire() {
        issuedAt = null;
        expiredAt = null;
    }

    public boolean isExpired() {
        return expiredAt.before(new Date());
    }
}
