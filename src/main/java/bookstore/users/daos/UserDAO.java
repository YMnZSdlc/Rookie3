package bookstore.users.daos;

import bookstore.App;
import bookstore.users.entities.User;
import com.google.common.collect.Lists;

import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class UserDAO {
    private List<User> userList = initializeFromFile();

    public List<User> getUserList() {
        return userList;
    }

    public void addUser(User user) {
        userList.add(user);
        serializeToFile(userList);
    }

    private void serializeToFile(List<User> userList) {
        String usersDataPath = this.getClass()
                .getClassLoader().getResource("usersData").getFile();

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(usersDataPath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<User> initializeFromFile() {
        try {
            String usersDataPath = Paths.get(App.FILES_DIRECTORY+"/userData").toAbsolutePath().toString();
            try (FileInputStream fileInputStream = new FileInputStream(usersDataPath);
                 ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                return (List<User>) objectInputStream.readObject();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return Arrays.asList();
        } catch (Exception e) {
            return Lists.newArrayList();
        }
    }


}
