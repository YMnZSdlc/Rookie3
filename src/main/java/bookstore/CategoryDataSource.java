package bookstore;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CategoryDataSource {
    public List<String> readDataFromFile() {
        List<String> linesFromFile = null;
        try {

            URI uri = this.getClass().getClassLoader().getResource("kategorie.txt").toURI();
            linesFromFile = Files.readAllLines(Paths.get(uri), Charset.forName("UNICODE"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return linesFromFile;
    }
}
