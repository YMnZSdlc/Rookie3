package bookstore;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AdminCategoryDTO
        implements CategoryInfoHolder {
    private String id;
    private String text;
    private CategoryState state;
    private AdminCategoryDTO parentCat; //to musiałem zmienić z uwagi na frontendowców
    private String parentCategoryId;

    //frontendowcy się uparli i oczekują od nas wartości parent id wystawionej w takiej metodzie, alb # jeśli nie ma parenta
    public String getParent() {
        return null; //todo
    }
}
