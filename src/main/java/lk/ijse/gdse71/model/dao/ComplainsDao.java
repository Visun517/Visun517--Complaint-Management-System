package lk.ijse.gdse71.model.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ComplainsDao {

    public ResultSet getAllComplains(String userId, DataSource dataSource) throws Exception {

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM complaints WHERE user_id = ?");
        preparedStatement.setString(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
}
