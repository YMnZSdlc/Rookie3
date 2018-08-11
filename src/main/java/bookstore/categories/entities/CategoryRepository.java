package bookstore.categories.entities;

import bookstore.categories.daos.CategorySource;
import bookstore.categories.daos.InMemoryCategoryDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>, CategorySource {

    List<Category> findAllByName(String name);

    @Query("select c from Category c where c.parentId = :parentId")
        //to jest to samo co niżej
    List<Category> findAllByParentId(Integer parentId);

    @Override
    default Optional<Category> findCategoryById(Integer id) {
        return findById(id);
    }

    @Override
    default List<Category> getCategories() {
        List<Category> all = findAll();
        if (all.isEmpty()) {
            all = InMemoryCategoryDAO.getInstance().getCategories();
           return saveAll(all);
        }
        return all;
    }

    @Override
    default void updateCategory(Category category) {
        save(category);
    }

    @Override
    default List<Category> findCategoriesByName(String name) {
        return findAllByName(name);
    }
}
