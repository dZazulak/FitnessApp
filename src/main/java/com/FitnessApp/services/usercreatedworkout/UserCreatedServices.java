package com.FitnessApp.services.usercreatedworkout;

import com.FitnessApp.entities.Exercise;
import com.FitnessApp.entities.UserCreatedWorkout;

import java.util.List;

public interface UserCreatedServices {
    UserCreatedWorkout createNewWorkout(UserCreatedWorkout userCreatedWorkout);
    List<Exercise> getAllExercisesByUserCreatedId(int userCreatedId);
    UserCreatedWorkout getUserCreatedWorkout(int userCreatedId);
}
