package com.FitnessApp.customexceptions;

public class UsernameAlreadyTaken extends RuntimeException {
    public UsernameAlreadyTaken(String message){
        super(message);
    }
}
