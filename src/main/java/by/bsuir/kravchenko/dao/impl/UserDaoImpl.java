package by.bsuir.kravchenko.dao.impl;

import by.bsuir.kravchenko.dao.UserDao;
import by.bsuir.kravchenko.entity.User;
import by.bsuir.kravchenko.exception.DaoException;
import by.bsuir.kravchenko.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao<User> {
    private static UserDaoImpl instance = new UserDaoImpl();

    private static final String USER_ID = "id";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role_id";


    private static final String SQL_SELECT_ALL_USERS = "SELECT id, firstname, lastname, email, password, role_id FROM users";

    private static final String SQL_FIND_USER_BY_ID = "SELECT id, firstname, lastname, email, password, role_id FROM users WHERE id=?";

    private static final String SQL_DELETE_USER = "DELETE FROM users WHERE ID=?";

    private static final String SQL_UPDATE_USER = "UPDATE users SET FIRSTNAME=?,LASTNAME=?,EMAIL=?,PASSWORD=?,ROLE_ID=? where id=?";

    private static final String SQL_INSERT_NEW_USER = "INSERT INTO users (FIRSTNAME,LASTNAME,EMAIL,PASSWORD,ROLE_ID) VALUES(?,?,?,?,?)";


    private UserDaoImpl() {

    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public void create(User entity) throws DaoException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_NEW_USER)) {
            setPreparedStatement(preparedStatement, entity);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("SQL exception, affected rows 0");
            }
        } catch (SQLException e) {
            throw new DaoException("SQL exception, can't create user", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public void delete(long id) throws DaoException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER)) {
            preparedStatement.setLong(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("SQL exception, affected rows 0");
            }
        } catch (SQLException e) {
            throw new DaoException("SQL exception, can't delete user", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS)) {
            while (resultSet.next()) {
                users.add(buildEntity(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("SQL exception, can't find all users", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return users;
    }

    @Override
    public void update(User entity) throws DaoException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER)) {
            setPreparedStatement(preparedStatement, entity);
            preparedStatement.setLong(6, entity.getId());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("SQL exception, affected rows 0");
            }
        } catch (SQLException e) {
            throw new DaoException("SQL exception, can't update user", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

    }

    @Override
    public Optional<User> findById(long id) throws DaoException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = buildEntity(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("SQL exception, can't find user by id", e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return Optional.ofNullable(user);
    }


    private void setPreparedStatement(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setInt(5, user.getRole());
    }

    private User buildEntity(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(USER_ID));
        user.setFirstName(resultSet.getString(FIRSTNAME));
        user.setLastName(resultSet.getString(LASTNAME));
        user.setEmail(resultSet.getString(EMAIL));
        user.setPassword(resultSet.getString(PASSWORD));
        user.setRole((resultSet.getInt(ROLE)));
        return user;
    }
}
