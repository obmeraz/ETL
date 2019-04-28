package by.bsuir.kravchenko.command.impl.admin;

import by.bsuir.kravchenko.command.Command;
import by.bsuir.kravchenko.command.PagePath;
import by.bsuir.kravchenko.controller.Router;
import by.bsuir.kravchenko.entity.User;
import by.bsuir.kravchenko.exception.CommandException;
import by.bsuir.kravchenko.exception.ServiceException;
import by.bsuir.kravchenko.service.UserService;
import by.bsuir.kravchenko.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class AddUserCommand implements Command {
    private static final String PARAM_FIRST_NAME = "firstname";
    private static final String PARAM_LAST_NAME = "lastname";
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_PASSWORD = "password";
    private UserService userService = new UserService();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String firstName = request.getParameter(PARAM_FIRST_NAME);
        String lastName = request.getParameter(PARAM_LAST_NAME);
        String email = request.getParameter(PARAM_EMAIL);
        String password = request.getParameter(PARAM_PASSWORD);
        List<String> errorLogs = new ArrayList<>();
        try {
            if (UserValidator.addUserValidation(firstName, lastName, email, password, errorLogs)) {
                User user = userService.buildUser(firstName, lastName, email, password);
                try {
                    userService.addUser(user);
                    router.setPagePath(PagePath.ADMIN_COMMAND);
                    router.setRedirectRoute();
                } catch (ServiceException e) {
                    throw new CommandException(e);
                }
            } else {
                request.getSession().setAttribute("errorLogs", errorLogs);
                router.setRedirectRoute();
                router.setPagePath(PagePath.ADMIN_COMMAND);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
