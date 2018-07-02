package bookstore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserValidationServiceTest {

    @Test
    void shouldPassValidationWithProperUserData() {
        CustomerRegistrationDTO user = createUserWithProperData();
        UserValidationService userValidationService = new UserValidationService();
        Map<String, String> errorsMap = userValidationService.validateUserData(user);
        Assertions.assertTrue(errorsMap.isEmpty());
    }

    @Test
    void shouldNotPassValidationWithWrongBirthDate() {
        CustomerRegistrationDTO user = createUserWithProperData();
        user.setBirthDate("12341212");
        UserValidationService userValidationService = new UserValidationService();
        Map<String, String> errorsMap = userValidationService.validateUserData(user);

        Assertions.assertTrue(
                errorsMap.containsKey(UserValidationService.BIRTH_DATA_VAL_RES));
    }
    @Test
    void shouldNotPassValidationWithNullValues() {
        CustomerRegistrationDTO customer = new CustomerRegistrationDTO();
        UserValidationService userValidationService = new UserValidationService();
        Map<String, String> errorsMap = userValidationService.validateUserData(customer);

        Assertions.assertTrue(!errorsMap.isEmpty());
    }


    private CustomerRegistrationDTO createUserWithProperData() {
        CustomerRegistrationDTO customer = new CustomerRegistrationDTO();
        customer.setFirstName("Krzysztof");
        customer.setLastName("Adfsfds");
        customer.setZipCode("87-123");
        customer.setCity("łódź");
        customer.setCountry("Poland");
        customer.setStreet("Zielona");
        customer.setBirthDate("1998-10-13");
        customer.setPesel("78945612321");
        customer.setEmail("sdafadsgf@wp.pl");
        customer.setPassword("assdddsfssdfg");
        customer.setPhone("789456123");
        customer.setPreferEmails(false);
        return customer;
    }

}