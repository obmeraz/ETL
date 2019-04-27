package by.bsuir.kravchenko.command.impl.common;

import by.bsuir.kravchenko.command.Command;
import by.bsuir.kravchenko.controller.Router;
import by.bsuir.kravchenko.entity.User;
import by.bsuir.kravchenko.exception.CommandException;
import by.bsuir.kravchenko.exception.ServiceException;
import by.bsuir.kravchenko.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LogInCommand implements Command {
    private UserService userService = new UserService();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            Optional<User> userOptional = userService.takeUserByEmailAndPassword(email, password);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("role", user.getRole());
                router.setPagePath("/jsp/main.jsp");
                router.setRedirectRoute();
            }else{
                request.getSession().setAttribute("message", "Invalid email or password");
                router.setRedirectRoute();
                router.setPagePath("/jsp/login.jsp");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
