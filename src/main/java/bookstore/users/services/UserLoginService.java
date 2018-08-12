package bookstore.users.services;

import bookstore.users.exception.PasswordDoesNotMatchException;
import bookstore.users.daos.UserDAO;
import bookstore.users.exception.UserNotExistsExeception;
import bookstore.users.dtos.CustomerLoginDTO;
import bookstore.users.entities.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.function.Supplier;

@Service //tworzy zabezpieczony SINGLETON z if'ami itd..
public class UserLoginService {

    @Autowired
    private UserDAO userDAO;

    public void login(CustomerLoginDTO customerLoginDTO) {
        Supplier<UserNotExistsExeception> exceptionSupplier = () -> new UserNotExistsExeception("Użytkownik nie istnieje");

        User user = userDAO.getUserList() // Sprawdzenie czy user istnieje
                .stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(customerLoginDTO.getLogin().trim()))
                .findFirst()
                .orElseThrow(exceptionSupplier);

        if (passwordIsNotCorrect(customerLoginDTO, user)) { // Sprawdzenie hasła
            throw new PasswordDoesNotMatchException("Błędne hasło");
        }
        UserContextHolder.logInUser(user);

    }

    private boolean passwordIsNotCorrect(CustomerLoginDTO customerLoginDTO, User user) {
        return !DigestUtils.sha512Hex(customerLoginDTO.getPassword().trim()).equals(user.getPasswordHash());
    }
}
