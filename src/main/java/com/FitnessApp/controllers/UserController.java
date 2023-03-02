package com.FitnessApp.controllers;

import com.FitnessApp.customexceptions.InvalidUsername;
import com.FitnessApp.customexceptions.UserNotFound;
import com.FitnessApp.customexceptions.UsernameAlreadyTaken;
import com.FitnessApp.entities.User;
import com.google.gson.Gson;
import io.javalin.http.Handler;
import com.FitnessApp.services.user.UserServices;

import java.util.List;


public class UserController {

    UserServices userServices;

    public UserController(UserServices userServices){
        this.userServices = userServices;
    }

    public Handler getUser = ctx -> {
        int userId = Integer.parseInt(ctx.pathParam("userId"));
        try{
            User user = this.userServices.getUserByIdService(userId);
            Gson gson = new Gson();
            ctx.result(gson.toJson(user));
            ctx.status(200);
        } catch (UserNotFound e){
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };

    public Handler getAllUsers = ctx ->{
        List<User> users = this.userServices.getAllUsersService();
        Gson gson = new Gson();
        ctx.result(gson.toJson(users));
        ctx.status(200);
    };

    public Handler createUser = ctx ->{
        try {
            Gson gson = new Gson();
            User newUser = gson.fromJson(ctx.body(), User.class);
            User createdUser = this.userServices.createUser(newUser);
            ctx.result(gson.toJson(createdUser));
            ctx.status(201);
        }
        catch (UsernameAlreadyTaken e){
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };

    public Handler deleteUser = ctx ->{
        int userId = Integer.parseInt(ctx.pathParam("userId"));
        try{
            Boolean deleted = this.userServices.deleteUserService(userId);
            Gson gson = new Gson();
            if(deleted){
                ctx.result(gson.toJson("Deleted"));
                ctx.status(200);
            }
        }
        catch (UserNotFound e){
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };

    public Handler checkLoginCredentials = ctx ->{
        try{
            Gson gson = new Gson();
            User json = gson.fromJson(ctx.body(), User.class);
            String username = json.getUsername();
            String password = json.getPassword();
            this.userServices.checkLoginCredentialsService(username, password);
            ctx.result(gson.toJson("Credentials valid"));
            ctx.status(200);
        }
        catch (IllegalArgumentException | InvalidUsername e){
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };

    public Handler isAdmin = ctx ->{
        int userId = Integer.parseInt(ctx.pathParam("userId"));
        try {
            Boolean admin = this.userServices.isUserAdminService(userId);
            Gson gson = new Gson();
            if(admin){
                ctx.result(gson.toJson("User is an admin"));
                ctx.status(200);
            }
            else{
                ctx.result(gson.toJson("User is not an admin"));
                ctx.status(200);
            }
        }
        catch (UserNotFound e){
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };
}
