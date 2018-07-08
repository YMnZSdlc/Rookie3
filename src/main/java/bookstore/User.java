package bookstore;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class User implements Serializable {
    private UserAddress userAddress;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String pesel;
    private String email;
    private String passwordHash;
    private String phone;
    private boolean preferEmails;
}
