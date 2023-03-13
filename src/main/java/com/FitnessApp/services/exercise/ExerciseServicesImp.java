package com.FitnessApp.services.exercise;

import com.FitnessApp.customexceptions.ExerciseInformationIsNotFilledOut;
import com.FitnessApp.customexceptions.PremadeExerciseNotFound;
import com.FitnessApp.customexceptions.UserCreatedExerciseNotFound;
import com.FitnessApp.customexceptions.UserNotFound;
import com.FitnessApp.daos.exercise.ExerciseDAO;
import com.FitnessApp.entities.Exercise;

public class ExerciseServicesImp implements ExerciseServices {

    ExerciseDAO exerciseDAO;

    public ExerciseServicesImp(ExerciseDAO exerciseDAO){
        this.exerciseDAO = exerciseDAO;
    }
    @Override
    public Exercise getExerciseByPremadeIdService(int exerciseId, int premadeId) {
        try{
            return exerciseDAO.getExerciseByPremadeId(exerciseId, premadeId);
        }
        catch(PremadeExerciseNotFound e){
            throw new PremadeExerciseNotFound("Premade Exercise not found");
        }
    }

    @Override
    public Exercise getExerciseByUserCreatedIdService(int exerciseId, int userCreatedId) {
        try{
            return exerciseDAO.getExerciseByUserCreatedId(exerciseId, userCreatedId);
        }
        catch (UserCreatedExerciseNotFound e){
            throw new UserCreatedExerciseNotFound("User Created Exercise not found");
        }
    }

    @Override
    public Exercise createNewExerciseService(Exercise exercise) {
        if(exercise.getExerciseName().isBlank() || exercise.getExerciseType().isBlank()){
            throw new ExerciseInformationIsNotFilledOut("Exercise information is not complete");
        }
        else{
            return exerciseDAO.createNewExercise(exercise);
        }
    }

    @Override
    public Exercise editExerciseService(int exerciseId, int usercreatedId, String exerciseName, String exerciseType, String description) {
        try{
            exerciseDAO.getExerciseByUserCreatedId(exerciseId, usercreatedId);
            if(exerciseName.isBlank() || exerciseType.isBlank()){
                throw new ExerciseInformationIsNotFilledOut("Exercise information is not complete");
            }
            else{
                return exerciseDAO.editExercise(exerciseId, usercreatedId, exerciseName, exerciseType, description);
            }
        }
        catch (UserCreatedExerciseNotFound e){
            throw new UserCreatedExerciseNotFound("User Created Exercise not found");
        }
    }

    @Override
    public boolean deleteExerciseService(int exerciseId, int usercreatedId) {
        try{
            exerciseDAO.getExerciseByUserCreatedId(exerciseId, usercreatedId);
            return exerciseDAO.deleteExercise(exerciseId, usercreatedId);
        }
        catch (UserCreatedExerciseNotFound e){
            throw new UserCreatedExerciseNotFound("User Created Exercise not found");
        }
    }
}
