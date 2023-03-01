package com.FitnessApp.customexceptions;

public class AccountInformationIsNotFilledOut extends RuntimeException{
    public AccountInformationIsNotFilledOut(String message){
        super(message);
    }
}
