package by.bsuir.kravchenko.controller;

import by.bsuir.kravchenko.command.Command;
import by.bsuir.kravchenko.command.CommandConstant;
import by.bsuir.kravchenko.command.CommandFactory;
import by.bsuir.kravchenko.command.impl.EmptyCommand;
import by.bsuir.kravchenko.exception.CommandException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<Command> commandOptional = CommandFactory.defineCommand(request.getParameter(CommandConstant.COMMAND));
        Command command = commandOptional.orElse(new EmptyCommand());
        Router router = new Router();
        try {
            router = command.execute(request);
        } catch (CommandException e) {
            e.printStackTrace();
        }
        switch (router.getRoute()) {
            case FORWARD:
                RequestDispatcher dispatcher = request.getRequestDispatcher(router.getPagePath());
                dispatcher.forward(request, response);
                break;
            case REDIRECT:
                response.sendRedirect(request.getContextPath() + router.getPagePath());
                break;
        }
    }
}
