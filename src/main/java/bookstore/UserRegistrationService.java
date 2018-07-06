package bookstore;

public class UserRegistrationService {
    private UserValidationService userValidationService = new UserValidationService();
    private UserDAO userDAO = new UserDAO();

    private void registerUser(CustomerRegistrationDTO customer) {

        if (userExistVer1(customer)) {
            throw new UserExistsException("User " + customer.getEmail() + " exist");
        }
        //todo nalezy przepisac dane z  CustomerRegistrationDTO na User -> zapisujac hash hasla i potem dodac uzytkownika do listy userow w userdao
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