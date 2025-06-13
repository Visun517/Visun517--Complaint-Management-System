package lk.ijse.gdse71.model.dao;

import lk.ijse.gdse71.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public int saveUser(User user , DataSource dataSource) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (id,username, password, role) VALUES (?, ?, ? ,?)");
        preparedStatement.setString(1,user.getId());
        preparedStatement.setString(2, user.getUserName());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, user.getRole());
        int i = preparedStatement.executeUpdate();
        return i;
    }

    public ResultSet loginUser(User user , DataSource dataSource) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
        preparedStatement.setString(1,user.getUserName());
        preparedStatement.setString(2, user.getPassword());
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
}
