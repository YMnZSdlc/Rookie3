package bookstore.users.services;

import bookstore.users.dtos.UserLoggedInDTO;
import bookstore.users.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserContextHolder {

    private static UserLoggedInDTO userLoggedInDTO;

    public static void logInUser(User user) {
        userLoggedInDTO = new UserLoggedInDTO(user.getEmail());
    }

    public static String getUserLoggedIn() {
        return userLoggedInDTO == null ? null : userLoggedInDTO.getLogin();
    }

}
