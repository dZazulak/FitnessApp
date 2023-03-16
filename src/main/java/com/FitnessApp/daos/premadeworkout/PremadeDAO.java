package com.FitnessApp.daos.premadeworkout;

import com.FitnessApp.entities.Exercise;
import com.FitnessApp.entities.PremadeWorkout;

import java.util.List;

public interface PremadeDAO {

    List<Exercise> getAllExercisesByPremadeId(int premadeId);

    PremadeWorkout getPremadeWorkout(int premadeId);

    String userSelectPremadeWorkout(int premadeId, String username);

}
