package by.bsuir.kravchenko.command;

import by.bsuir.kravchenko.controller.Router;
import by.bsuir.kravchenko.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest request) throws CommandException;
}