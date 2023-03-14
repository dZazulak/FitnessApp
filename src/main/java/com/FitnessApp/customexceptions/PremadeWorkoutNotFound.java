package com.FitnessApp.customexceptions;

public class PremadeWorkoutNotFound extends RuntimeException{
    public PremadeWorkoutNotFound(String message){
        super(message);
    }
}
