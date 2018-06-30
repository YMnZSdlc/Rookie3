package bookstore;

import org.apache.commons.lang3.NotImplementedException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryCategoryDAO implements CategorySource {
    // DAO Data Access Object
    // DTO Data Transfer Object
    private static InMemoryCategoryDAO instance;
    private List<Category> categoriesInMemory;
    private static CategoryDataSource categoryDataSource;


    private InMemoryCategoryDAO() {
        categoriesInMemory = initializeCategories();
    }

    protected InMemoryCategoryDAO(CategoryDataSource categoryDataSource){
        this.categoryDataSource = categoryDataSource;
        categoriesInMemory = initializeCategories();
    }

    public static InMemoryCategoryDAO getInstance() {
        // sprawdzamy z uwagi na wydajnosc synchronized
        if (instance == null) {
            // z uwagi na wielowątkowość, by na pewno
            // tylko jeden wątek utworzył instancję
            synchronized (InMemoryCategoryDAO.class) {
                if (instance == null) {
                    CategoryDataSource categoryDataSource = new CategoryDataSource();
                    instance = new InMemoryCategoryDAO(categoryDataSource);
                }
            }
        }
        return instance;
    }

    private List<Category> initializeCategories() {
        List<String> linesFromFile = readDataFromFile();
        return populateCategories(linesFromFile);
    }

    public List<String> readDataFromFile() {
        List<String> linesFromFile = null;
        try {
            linesFromFile = Files.readAllLines(Paths
                    .get("C:\\projects\\sda9intermediate\\src\\main\\resources\\kategorie.txt"), Charset.forName("UNICODE"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linesFromFile;
    }

    private List<Category> populateCategories(List<String> linesFromFile) {
        AtomicInteger idCounter = new AtomicInteger(1);
        List<Category> categoryList = linesFromFile.stream()
                .map(e -> new Category(idCounter.getAndIncrement(), e))
                .collect(Collectors.toList());
        Map<Integer, List<Category>> categoryMap = categoryList.stream()
                .collect(Collectors.groupingBy(e -> countSpaces(e)));
        populateRecursive(0, categoryMap);
        categoryList
                .forEach(category -> category
                        .setName(category
                                .getName()
                                .trim()));
        return categoryList;
    }

    private void populateRecursive(int level, Map<Integer, List<Category>> categoryMap) {
        List<Category> categories = categoryMap.get(level);
        if (categories == null) {
            return;
        }
        for (Category category : categories) {
            if (level == 0) {
                category.setParentId(null);
            } else {
                Integer parentId = categoryMap.get(level - 1).stream()
                        .map(e -> e.getId())
                        .filter(e -> e < category.getId())
                        .sorted(Comparator.reverseOrder())
                        .findFirst().get();
                category.setParentId(parentId);
            }
        }
        populateRecursive(level + 1, categoryMap);
    }

    private int countSpaces(Category category) {
        return category.getName().startsWith(" ") ?
                category.getName().split("\\S")[0].length() : 0;
    }

    @Override
    public void updateCategory(Category category) {
        throw new NotImplementedException("Pudlo");
    }

    @Override
    public List<Category> findCategoriesByName(String name) {
        return categoriesInMemory
                .stream()
                .filter(category->category.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Category> getCategories() {
        return categoriesInMemory;
    }

    @Override
    public Optional<Category> findCategoryById(Integer id) {
        return categoriesInMemory
                .stream()
                .filter(category -> category.getId().equals(id))
                .findFirst();
    }
}
