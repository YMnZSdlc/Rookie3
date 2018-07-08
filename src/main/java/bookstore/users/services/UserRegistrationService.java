package bookstore.users.services;

import bookstore.users.daos.UserDAO;
import bookstore.users.exception.UserExistsException;
import bookstore.users.dtos.CustomerRegistrationDTO;
import bookstore.users.entities.User;

public class UserRegistrationService {
    private UserValidationService userValidationService = new UserValidationService();
    private UserDAO userDAO = new UserDAO();

    public void registerUser(CustomerRegistrationDTO registrationDTO) {

        if (userExistVer1(registrationDTO)) {
            throw new UserExistsException("User " + registrationDTO.getEmail() + " exist");
        }
        //todo nalezy przepisac dane z  CustomerRegistrationDTO na User -> zapisujac hash hasla i potem dodac uzytkownika do listy userow w userdao
        User user = new CustomerRegistrationDTOtoUserBuilder().rewriteToUser(registrationDTO);
        userDAO.addUser(user);
    }

    private boolean userExistVer1(CustomerRegistrationDTO customer) {
        for (User user : userDAO.getUserList()) {
            if (user.getEmail().trim().equals(customer.getEmail().trim())){
                return true;
            }
        }
        return false;
    }

    private boolean userExistVer2 (CustomerRegistrationDTO customer){
        return userDAO.getUserList().stream()
                .map(user->user.getEmail())
                .filter(email->email.equals(customer.getEmail()))
                .findAny().isPresent();
    }

    private boolean userExistVer3 (CustomerRegistrationDTO customer){
        return userDAO.getUserList().stream()
                .map(user->user.getEmail())
                .anyMatch(email->email.equals(customer.getEmail()));
    }
}