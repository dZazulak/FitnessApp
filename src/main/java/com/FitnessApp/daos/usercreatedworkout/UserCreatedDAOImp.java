package com.FitnessApp.daos.usercreatedworkout;

import com.FitnessApp.customexceptions.UserCreatedWorkoutNotFound;
import com.FitnessApp.customexceptions.UserNotFound;
import com.FitnessApp.entities.Exercise;
import com.FitnessApp.entities.User;
import com.FitnessApp.entities.UserCreatedWorkout;
import com.FitnessApp.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserCreatedDAOImp implements UserCreatedDAO {
    @Override
    public int createMaxUserCreatedId() {
        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "select max(usercreated_id) as highest_usercreated_id from uc_wkout_table;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int index = 0;
            if (resultSet.next()) {
                index = resultSet.getInt("highest_usercreated_id");
            }
            return index + 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    int userCreatedId = this.createMaxUserCreatedId();

    @Override
    public UserCreatedWorkout createNewWorkout(UserCreatedWorkout userCreatedWorkout) {
        userCreatedWorkout.setUserCreatedId(this.userCreatedId);
        this.userCreatedId++;
        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "insert into uc_wkout_table values(?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userCreatedWorkout.getUserCreatedId());
            preparedStatement.setString(2, userCreatedWorkout.getWorkoutName());
            preparedStatement.setString(3, userCreatedWorkout.getUsername());
            preparedStatement.setString(4, userCreatedWorkout.getDescription());
            preparedStatement.setDate(5, Date.valueOf(userCreatedWorkout.getDateCreated()));
            preparedStatement.executeUpdate();
            return userCreatedWorkout;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Exercise> getAllExercisesByUserCreatedId(int userCreatedId) {
        List<Exercise> exerciseList = new ArrayList<>();
        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "select * from exercise_table where usercreated_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userCreatedId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setExerciseId(resultSet.getInt("exercise_id"));
                exercise.setUserCreatedId(resultSet.getInt("usercreated_id"));
                exercise.setPremadeId(0);
                exercise.setExerciseName(resultSet.getString("exercise_name"));
                exercise.setExerciseType(resultSet.getString("exercise_type"));
                exercise.setDescription(resultSet.getString("description"));

                exerciseList.add(exercise);
            }
            return exerciseList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserCreatedWorkout getUserCreatedWorkout(int userCreatedId) {
        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "select * from uc_wkout_table where usercreated_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userCreatedId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                UserCreatedWorkout userCreatedWorkout = new UserCreatedWorkout();
                userCreatedWorkout.setUserCreatedId(resultSet.getInt("usercreated_id"));
                userCreatedWorkout.setWorkoutName(resultSet.getString("wkout_name"));
                userCreatedWorkout.setUsername(resultSet.getString("username"));
                userCreatedWorkout.setDescription(resultSet.getString("description"));
                userCreatedWorkout.setDateCreated("date_created");
                return userCreatedWorkout;
            } else {
                throw new UserCreatedWorkoutNotFound("User Created Workout not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteWorkout(UserCreatedWorkout userCreatedWorkout) {
        try (Connection connection = DatabaseConnection.createConnection()) {
            UserCreatedWorkout workout = getUserCreatedWorkout(userCreatedWorkout.getUserCreatedId());
            String sql = "delete from uc_wkout_table where usercreated_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userCreatedWorkout.getUserCreatedId());
            preparedStatement.executeQuery();
        } catch (UserCreatedWorkoutNotFound e) {
            throw new UserCreatedWorkoutNotFound("User Created Workout not found");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
