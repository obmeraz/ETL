package by.bsuir.kravchenko.service;

import by.bsuir.kravchenko.dao.impl.UserDaoImpl;
import by.bsuir.kravchenko.entity.RoleType;
import by.bsuir.kravchenko.entity.User;
import by.bsuir.kravchenko.exception.DaoException;
import by.bsuir.kravchenko.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class UserService {

    public User buildUser(String firstName,
                          String lastName, String email,
                          String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setRole(RoleType.USER);
        user.setPassword(password);
        return user;
    }

    public Optional<User> takeUserByEmailAndPassword(String email, String password) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        Optional<User> userOptional;
        try {
            userOptional = userDao.findUserByEmailAndPassword(email, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return userOptional;
    }

    public List<User> takeAllUsers() throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        List<User> users;
        try {
            users = userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return users;
    }

    public Optional<User> takeUserById(long id) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        Optional<User> userOptional;
        try {
            userOptional = userDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Can't find user by id", e);
        }
        return userOptional;
    }

    public void giveUserAdminRights(User user) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        user.setRole(RoleType.ADMIN);
        try {
            userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void deleteUserById(long id) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        try {
            userDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void removeAdminRights(User user) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        user.setRole(RoleType.USER);
        try {
            userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void addUser(User user) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        try {
            userDao.create(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
