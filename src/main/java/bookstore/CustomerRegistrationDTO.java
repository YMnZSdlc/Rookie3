package bookstore;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRegistrationDTO {

    private String firstName;
    private String lastName;
    private String zipCode;
    private String city;
    private String country;
    private String street;
    private String birthDate;
    private String pesel;
    private String email;
    private String password;
    private String phone;
    private boolean preferEmails;


}
