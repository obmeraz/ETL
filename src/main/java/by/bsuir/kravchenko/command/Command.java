package by.bsuir.kravchenko.command;

import by.bsuir.kravchenko.controller.Router;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest request);
}