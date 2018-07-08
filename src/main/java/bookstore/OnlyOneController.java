package bookstore;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller //singletone
public class OnlyOneController {

    private final UserRegistrationService userRegistrationService = new UserRegistrationService();


    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        return "index";
    }

    @GetMapping("/cats")
    public String cats(Map<String, Object> model, @RequestParam(required = false) String searchText) {
        SearchCategoriesService searchCategoriesService = new SearchCategoriesService();
        List<AdminCategoryDTO> categoryDtoList = searchCategoriesService.filterCategories(searchText);//tu trzeba przekazać opracowaną listę
        model.put("catsdata", categoryDtoList);
        return "cats";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(Map<String, Object> model) {
        model.put("form", new CustomerRegistrationDTO()); // pusty CustomerRegistrationDTO do przechowywania danych z formularza rejestracji
        // dodatkowo z CustomerRegistrationDto wyciagnijcie pola [street, city, country, zipCode
        // do osbnej klasy UserAddress tak by sie wszystko kompilowalo i przechodzily testy - nalezy je poprawic po zmianie
        model.put("countries", Arrays.asList(Countries.values())); // kolekcja krajów - enum Countries (POLSKA,NIEMCY,ROSJA) z polami symbol plName
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerEffect(@ModelAttribute CustomerRegistrationDTO customerRegistrationDto, Map<String, Object> model) {
//        CustomerRegistrationDTO registrationdto = (CustomerRegistrationDTO) model.get("customerRegistrationDto");
        Map<String, String> validationErrorsMap = new UserValidationService().validateUserData(customerRegistrationDto); // tu nalezy wywolac serwis do walidacji danych uzytkownika
        model.put("form", customerRegistrationDto);
        model.put("countries", Arrays.asList(Countries.values())); // kolekcja krajów (w to miejsce wstawcie kolekcje) - enum Countries (POLSKA,NIEMCY,ROSJA) z polami symbol plName

        if (!validationErrorsMap.isEmpty()) { //tu zamiast true powinno sie znaleźć sprawdzenie czy walidacja danych sie powiodla (pusta mapa) - najpierw sytuacja kiedy sie nie powiodla
            model.putAll(validationErrorsMap);
            return "registerForm";
        } else { // tu wpadamy jeśli validacja jest OK
            try {
                userRegistrationService.registerUser(customerRegistrationDto);
                // tu nalezy zarejestrowac uzytkownika przez serwis UserRegistrationService
            } catch (UserExistsException e) {
                model.put("userExistsException", "Użytkownik istnieje !!!"); //todo tu wstawcie odpowiedni komunikat "Uzytkownik isnieje" np
                return "registerForm";
            }
            return "registerEffect";
        }
    }
}
