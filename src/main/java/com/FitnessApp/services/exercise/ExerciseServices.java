package com.FitnessApp.services.exercise;

import com.FitnessApp.entities.Exercise;

public interface ExerciseServices {

    Exercise getExerciseByPremadeIdService(int exerciseId, int premadeId);
    Exercise getExerciseByUserCreatedIdService(int exerciseId, int userCreatedId);
    Exercise createNewExerciseService(Exercise exercise);
    Exercise editExerciseService(int exerciseId, int usercreatedId, String exerciseName, String exerciseType, String description);
    boolean deleteExerciseService(int exerciseId, int usercreatedId);


}
