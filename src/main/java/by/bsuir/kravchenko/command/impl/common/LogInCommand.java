package by.bsuir.kravchenko.command.impl.common;

import by.bsuir.kravchenko.command.Command;
import by.bsuir.kravchenko.command.PagePath;
import by.bsuir.kravchenko.controller.Router;
import by.bsuir.kravchenko.entity.User;
import by.bsuir.kravchenko.exception.CommandException;
import by.bsuir.kravchenko.exception.ServiceException;
import by.bsuir.kravchenko.service.UserService;
import by.bsuir.kravchenko.validator.UserValidator;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LogInCommand implements Command {
    private UserService userService = new UserService();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (UserValidator.validateLogInData(email, password)) {
            try {
                String passwordHash = DigestUtils.sha256Hex(password);
                Optional<User> userOptional = userService.takeUserByEmailAndPassword(email, passwordHash);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    request.getSession().setAttribute("user", user);
                    request.getSession().setAttribute("role", user.getRole());
                    router.setPagePath(PagePath.MAIN);
                    router.setRedirectRoute();
                } else {
                    request.getSession().setAttribute("message", "Invalid email or password");
                    router.setRedirectRoute();
                    router.setPagePath(PagePath.LOGIN);
                }
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        } else {
            request.getSession().setAttribute("message", "Correct data,please");
            router.setRedirectRoute();
            router.setPagePath(PagePath.LOGIN);
        }
        return router;
    }
}
