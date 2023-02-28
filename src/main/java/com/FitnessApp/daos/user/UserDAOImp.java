package com.FitnessApp.daos.user;

import com.FitnessApp.customexceptions.UserNotFound;
import com.FitnessApp.customexceptions.UsernameAlreadyTaken;
import com.FitnessApp.entities.User;
import com.FitnessApp.util.DatabaseConnection;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImp implements UserDAO {


    @Override
    public int createMaxUserId() {
        try(Connection connection = DatabaseConnection.createConnection()){
            String sql = "select max(user_id) as highest_user_id from user_table;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int index = 0;
            if(resultSet.next()){
                index = resultSet.getInt("highest_user_id");
            }
            return index + 1;
        }
        catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }
    int user_id_counter = this.createMaxUserId();
    @Override
    public User getUserById(int userId) {
        try(Connection connection = DatabaseConnection.createConnection()){
            String sql = "select * from user_table where user_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                User user = new User();
                user.setUserId(resultSet.getInt("user_id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("passcode"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAdmin(resultSet.getBoolean("isAdmin"));
                return user;
            }
            else{
                throw new UserNotFound("User not found");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User createUser(User user) {
        user.setUserId(this.user_id_counter);
        this.user_id_counter++;
        try(Connection connection = DatabaseConnection.createConnection()){
            String sql = "insert into user_table values(?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.setBoolean(6, user.isAdmin());
            preparedStatement.executeUpdate();
            return user;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void usernameIsTaken(String username) {
        try(Connection connection = DatabaseConnection.createConnection()){
            String sql = "select * from user_table where username=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                throw new UsernameAlreadyTaken("Username is taken");
            }
            else{
                throw new UserNotFound("User not found");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int userId) {
        try(Connection connection = DatabaseConnection.createConnection()){
            User user = getUserById(userId);
            String sql = "delete from user_table where user_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.executeQuery();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try(Connection connection = DatabaseConnection.createConnection()){
            String sql = "select * from user_table;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                User user = new User();
                user.setUserId(resultSet.getInt("user_id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("passcode"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAdmin(resultSet.getBoolean("isAdmin"));

                userList.add(user);
            }
            return userList;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public User checkLoginCredentials(String username, String password) {
        try(Connection connection = DatabaseConnection.createConnection()){
            String sql = "select * from user_table where username=? and passcode=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("passcode"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getBoolean("isAdmin"));
            }
            else {
                throw new UserNotFound("User not found");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean isUserAdmin(int userId) {
        try(Connection connection = DatabaseConnection.createConnection()){
            String sql = "select isAdmin from user_table where user_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getBoolean("isAdmin");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
