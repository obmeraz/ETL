package by.bsuir.kravchenko.dao;

import by.bsuir.kravchenko.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    void create(T entity) throws DaoException;

    List<T> findAll() throws DaoException;

    void update(T entity) throws DaoException;

    Optional<T> findById(long id) throws DaoException;

    void delete(long id) throws DaoException;
}
