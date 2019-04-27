package by.bsuir.kravchenko.command;

import by.bsuir.kravchenko.command.impl.EmptyCommand;
import by.bsuir.kravchenko.command.impl.common.LogInCommand;
import by.bsuir.kravchenko.command.impl.common.LogOutCommand;
import by.bsuir.kravchenko.command.impl.user.IncrementLoadDB;
import by.bsuir.kravchenko.command.impl.user.InitLoadDB;

public enum CommandType {
    EMPTY_COMMAND(new EmptyCommand()),
    LOG_OUT(new LogOutCommand()),
    INIT_LOAD_DB(new InitLoadDB()),
    INCREMENT_LOAD_DB(new IncrementLoadDB()),
    LOGIN(new LogInCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
