package bookstore;

public class UserRegistrationService {
    private UserValidationService userValidationService = new UserValidationService();
    private UserDAO userDAO = new UserDAO();

/*    private void registerUser(CustomerRegistrationDTO customer) {

        if (userExistVer1(customer)) {
            throw new UserExistExeption("User " + customer.getEmail() + " exist");
        }
        UserRegistrationDTOToUserBuilder


    }*/

/*    private boolean userExistVer1(CustomerRegistrationDTO customer) {
        return userDAO.getUserList().stream()
                .map(user -> user.getEmail()).anyMatch(e -> e.equals(customer.getEmail()));
    }*/


}
