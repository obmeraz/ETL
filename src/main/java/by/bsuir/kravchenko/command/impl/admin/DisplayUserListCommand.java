package by.bsuir.kravchenko.command.impl.admin;

import by.bsuir.kravchenko.command.Command;
import by.bsuir.kravchenko.command.PagePath;
import by.bsuir.kravchenko.controller.Router;
import by.bsuir.kravchenko.entity.User;
import by.bsuir.kravchenko.exception.CommandException;
import by.bsuir.kravchenko.exception.ServiceException;
import by.bsuir.kravchenko.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DisplayUserListCommand implements Command {
    private UserService userService = new UserService();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        List<User> users;
        try {
            users = userService.takeAllUsers();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        router.setPagePath(PagePath.ADMIN);
        request.setAttribute("users", users);
        return router;
    }
}
