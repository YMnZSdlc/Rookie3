package bookstore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserValidationServiceTest {

    @Test
    void shouldPassValidationWithProperUserData(){
        CustomerRegistrationDTO user1 = createUserWithProperData();

        UserValidationService userValidationService = new UserValidationService();
        Map<String,String> errorMap1 =  userValidationService.validateUserData(user1);
        Assertions.assertTrue(errorMap1.isEmpty());

    }

    private CustomerRegistrationDTO  createUserWithProperData() {
        CustomerRegistrationDTO customer1 = new CustomerRegistrationDTO();
        customer1.setFirstName("Adam");
        customer1.setLastName("Sztaba");
        customer1.setZipCode("95-100");
        customer1.setCity("łódź");
        customer1.setStreet("Zielona");
        customer1.setBirthDate("1988-12-23");
        customer1.setPesel("78212121215");
        customer1.setEmail("git@sda.pl");
        customer1.setPassword("abcgdteuajdkjas");
        customer1.setPhone("654896526");
        customer1.setPreferEmails(false);
        return customer1;
    }

}