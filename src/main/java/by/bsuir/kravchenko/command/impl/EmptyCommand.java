package by.bsuir.kravchenko.command.impl;

import by.bsuir.kravchenko.command.Command;
import by.bsuir.kravchenko.command.PagePath;
import by.bsuir.kravchenko.controller.Router;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setPagePath(PagePath.MAIN);
        return router;
    }
}
