package by.bsuir.kravchenko.validator;

import by.bsuir.kravchenko.exception.ServiceException;
import by.bsuir.kravchenko.service.UserService;

import java.util.List;

public class UserValidator {
    private static final String REGEX_NAME = "^[A-ZЁА-Я]([a-z]{1,20}|[а-яё]{1,20})$";
    private static final String REGEX_EMAIL = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,35}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,35}[a-zA-Z0-9])?)*$";

    private static UserService userService = new UserService();

    public static boolean addUserValidation(String firstName,
                                            String lastName, String email,
                                            String password, List<String> messages) throws ServiceException {
        if (firstName == null || firstName.isEmpty()
                || lastName == null || lastName.isEmpty()
                || password == null || password.isEmpty()
                || email == null || email.isEmpty()) {
            messages.add("Fill in the blank fields");
            return false;
        }

        boolean isValid = true;

        if (!firstName.matches(REGEX_NAME)) {
            messages.add("Invalid First Name");
            isValid = false;
        }
        if (!lastName.matches(REGEX_NAME)) {
            messages.add("Invalid Last Name");
            isValid = false;
        }
        if (!validateEmail(email)) {
            messages.add("Invalid Email");
            isValid = false;
        }

        if (userService.checkIsEmailAlreadyExists(email)) {
            messages.add("Email is already exist");
            isValid = false;
        }

        if (!validatePassword(password)) {
            messages.add("Invalid Password");
            isValid = false;
        }
        return isValid;
    }

    private static boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return email.matches(REGEX_EMAIL);
    }

    private static boolean validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        return password.length() >= 6;
    }

    public static boolean validateLogInData(String email, String password) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }
        return (validateEmail(email) && validatePassword(password));
    }
}
