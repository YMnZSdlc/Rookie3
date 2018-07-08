package bookstore;

import bookstore.categories.entities.Category;
import bookstore.categories.daos.InMemoryCategoryDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class InMemoryCategoryDAOTest {
//    private InMemoryCategoryDAO inMemoryCategoryDAO =
//            Mockito.spy(InMemoryCategoryDAO);


    @Test
    void shouldReturnListOfCategoriesFromFile() {
        // given
        InMemoryCategoryDAO inMemoryCategoryDAO = InMemoryCategoryDAO.getInstance();
        Category cat1;
        Category cat2;
        Category cat3;
        Integer expectedParentId1 = null;
        Integer expectedParentId2 = 1;
        Integer expectedParentId3 = 2;
        String expectedNameId1 = "Książki";
        String expectedNameId2 = "Atlasy, mapy, przewodniki";
        String expectedNameId3 = "Atlasy geograficzne i astronomiczne";

        // when
        List<Category> list = inMemoryCategoryDAO.getCategories();

        cat1 = list.stream()
                .filter(e -> e.getId().equals(1))
                .findFirst().get();
        Integer actualParentId1 = cat1.getParentId();
        String actualNameId1 = cat1.getName();

        cat2 = list.stream()
                .filter(e -> e.getId().equals(2))
                .findFirst().get();
        Integer actualParentId2 = cat2.getParentId();
        String actualNameId2 = cat2.getName();

        cat3 = list.stream()
                .filter(e -> e.getId().equals(3))
                .findFirst().get();
        Integer actualParentId3 = cat3.getParentId();
        String actualNameId3 = cat3.getName();

        // then
        Assertions.assertEquals(expectedParentId1, actualParentId1);
        Assertions.assertEquals(expectedParentId2, actualParentId2);
        Assertions.assertEquals(expectedParentId3, actualParentId3);

        Assertions.assertEquals(expectedNameId1, actualNameId1);
        Assertions.assertEquals(expectedNameId2, actualNameId2);
        Assertions.assertEquals(expectedNameId3, actualNameId3);
    }
}