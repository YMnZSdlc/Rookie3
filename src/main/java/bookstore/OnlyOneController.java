package bookstore;

import bookstore.categories.dtos.AdminCategoryDTO;
import bookstore.categories.services.SearchCategoriesService;
import bookstore.users.dtos.CustomerLoginDTO;
import bookstore.users.dtos.CustomerRegistrationDTO;
import bookstore.users.exception.UserExistsException;
import bookstore.users.services.UserLoginService;
import bookstore.users.services.UserRegistrationService;
import bookstore.users.services.UserValidationService;
import bookstore.weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller //singleton
public class OnlyOneController {

    //DEPENDENCY INJECTION - Spring umożliwi nam użycie tej klasy, która jest singletonem
    @Autowired  //Działa tylko dla klas oznaczonych jako @Service/@Component/@Controller/@RestController itd
    private UserLoginService userLoginService;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private SearchCategoriesService searchCategoriesService;


    @Autowired
    private UserRegistrationService userRegistrationService;
    
    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        return "index";
    }

    @GetMapping("/weather")
    @ResponseBody
    public ResponseEntity<String> weather(Map<String, Object> model) {
        try {
            return ResponseEntity.ok(weatherService.getWeather());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Błąd");
        }
    }

    @GetMapping("/cats")
    public String cats(Map<String, Object> model, @RequestParam(required = false) String searchText) {
        List<AdminCategoryDTO> categoryDtoList = searchCategoriesService.filterCategories(searchText);
        model.put("catsdata", categoryDtoList);
        return "cats";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET) //wyświetlanie pustego formularza logowania
    public String loginForm(Map<String, Object> model) {
        model.put("form", new CustomerLoginDTO());
        return "loginForm";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginEffect(@ModelAttribute CustomerLoginDTO loginDto, Map<String, Object> model) {
        userLoginService.login(loginDto);
        return "index";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET) //wyświetlanie pustego formularza
    public String registerForm(Map<String, Object> model) {
        model.put("form", new CustomerRegistrationDTO()); //pusty CustomerRegistrationDTO do przechowywania danych z formularza rejestracji
        // dodatkowo z CustomerRegistrationDto wyciagnijcie pola [street, city, country, zipCode
        // do osbnej klasy UserAddress tak by sie wszystko kompilowalo i przechodziły testy - nalezy je poprawic po zmianie
        model.put("countries", Arrays.asList(Countries.values())); //kolekcja krajów - enum Countries (POLSKA,NIEMCY,ROSJA) z polami symbol plName

        return "registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST) //obsluga przeslanych danych
    public String registerEffect(@ModelAttribute CustomerRegistrationDTO customerRegistrationDto, Map<String, Object> model) {
//        CustomerRegistrationDTO registrationdto = (CustomerRegistrationDTO) model.get("customerRegistrationDto");
        Map<String, String> validationErrorsMap = new UserValidationService().validateUserData(customerRegistrationDto); //serwis do walidacji danych uzytkownika
        model.put("form", customerRegistrationDto);
        model.put("countries", Arrays.asList(Countries.values())); //kolekcja krajów (w to miejsce wstawcie kolekcje) - enum Countries (POLSKA,NIEMCY,ROSJA) z polami symbol plName

        if (!validationErrorsMap.isEmpty()) { //sprawdzenie czy walidacja danych sie powiodla (pusta mapa) - najpierw sytuacja kiedy sie nie powiodla
            model.putAll(validationErrorsMap);
            return "registerForm";
        } else { //tu jest sytuacja kiedy walidacja jest ok
            try {
                userRegistrationService.registerUser(customerRegistrationDto);

                //todo tu nalezy zarejestrowac uzytkownika przez
                // serwis UserRegistrationService
            } catch (UserExistsException e) {
                model.put("userExistsException", "Uzytkownik isnieje!!!!"); //todo tu wstawcie odpowiedni komunikat "Uzytkownik isnieje" np
                return "registerForm";
            }
            return "registerEffect";
        }
    }
}
