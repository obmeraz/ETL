package by.bsuir.kravchenko.filter;

import by.bsuir.kravchenko.command.CommandType;
import by.bsuir.kravchenko.entity.RoleType;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Set;

class RoleConfigurator {
    private static EnumMap<RoleType, Set<CommandType>> rolePrivilegeMap = new EnumMap<>(RoleType.class);

    static {
        initGuestPrivileges();
        initUserPrivileges();
        initAdminPrivileges();
    }

    private static void initGuestPrivileges(){
        Set<CommandType> userCommands = new HashSet<>();
        userCommands.add(CommandType.LOGIN);
        rolePrivilegeMap.put(RoleType.GUEST, userCommands);
    }

    private static void initUserPrivileges() {
        Set<CommandType> userCommands = new HashSet<>();
        userCommands.add(CommandType.EMPTY_COMMAND);
        userCommands.add(CommandType.INCREMENT_LOAD_DB);
        userCommands.add(CommandType.LOG_OUT);
        rolePrivilegeMap.put(RoleType.USER, userCommands);
    }

    private static void initAdminPrivileges() {
        Set<CommandType> adminCommands = new HashSet<>();
        adminCommands.add(CommandType.EMPTY_COMMAND);
        adminCommands.add(CommandType.INCREMENT_LOAD_DB);
        adminCommands.add(CommandType.LOG_OUT);
        adminCommands.add(CommandType.INIT_LOAD_DB);
        adminCommands.add(CommandType.DISPLAY_USERLIST);
        adminCommands.add(CommandType.DELETE_USER);
        adminCommands.add(CommandType.MAKE_ADMIN);
        adminCommands.add(CommandType.REMOVE_ADMIN);
        adminCommands.add(CommandType.ADD_USER);
        rolePrivilegeMap.put(RoleType.ADMIN, adminCommands);
    }

    boolean checkRolePrivileges(RoleType userRole, CommandType commandType) {
        if (commandType.equals(CommandType.LOGIN)) {
            return true;
        }
        Set<CommandType> commandTypeList = rolePrivilegeMap.get(userRole);
        return commandTypeList.stream().anyMatch(commandType::equals);
    }

    boolean checkCommandName(String commandName) {
        return Arrays.stream(CommandType.values())
                .anyMatch(commandType -> commandType.toString().equals(commandName.toUpperCase()));
    }
}
