package woodiny.socialserver.model.user;

import lombok.Getter;

@Getter
public class Email {
    private final String address;

    public Email(String address) {
        this.address = address;
    }
}
