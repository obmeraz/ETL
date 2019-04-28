package by.bsuir.kravchenko.command;

import by.bsuir.kravchenko.command.impl.EmptyCommand;
import by.bsuir.kravchenko.command.impl.admin.*;
import by.bsuir.kravchenko.command.impl.common.LogInCommand;
import by.bsuir.kravchenko.command.impl.common.LogOutCommand;
import by.bsuir.kravchenko.command.impl.user.IncrementLoadDB;
import by.bsuir.kravchenko.command.impl.user.InitLoadDB;

public enum CommandType {
    EMPTY_COMMAND(new EmptyCommand()),
    LOG_OUT(new LogOutCommand()),
    INIT_LOAD_DB(new InitLoadDB()),
    INCREMENT_LOAD_DB(new IncrementLoadDB()),
    ADD_USER(new AddUserCommand()),
    DELETE_USER(new DeleteUserCommand()),
    DISPLAY_USERLIST(new DisplayUserListCommand()),
    MAKE_ADMIN(new MakeAdminCommand()),
    REMOVE_ADMIN(new RemoveAdminCommand()),
    LOGIN(new LogInCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
