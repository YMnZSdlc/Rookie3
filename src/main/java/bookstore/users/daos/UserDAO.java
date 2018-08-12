package bookstore.users.daos;

import bookstore.App;
import bookstore.users.entities.User;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service //tworzy zabezpieczony SINGLETON z if'ami itd..
public class UserDAO {

    // @Service @Conroler @Repository tego szuka spring po uruchomieniu aplikacji
    // i tworzy singletony obiektów wskazanych adnotacjami

    @Autowired //dzięki tej adnotacji spring tworzy referencje do UserRepository
    private UserRepository userRepository;

   // private List<User> userList = initializeFromFile();



    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    private void serializeToFile(List<User> userList) {
        String usersDataPath = Paths.get(App.FILES_DIRECTORY + "/usersData")
                .toAbsolutePath().toString();
        try (FileOutputStream fileOutputStream = new FileOutputStream(usersDataPath);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(userList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<User> initializeFromFile() {
        try {
            String usersDataPath = Paths.get(App.FILES_DIRECTORY + "/usersData")
                    .toAbsolutePath().toString();
            try (FileInputStream fileInputStream = new FileInputStream(usersDataPath);
                 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

                return (List<User>) objectInputStream.readObject();

            }
        } catch (Exception e) {
            return Lists.newArrayList();
        }
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findAllByEmail(email)
                .stream().findFirst();

//        return userList.stream()
//                .filter(w -> w.getEmail().equalsIgnoreCase(email))
//                .findFirst();
    }
}
