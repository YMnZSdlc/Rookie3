package bookstore;

import bookstore.users.dtos.CustomerRegistrationDTO;
import bookstore.users.entities.UserAddress;
import bookstore.users.services.UserValidationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;


class UserValidationServiceTest {

    @Test
    void shouldPassValidationWithProperUserData() {
        //given
        CustomerRegistrationDTO user = createUserWithProperData();
        UserValidationService userValidationService = new UserValidationService();
        //when
        Map<String, String> errorsMap = userValidationService.validateUserData(user);
        //then
        Assertions.assertTrue(errorsMap.isEmpty());
    }

    @Test
    void shouldNotPassValidationWithWrongBirthDate() {
        //given
        CustomerRegistrationDTO user = createUserWithProperData();
        user.setBirthDate("1809-12-12");
        UserValidationService userValidationService = new UserValidationService();
        //when
        Map<String, String> errorsMap = userValidationService.validateUserData(user);
        //then
        Assertions.assertTrue(errorsMap.containsKey(UserValidationService.BIRTH_DATA_VAL_RES));
    }

    @Test
    void shouldNotPassValidationWithNullValues() {
        //given
        CustomerRegistrationDTO customer = new CustomerRegistrationDTO();
        UserValidationService userValidationService = new UserValidationService();
        //when
        Map<String, String> errorsMap = userValidationService.validateUserData(customer);
        //then
        Assertions.assertTrue(!errorsMap.isEmpty());
    }

    @Test
    void shouldPassValidationWithWhiteSpaces() {
        //given
        CustomerRegistrationDTO customer = createUserWithDataWithWhiteSpaces();
        UserValidationService userValidationService = new UserValidationService();
        //when
        Map<String, String> errorsMap = userValidationService.validateUserData(customer);
        //then
        Assertions.assertTrue(errorsMap.isEmpty());
    }

    @Test
    void shouldNotPassValidationWithBrokenPhone() {
        //given
        CustomerRegistrationDTO customer = createUserWithDataWithWhiteSpaces();
        customer.setPhone("876543jh5");
        UserValidationService userValidationService = new UserValidationService();
        //when
        Map<String, String> errorsMap= userValidationService.validateUserData(customer);
        //then
        Assertions.assertTrue(errorsMap.containsKey(UserValidationService.PHONE_VAL_RES));
    }

    private CustomerRegistrationDTO createUserWithProperData() {
        CustomerRegistrationDTO customer = new CustomerRegistrationDTO();
        customer.setFirstName("Krzysztof");
        customer.setLastName("Adfsfds");
        customer.setUserAddress(new UserAddress());//tworzenie obiektu klasy UserAddress w obiekcie CustomerRegistrationDTO
        UserAddress ua = customer.getUserAddress(); //przykład z wyciągniętą zmienną
        ua.setZipCode("87-123");
        customer.getUserAddress().setCity("łódź");
        customer.getUserAddress().setCountry("Poland");
        customer.getUserAddress().setStreet("Zielona");
        customer.setBirthDate("1998-10-13");
        customer.setPesel("78945612321");
        customer.setEmail("sdafadsgf@wp.pl");
        customer.setPassword("assdddsfssdfg");
        customer.setPhone("789456123");
        customer.setPreferEmails(false);
        return customer;
    }

    private CustomerRegistrationDTO createUserWithDataWithWhiteSpaces() {
        CustomerRegistrationDTO customer = new CustomerRegistrationDTO();
        customer.setFirstName(" Krzysztof ");
        customer.setLastName(" Adfsfds ");
        customer.setUserAddress(new UserAddress());
        customer.getUserAddress().setZipCode(" 87-123");
        customer.getUserAddress().setCity(" łódź ");
        customer.getUserAddress().setCountry(" Poland ");
        customer.getUserAddress().setStreet(" Zielona ");
        customer.setBirthDate(" 1998-10-13");
        customer.setPesel(" 78945612321 ");
        customer.setEmail(" sdafadsgf@wp.pl ");
        customer.setPassword(" assdddsfssdfg ");
        customer.setPhone(" 789456123 ");
        customer.setPreferEmails(false);
        return customer;
    }

}