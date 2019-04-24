package by.bsuir.kravchenko.command;

import by.bsuir.kravchenko.command.impl.EmptyCommand;

public enum CommandType {
    EMPTY_COMMAND(new EmptyCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
