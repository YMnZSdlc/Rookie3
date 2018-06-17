package bookstore;

import lombok.Getter;
import lombok.Setter;


@Getter //dzięki temu nie trzeba robić getter setter
@Setter //robi to kompilator w trakcie kompilacji
//tylko coś u mnie nie działa
public class Category {
    private Integer id;
    private String name;
    private Integer parentId;

    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
