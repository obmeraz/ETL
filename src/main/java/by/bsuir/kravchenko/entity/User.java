package by.bsuir.kravchenko.entity;

import javax.management.relation.Role;
import java.util.Optional;

public class User {
    private long id;
    private RoleType role;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public RoleType getRole() {
        return role;
    }

    public int getRoleId() {
        return role.ordinal();
    }

    public void setRole(int roleId) {
        Optional<RoleType> roleTypeOptional = RoleType.valueOf(roleId);
        this.role = roleTypeOptional.orElse(RoleType.USER);
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
