package bookstore;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class OnlyOneController {


    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        return "index";
    }

    @GetMapping("/cats")
    public String cats(Map<String, Object> model, @RequestParam(required = false) String searchText) {
        List<AdminCategoryDTO> categoryDtoList = null;//todo - tu trzeba przekazac opracowaną listę
        model.put("catsdata", categoryDtoList);
        return "cats";
    }
}
