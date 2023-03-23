package com.FitnessApp.services.usercreatedworkout;

import com.FitnessApp.customexceptions.UserCreatedWorkoutNotFound;
import com.FitnessApp.customexceptions.UserNotFound;
import com.FitnessApp.customexceptions.WorkoutInformationIsNotFilledOut;
import com.FitnessApp.daos.user.UserDAO;
import com.FitnessApp.daos.usercreatedworkout.UserCreatedDAO;
import com.FitnessApp.entities.Exercise;
import com.FitnessApp.entities.UserCreatedWorkout;

import java.util.List;

public class UserCreatedServicesImp implements UserCreatedServices {

    UserCreatedDAO userCreatedDAO;
    UserDAO userDAO;

    public UserCreatedServicesImp(UserCreatedDAO userCreatedDAO, UserDAO userDAO){
        this.userCreatedDAO = userCreatedDAO;
        this.userDAO = userDAO;
    }

    @Override
    public UserCreatedWorkout createNewWorkout(UserCreatedWorkout userCreatedWorkout) {
        try {
            if (userCreatedWorkout.getWorkoutName().isBlank() || userCreatedWorkout.getUsername().isBlank() || userCreatedWorkout.getUserCreatedId() < 0) {
                throw new WorkoutInformationIsNotFilledOut("Workout Information is not filled out");
            } else {
                userCreatedDAO.getUserCreatedWorkout(userCreatedWorkout.getUserCreatedId());
                userDAO.getUserByUsername(userCreatedWorkout.getUsername());
                return userCreatedDAO.createNewWorkout(userCreatedWorkout);
            }
        }
        catch (UserCreatedWorkoutNotFound e){
            throw new UserCreatedWorkoutNotFound("User Created Workout not found");
        }
        catch (UserNotFound e){
            throw new UserNotFound("User not found");
        }
    }

    @Override
    public List<Exercise> getAllExercisesByUserCreatedId(int userCreatedId) {
        List<Exercise> exercises;
        exercises = userCreatedDAO.getAllExercisesByUserCreatedId(userCreatedId);
        if(exercises.size() < 1){
            throw new UserCreatedWorkoutNotFound("User Created Workout not found");
        }
        else{
            return exercises;
        }
    }

    @Override
    public UserCreatedWorkout getUserCreatedWorkout(int userCreatedId) {
        try{
            return userCreatedDAO.getUserCreatedWorkout(userCreatedId);
        }
        catch (UserCreatedWorkoutNotFound e){
            throw new UserCreatedWorkoutNotFound("User Created Workout not found");
        }
    }

    @Override
    public boolean deleteWorkout(UserCreatedWorkout userCreatedWorkout) {
        try{
            userCreatedDAO.getUserCreatedWorkout(userCreatedWorkout.getUserCreatedId());
            return userCreatedDAO.deleteWorkout(userCreatedWorkout);
        }
        catch (UserCreatedWorkoutNotFound e){
            throw new UserNotFound("User Created Workout not found");
        }
    }
}
