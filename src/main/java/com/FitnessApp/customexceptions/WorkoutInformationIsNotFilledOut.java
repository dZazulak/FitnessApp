package com.FitnessApp.customexceptions;

public class WorkoutInformationIsNotFilledOut extends RuntimeException{
    public WorkoutInformationIsNotFilledOut(String message){
        super(message);
    }
}
