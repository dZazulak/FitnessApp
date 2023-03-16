package com.FitnessApp.daos.premadeworkout;

import com.FitnessApp.customexceptions.PremadeWorkoutNotFound;
import com.FitnessApp.entities.Exercise;
import com.FitnessApp.entities.PremadeWorkout;
import com.FitnessApp.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PremadeDAOImp implements PremadeDAO {
    @Override
    public List<Exercise> getAllExercisesByPremadeId(int premadeId) {
        List<Exercise> exerciseList = new ArrayList<>();
        try(Connection connection = DatabaseConnection.createConnection()){
            String sql = "select * from exercise_table where premade_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, premadeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Exercise exercise = new Exercise();
                exercise.setExerciseId(resultSet.getInt("exercise_id"));
                exercise.setUserCreatedId(0);
                exercise.setPremadeId(resultSet.getInt("premade_id"));
                exercise.setExerciseName(resultSet.getString("exercise_name"));
                exercise.setExerciseType(resultSet.getString("exercise_type"));
                exercise.setDescription(resultSet.getString("description"));

                exerciseList.add(exercise);
            }
            return exerciseList;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PremadeWorkout getPremadeWorkout(int premadeId) {
        try(Connection connection = DatabaseConnection.createConnection()){
            String sql = "select * from pm_wkout_table where premade_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, premadeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                PremadeWorkout premadeWorkout = new PremadeWorkout();
                premadeWorkout.setPremadeId(resultSet.getInt("premade_id"));
                premadeWorkout.setWorkoutName(resultSet.getString("wkout_name"));
                premadeWorkout.setDescription(resultSet.getString("description"));

                return premadeWorkout;
            }
            else{
                throw new PremadeWorkoutNotFound("Premade Workout not found");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String userSelectPremadeWorkout(int premadeId, String username) {
        try(Connection connection = DatabaseConnection.createConnection()){
            String updateSql = "update pm_wkout_table set username=? where premade_id=?";
            PreparedStatement pS = connection.prepareStatement(updateSql);
            pS.setString(1, username);
            pS.setInt(2, premadeId);
            pS.execute();
            String sql = "select username from pm_wkout_table where premade_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, premadeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getString(1);
            }
            else{
                throw new PremadeWorkoutNotFound("Premade Workout not found");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
