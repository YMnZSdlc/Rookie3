package bookstore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        String expectedNameId2 = "Powieści";
        String expectedNameId3 = "Fantastyka";

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
        Assertions.assertEquals(expectedParentId1,
                actualParentId1);
        Assertions.assertEquals(expectedParentId2,
                actualParentId2);
        Assertions.assertEquals(expectedParentId3,
                actualParentId3);

        Assertions.assertEquals(expectedNameId1,
                actualNameId1);
        Assertions.assertEquals(expectedNameId2,
                actualNameId2);
        Assertions.assertEquals(expectedNameId3,
                actualNameId3);
    }
}