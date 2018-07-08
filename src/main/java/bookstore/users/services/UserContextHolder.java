package bookstore.users.services;

import bookstore.users.dtos.UserLoggedInDTO;
import bookstore.users.entities.User;

public class UserContextHolder {

    private static UserLoggedInDTO userLoggedInDTO;

    public static void logInUser(User user){
        userLoggedInDTO = new UserLoggedInDTO(user.getEmail());

    }

}
