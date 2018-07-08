package bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static String FILES_DIRECTORY;

    public static void main(String[] args) {
//        FILES_DIRECTORY= args[0];
        SpringApplication.run(App.class);
    }

}