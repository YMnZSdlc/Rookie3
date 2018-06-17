package bookstore;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AdminCategoryDTO implements CategoryInfoHolder {
    private String id;
    private String text;
    private CategoryState state;
    private AdminCategoryDTO parent;
    private String parentCategoryId;
}
