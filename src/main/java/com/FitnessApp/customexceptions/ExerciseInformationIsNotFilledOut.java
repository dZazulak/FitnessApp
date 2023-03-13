package com.FitnessApp.customexceptions;

public class ExerciseInformationIsNotFilledOut extends RuntimeException{
    public ExerciseInformationIsNotFilledOut(String message){
        super(message);
    }
}
