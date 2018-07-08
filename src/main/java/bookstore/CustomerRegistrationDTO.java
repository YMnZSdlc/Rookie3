package bookstore;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRegistrationDTO {

    private UserAddress userAddress;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String pesel;
    private String email;
    private String password;
    private String phone;
    private boolean preferEmails;
}
