package bookstore;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private List<User> userList = new ArrayList<User>();

    public List<User> getUserList() {
        return userList;
    }
}
