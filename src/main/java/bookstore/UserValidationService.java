package bookstore;

import com.google.common.collect.Maps;
import org.junit.platform.commons.util.StringUtils;

import java.util.Map;

public class UserValidationService {

    public Map<String,String> validateUserData (CustomerRegistrationDTO dto){
        Map<String,String> errorsResult = Maps.newHashMap();

        if (dto.getFirstName().length()<3) {
            errorsResult.put("firstNameValRes","Imię jest wymagane. Przynajmniej 3 znaki.");
        }
        if (dto.getLastName().length()<3){
            errorsResult.put("lastNameValRes","Nazwisko jest wymagane. Przynajmniej 3 znaki.");
        }
        if (!dto.getZipCode().matches("^[0-9]{2}-[0-9]{3}$")){
            errorsResult.put("zipCodeValRes","Zły format. Kod pocztowy powinien mieć format 12-345");
        }
        if (StringUtils.isBlank(dto.getCity())){
            errorsResult.put("cityValResult", "Podanie nazwy miasta jest wymagane.");
        }
        if (StringUtils.isBlank(dto.getCountry())){
            errorsResult.put("countryValResult", "Podanie nazwy kraju jest wymagane.");
        }
        if (StringUtils.isBlank(dto.getStreet())){
            errorsResult.put("streetValResult", "Podanie nazwy ulicy jest wymagane.");
        }
        if (!dto.getBirthDate().matches("^([1][9][1-9]{2}|[2][0][0-1][0-9])-([0][1-9]|[1][1-2])-([0][1-9]|[12][0-9]|[3][0-1])$")){
            errorsResult.put("birthDateValRes","Zły format. Data urodzin powinna być podana w formacie RRRR-MM-DD");
        }
        if (!dto.getPesel().matches("^[0-9]{11}")){
            errorsResult.put("peselValRes", "Numer PESEL powienin skłądaćsięz 11 cyfr. ");
        }
        if (!dto.getEmail().matches("^\\w+@\\w+\\.\\w+")){
            errorsResult.put("emailValRes", "zły format adresu e-mail");
        }
        if (dto.getPassword().length()<10 || dto.getPassword().length()>20){
            errorsResult.put("passwordValRes", "Hasło jest wymagane. Musi zawierać od 10 do 20 znaków.");
        }
        if (dto.getPhone().length() != 9){
            errorsResult.put("phoneValRes", "zły format. Numer telefonu powienin skłądć się z 9 cyfr.");
        }
        return errorsResult;
    }
}
