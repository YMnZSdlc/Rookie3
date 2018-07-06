package bookstore;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class UserValidationService {

    public static final String FIRST_NAME_VAL_RES = "firstNameValRes";
    public static final String LAST_NAME_VAL_RES = "lastNameValRes";
    public static final String ZIP_CODE_VAL_RES = "zipCodeValRes";
    public static final String CITY_VAL_RES = "cityValRes";
    public static final String COUNTRY_VAL_RES = "countryValRes";
    public static final String STREET_VAL_RES = "streetValRes";
    public static final String BIRTH_DATA_VAL_RES = "birthDataValRes";
    public static final String PESEL_VAL_RES = "peselValRes";
    public static final String EMAIL_VAL_RES = "emailValRes";
    public static final String PASSWORD_VAL_RES = "passwordValRes";
    public static final String PHONE_VAL_RES = "phoneValRes";

    public Map<String, String> validateUserData(CustomerRegistrationDTO dto) {
        Map<String, String> errorsResult = Maps.newHashMap();

        if (StringUtils.isBlank(dto.getFirstName()) || dto.getFirstName().length() < 3) {
            errorsResult.put(FIRST_NAME_VAL_RES,
                    "Imię jest wymagane. Przynajmniej 3 znaki.");
        }
        if (StringUtils.defaultIfBlank(dto.getLastName(), "").trim().length() < 3) {
            errorsResult.put(LAST_NAME_VAL_RES,
                    "Nazwisko jest wymagane. Przynajmniej 3 znaki.");
        }
        if (StringUtils.isBlank(dto.getZipCode()) || !dto.getZipCode().matches("^[0-9]{2}-[0-9]{3}$")) {
            errorsResult.put(ZIP_CODE_VAL_RES,
                    "Zły format. Kod pocztowy powinien mieć format 12-345.");
        }
        if (StringUtils.isBlank(dto.getCity())) {
            errorsResult.put(CITY_VAL_RES,
                    "Podanie nazwy miasta jest wymagane.");
        }
        if (StringUtils.isBlank(dto.getCountry())) {
            errorsResult.put(COUNTRY_VAL_RES,
                    "Podanie nazwy kraju jest wymagane.");
        }
        if (StringUtils.isBlank(dto.getStreet())) {
            errorsResult.put(STREET_VAL_RES,
                    "Podanie nazwy ulicy jest wymagane.");
        }
        if (StringUtils.isBlank(dto.getBirthDate()) || !dto.getBirthDate()
                .matches("^([1][9]|[2][0])\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12]\\d|[3][012])$")) {
            errorsResult.put(BIRTH_DATA_VAL_RES,
                    "Zły format. Data urodzin powinna być podana w formacie RRRR-MM-DD");
        }
        if (StringUtils.isBlank(dto.getPesel()) || !dto.getPesel().matches("^[0-9]{11}")) {
            errorsResult.put(PESEL_VAL_RES,
                    "Zły format. Numer PESEL powinien składać się z 11 cyfr.");
        }
        if (StringUtils.isBlank(dto.getEmail()) || !dto.getEmail().matches("^\\w+@\\w+\\.\\w+")) {
            errorsResult.put(EMAIL_VAL_RES,
                    "Zły format adresu email");
        }
        if (StringUtils.defaultIfBlank(dto.getPassword(), "").trim().length() < 10 ||
                dto.getPassword().length() > 20) {
            errorsResult.put(PASSWORD_VAL_RES,
                    "Hasło jest wymagane. Musi zawierać od 10 do 20 znaków.");
        }
        if (!StringUtils.defaultIfBlank(dto.getPhone(), "").trim().matches("^\\d{9}$")) {
            errorsResult.put(PHONE_VAL_RES,
                    "Zły format. Numer telefonu powinien składać się z 9 cyfr.");
        }
        return errorsResult;
    }
}
