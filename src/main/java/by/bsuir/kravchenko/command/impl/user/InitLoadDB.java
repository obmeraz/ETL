package by.bsuir.kravchenko.command.impl.user;

import by.bsuir.kravchenko.command.Command;
import by.bsuir.kravchenko.controller.Router;
import by.bsuir.kravchenko.service.DataBaseService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class InitLoadDB implements Command {
    private DataBaseService dataBaseService = new DataBaseService();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        List<String> logs = new ArrayList<>();
        dataBaseService.initDataBase(logs);
        request.setAttribute("logs", logs);
        router.setPagePath("/jsp/result.jsp");
        return router;
    }
}
