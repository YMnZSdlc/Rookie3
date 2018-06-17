package bookstore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryState {
    private boolean open;
    private boolean selected;
    private boolean disabled;
}
