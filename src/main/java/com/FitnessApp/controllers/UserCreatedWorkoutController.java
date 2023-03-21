package com.FitnessApp.controllers;


import java.util.ArrayList;

import com.FitnessApp.services.usercreatedworkout.UserCreatedServices;
import com.google.gson.Gson;
import io.javalin.http.Handler;
public class UserCreatedWorkoutController {

    UserCreatedServices userCreatedServices;

    public UserCreatedWorkoutController(UserCreatedServices userCreatedServices){
        this.userCreatedServices = userCreatedServices;
    }
}
