package com.FitnessApp.services.premadeworkout;

import com.FitnessApp.entities.Exercise;
import com.FitnessApp.entities.PremadeWorkout;

import java.util.List;

public interface PremadeServices {

    List<Exercise> getAllExercisesByPremadeIdService(int premadeId);

    PremadeWorkout getPremadeWorkoutService(int premadeId);

    String userSelectPremadeWorkoutService(int premadeId, String username);
}
