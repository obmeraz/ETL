package by.bsuir.kravchenko.service;

import by.bsuir.kravchenko.dao.impl.UserDaoImpl;
import by.bsuir.kravchenko.entity.User;
import by.bsuir.kravchenko.exception.DaoException;
import by.bsuir.kravchenko.exception.ServiceException;

import java.util.Optional;

public class UserService {

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
}
