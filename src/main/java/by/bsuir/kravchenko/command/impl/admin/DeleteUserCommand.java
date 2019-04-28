package by.bsuir.kravchenko.command.impl.admin;

import by.bsuir.kravchenko.command.Command;
import by.bsuir.kravchenko.command.CommandConstant;
import by.bsuir.kravchenko.command.PagePath;
import by.bsuir.kravchenko.controller.Router;
import by.bsuir.kravchenko.exception.CommandException;
import by.bsuir.kravchenko.exception.ServiceException;
import by.bsuir.kravchenko.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand implements Command {
    private UserService userService = new UserService();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
       // String path=String.valueOf(request.getSession().getAttribute(CommandConstant.CURRENT_PAGE));
        String id = request.getParameter("id");
        if (id == null || id.isEmpty()) {
            request.getSession().setAttribute(CommandConstant.MESSAGE, "Doesn't exist");
            router.setPagePath(PagePath.ERROR);
            router.setRedirectRoute();
            return router;
        }
        try {
            long userId = Long.parseLong(id);
            userService.deleteUserById(userId);
            router.setPagePath(PagePath.ADMIN_COMMAND);
            router.setRedirectRoute();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
