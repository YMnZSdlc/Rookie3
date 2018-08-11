package bookstore.categories.entities;

import bookstore.infrastructure.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

// dzieki temu nie musimy pisac kodow
// getterow i setterow w klasie, robi to
// kompilator w trakcie kompilacji, dzieki
// bibliotece Lombok

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseEntity {

    private String name;
    private Integer parentId;

    public Category(String name) {
        this.name = name;
    }
}
