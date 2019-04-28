package by.bsuir.kravchenko.entity;

import java.util.Arrays;
import java.util.Optional;

public enum RoleType {
    USER,
    ADMIN,
    GUEST;

    public static Optional<RoleType> valueOf(int roleId) {
        return Arrays.stream(values())
                .filter(roleType -> roleType.ordinal() == roleId)
                .findFirst();
    }
}
