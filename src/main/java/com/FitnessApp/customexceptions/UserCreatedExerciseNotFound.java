package com.FitnessApp.customexceptions;

public class UserCreatedExerciseNotFound extends RuntimeException{
    public UserCreatedExerciseNotFound(String message){
        super(message);
    }
}
