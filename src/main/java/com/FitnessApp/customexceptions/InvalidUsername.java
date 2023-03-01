package com.FitnessApp.customexceptions;

public class InvalidUsername extends RuntimeException{
    public InvalidUsername(String message){
        super(message);
    }
}
