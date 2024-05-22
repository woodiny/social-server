package woodiny.socialserver.dto;

import lombok.Getter;

@Getter
public class UserRegisterRequest {
    private final String principal;
    private final String credentials;

    public UserRegisterRequest(String principal, String credentials) {
        this.principal = principal;
        this.credentials = credentials;
    }
}
