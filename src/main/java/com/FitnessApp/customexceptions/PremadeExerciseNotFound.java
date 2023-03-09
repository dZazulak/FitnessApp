package com.FitnessApp.customexceptions;

public class PremadeExerciseNotFound extends RuntimeException{
    public PremadeExerciseNotFound(String message){
        super(message);
    }
}
