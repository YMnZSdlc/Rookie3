package bookstore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryCategoryDAOTest {

    @Test
    void shouldReturnListOfCategoriesFromFile(){
        //given
        InMemoryCategoryDAO inMemoryCategoryDAO = new InMemoryCategoryDAO();
        Category cat1;
        Category cat2;
        Category cat3;
        Integer expectedParentId1 = null;
        Integer expectedParentId2 = 1;
        Integer expectedParentId3 = 2;
        String expectedName1 = "Ksiazka";
        String expectedName2 = "Powiesc";
        String expectedName3 = "Ksiazka";
        //when
        List<Category> list = inMemoryCategoryDAO.initializeCategories();
        cat1 = list.stream()
                .filter(e -> e.equals(1))
                .findFirst()
                .get();
        Integer actualValue1 = cat1.getParentId();
        String actualName1 = cat1.getName();
        cat2 = list.stream()
                .filter(e -> e.equals(2))
                .findFirst()
                .get();
        Integer actualValue2 = cat1.getParentId();
        String actualName2 = cat1.getName();
        cat3 = list.stream()
                .filter(e -> e.equals(3))
                .findFirst()
                .get();
        Integer actualValue3 = cat1.getParentId();
        String actualName3 = cat1.getName();
        //then
        Assertions.assertEquals(expectedParentId1, actualValue1);
        Assertions.assertEquals(expectedParentId2, actualValue2);
        Assertions.assertEquals(expectedParentId3, actualValue3);
        Assertions.assertEquals(expectedName1, actualName1);
        Assertions.assertEquals(expectedName2, actualName2);
        Assertions.assertEquals(expectedName3, actualName3);
    }

}