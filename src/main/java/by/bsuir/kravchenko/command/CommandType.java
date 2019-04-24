package by.bsuir.kravchenko.command;

import by.bsuir.kravchenko.command.impl.EmptyCommand;
import by.bsuir.kravchenko.command.impl.common.LogInCommand;

public enum CommandType {
    EMPTY_COMMAND(new EmptyCommand()),
    LOGIN(new LogInCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
