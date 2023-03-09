package com.FitnessApp.daos.exercise;

import com.FitnessApp.customexceptions.PremadeExerciseNotFound;
import com.FitnessApp.customexceptions.UserCreatedExerciseNotFound;
import com.FitnessApp.customexceptions.UserNotFound;
import com.FitnessApp.entities.Exercise;
import com.FitnessApp.entities.User;
import com.FitnessApp.util.DatabaseConnection;

import javax.xml.crypto.Data;
import java.sql.*;

public class ExerciseDAOImp implements ExerciseDAO {

    @Override
    public int createMaxExerciseId() {
        try(Connection connection = DatabaseConnection.createConnection()){
            String sql = "select max(exercise_id) as highest_exercise_id from exercise_table;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int index = 0;
            if(resultSet.next()){
                index = resultSet.getInt("highest_exercise_id");
            }
            return index + 1;
        }
        catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }
    int exercise_id_counter = this.createMaxExerciseId();

    @Override
    public Exercise getExerciseByPremadeId(int exerciseId, int premadeId) {
        try(Connection connection = DatabaseConnection.createConnection()){
            String sql = "select * from exercise_table where exercise_id=? and premade_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, exerciseId);
            preparedStatement.setInt(2, premadeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Exercise exercise = new Exercise();
                exercise.setExerciseId(resultSet.getInt("exercise_id"));
                exercise.setUserCreatedId(0);
                exercise.setPremadeId(resultSet.getInt("premade_id"));
                exercise.setExerciseName(resultSet.getString("exercise_name"));
                exercise.setExerciseType(resultSet.getString("exercise_type"));
                exercise.setDescription(resultSet.getString("description"));
                return exercise;
            }
            else{
                throw new PremadeExerciseNotFound("Premade Exercise not found");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Exercise getExerciseByUserCreatedId(int exerciseId, int userCreatedId) {
        try(Connection connection = DatabaseConnection.createConnection()){
            String sql = "select * from exercise_table where exercise_id=? and usercreated_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, exerciseId);
            preparedStatement.setInt(2, userCreatedId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Exercise exercise = new Exercise();
                exercise.setExerciseId(resultSet.getInt("exercise_id"));
                exercise.setUserCreatedId(resultSet.getInt("usercreated_id"));
                exercise.setPremadeId(0);
                exercise.setExerciseName(resultSet.getString("exercise_name"));
                exercise.setExerciseType(resultSet.getString("exercise_type"));
                exercise.setDescription(resultSet.getString("description"));
                return exercise;
            }
            else{
                throw new UserCreatedExerciseNotFound("User Created Exercise not found");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Exercise createNewExercise(Exercise exercise) {
        exercise.setExerciseId(this.exercise_id_counter);
        this.exercise_id_counter++;
        try(Connection connection = DatabaseConnection.createConnection()){
            String sql = "insert into exercise_table values(?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, exercise.getExerciseId());
            preparedStatement.setInt(2, exercise.getUserCreatedId());
            preparedStatement.setInt(3, 0);
            preparedStatement.setString(4, exercise.getExerciseName());
            preparedStatement.setString(5, exercise.getExerciseType());
            preparedStatement.setString(6, exercise.getDescription());
            preparedStatement.executeUpdate();
            return exercise;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Exercise editExercise(int exerciseId, int usercreatedId, String exerciseName, String exerciseType, String description) {
        try(Connection connection = DatabaseConnection.createConnection()) {
            String sql = "update exercise_table set exercise_name=?, exercise_type=?, description=? where exercise_id=? and usercreated_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, exerciseName);
            preparedStatement.setString(2, exerciseType);
            preparedStatement.setString(3, description);
            preparedStatement.setInt(4, exerciseId);
            preparedStatement.setInt(5, usercreatedId);
            preparedStatement.executeUpdate();

            Exercise exercise = getExerciseByUserCreatedId(exerciseId, usercreatedId);
            return exercise;
        }
        catch (SQLException e) {
        e.printStackTrace();
        return null;
        }
    }

    @Override
    public boolean deleteExercise(int exerciseId, int usercreatedId) {
        try(Connection connection = DatabaseConnection.createConnection()){
            Exercise exercise = getExerciseByUserCreatedId(exerciseId, usercreatedId);
            String sql = "delete from exercise_table where exercise_id=? and usercreated_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, exercise.getExerciseId());
            preparedStatement.setInt(2, exercise.getUserCreatedId());
            preparedStatement.executeQuery();
        }
        catch(UserCreatedExerciseNotFound e){
            throw new UserCreatedExerciseNotFound("User Created Exercise not found");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
}
