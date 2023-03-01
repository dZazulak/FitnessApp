package com.FitnessApp.customexceptions;

public class InvalidPassword extends RuntimeException{
    public InvalidPassword(String message){
        super(message);
    }
}
