package bookstore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class InMemoryCategoryDAOTest {

//    private InMemoryCategoryDAO inMemoryCategoryDAO =
//            Mockito.spy(new InMemoryCategoryDAO);

    @Test
    void shouldReturnListOfCategoriesFromFile() {
        //given
        InMemoryCategoryDAO inMemoryCategoryDAO = new InMemoryCategoryDAO();
        Category cat1;
        Category cat2;
        Category cat3;
        Integer expectedParentId1 = null;
        Integer expectedParentId2 = 1;
        Integer expectedParentId3 = 2;
        String expectedName1 = "Książki";
        String expectedName2 = "Powieści";
        String expectedName3 = "Fantastyka";

        //when
        List<Category> list = inMemoryCategoryDAO.getCategories();
        cat1 = list.stream()
                .filter(e -> e.getId().equals(1))
                .findFirst()
                .get();
        Integer actualValue1 = cat1.getParentId();
        String actualName1 = cat1.getName();
        cat2 = list.stream()
                .filter(e -> e.getId().equals(2))
                .findFirst()
                .get();
        Integer actualValue2 = cat2.getParentId();
        String actualName2 = cat2.getName();
        cat3 = list.stream()
                .filter(e -> e.getId().equals(3))
                .findFirst()
                .get();
        Integer actualValue3 = cat3.getParentId();
        String actualName3 = cat3.getName();

        //then
        Assertions.assertEquals(expectedParentId1, actualValue1);
        Assertions.assertEquals(expectedParentId2, actualValue2);
        Assertions.assertEquals(expectedParentId3, actualValue3);
        Assertions.assertEquals(expectedName1, actualName1);
        Assertions.assertEquals(expectedName2, actualName2);
        Assertions.assertEquals(expectedName3, actualName3);
    }

}