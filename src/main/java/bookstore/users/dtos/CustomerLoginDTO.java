package bookstore.users.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerLoginDTO {

    private String login;
    private String password;
}
