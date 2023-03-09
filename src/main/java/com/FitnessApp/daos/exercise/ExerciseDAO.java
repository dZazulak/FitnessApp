package com.FitnessApp.daos.exercise;

import com.FitnessApp.entities.Exercise;

public interface ExerciseDAO {

    int createMaxExerciseId();
    Exercise getExerciseByPremadeId(int exerciseId, int premadeId);
    Exercise getExerciseByUserCreatedId(int exerciseId, int userCreatedId);
    Exercise createNewExercise(Exercise exercise);
    Exercise editExercise(int exerciseId, int usercreatedId, String exerciseName, String exerciseType, String description);
    boolean deleteExercise(int exerciseId, int usercreatedId);

}
