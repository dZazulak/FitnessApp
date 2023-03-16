package com.FitnessApp.customexceptions;

public class UserCreatedWorkoutNotFound extends RuntimeException{
    public UserCreatedWorkoutNotFound(String message){
        super(message);
    }
}
