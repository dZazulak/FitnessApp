package com.FitnessApp.daos.usercreatedworkout;

import com.FitnessApp.entities.Exercise;
import com.FitnessApp.entities.PremadeWorkout;
import com.FitnessApp.entities.UserCreatedWorkout;

import java.util.List;

public interface UserCreatedDAO {

    int createMaxUserCreatedId();

    UserCreatedWorkout createNewWorkout(UserCreatedWorkout userCreatedWorkout);
    List<Exercise> getAllExercisesByUserCreatedId(int userCreatedId);

    UserCreatedWorkout getUserCreatedWorkout(int userCreatedId);

    boolean deleteWorkout(UserCreatedWorkout userCreatedWorkout);

}
