package by.bsuir.kravchenko.command.impl.common;

import by.bsuir.kravchenko.command.Command;
import by.bsuir.kravchenko.controller.Router;
import by.bsuir.kravchenko.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        request.getSession().invalidate();
        router.setPagePath("/jsp/main.jsp");
        router.setRedirectRoute();
        return router;
    }
}
