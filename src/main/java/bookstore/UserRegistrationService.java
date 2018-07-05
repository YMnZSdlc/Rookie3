package bookstore;

public class UserRegistrationService {
    private UserValidationService userValidationService = new UserValidationService();
    private UserDAO userDAO = new UserDAO();

    private void registerUser(CustomerRegistrationDTO customer) {

        if (userExistVer1(customer)) {
            throw new UserExistExeption("User " + customer.getEmail() + " exist");
        }
//        UserRegistrationDTOToUserBuilder //todo



    }

    private boolean userExistVer1(CustomerRegistrationDTO customer) {
        for (User user : userDAO.getUserList()) {
            if (user.getEmail().equals(customer.getEmail())){
                return true;
            }
        }
        return false;
    }

    private boolean userExistVer2 (CustomerRegistrationDTO customer){
        return userDAO.getUserList().stream()
                .map(e->e.getEmail())
                .filter(email->email.equals(customer.getEmail()))
                .findAny().isPresent();
    }


}