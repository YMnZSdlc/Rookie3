package bookstore;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddress {
    private String street;
    private String city;
    private String country;
    private String zipCode;
}
