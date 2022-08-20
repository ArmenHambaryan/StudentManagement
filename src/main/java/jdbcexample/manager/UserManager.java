package jdbcexample.manager;

import jdbcexample.db.DBConnetionProvider;
import jdbcexample.model.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserManager {

    private Connection connection;

    public UserManager() {
        connection = DBConnetionProvider.getInstance().getConnection();

    }

    public void addUser(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user(name,surname,email,password) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.executeUpdate();

        ResultSet resaltSet = preparedStatement.getGeneratedKeys();

        if (resaltSet.next()) {
            int id = resaltSet.getInt(1);
            user.setId(id);

        }

    }

    public List<User> getAllUsers() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM  user");
        List<User> users = new LinkedList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            users.add(user);

        }
        return users;
    }
     public void deleteUserById(int id) throws SQLException {
         PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id = ?");
         preparedStatement.setInt(1,id);
         preparedStatement.executeUpdate();


     }
}



