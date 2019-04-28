package by.bsuir.kravchenko.dao;

import by.bsuir.kravchenko.entity.User;
import by.bsuir.kravchenko.exception.DaoException;

import java.util.Optional;

public interface UserDao<T> extends Dao<User>{
    Optional<User> findUserByEmailAndPassword(String email, String password) throws DaoException;
    boolean checkEmail(String email) throws DaoException;
}
