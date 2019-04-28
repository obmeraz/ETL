package by.bsuir.kravchenko.command.impl.admin;

import by.bsuir.kravchenko.command.Command;
import by.bsuir.kravchenko.command.CommandConstant;
import by.bsuir.kravchenko.command.PagePath;
import by.bsuir.kravchenko.controller.Router;
import by.bsuir.kravchenko.entity.User;
import by.bsuir.kravchenko.exception.CommandException;
import by.bsuir.kravchenko.exception.ServiceException;
import by.bsuir.kravchenko.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class MakeAdminCommand implements Command {
    private UserService userService = new UserService();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
       // String path=String.valueOf(request.getSession().getAttribute(CommandConstant.CURRENT_PAGE));
        String id = request.getParameter("id");
        if (id == null || id.isEmpty()) {
            request.getSession().setAttribute(CommandConstant.MESSAGE, "Invalid user id");
            router.setPagePath(PagePath.ERROR);
            router.setRedirectRoute();
            return router;
        }
        try {
            long userId = Long.parseLong(id);
            Optional<User> userOptional = userService.takeUserById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                userService.giveUserAdminRights(user);
                router.setPagePath(PagePath.ADMIN_COMMAND);
                router.setRedirectRoute();
            } else {
                request.getSession().setAttribute(CommandConstant.MESSAGE, "Can't found user");
                router.setPagePath(PagePath.ERROR);
                router.setRedirectRoute();
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
