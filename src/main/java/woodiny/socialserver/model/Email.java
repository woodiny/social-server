package woodiny.socialserver.model;

import lombok.Getter;

@Getter
public class Email {
    private final String address;

    public Email(String address) {
        this.address = address;
    }
}
