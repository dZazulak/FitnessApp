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
            String userJson = gson.toJson(user);
            ctx.result(userJson);
            ctx.status(200);
        } catch (UserNotFound e){
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };

    public Handler getAllUsers = ctx ->{
        List<User> users = this.userServices.getAllUsersService();
        Gson gson = new Gson();
        String usersJson = gson.toJson(users);
        ctx.result(usersJson);
        ctx.status(200);
    };

    public Handler createUser = ctx ->{
        try {
            Gson gson = new Gson();
            User newUser = gson.fromJson(ctx.body(), User.class);
            User createdUser = this.userServices.createUser(newUser);
            String createdUserJson = gson.toJson(createdUser);
            ctx.result(createdUserJson);
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
                String userDeleted = gson.toJson("Deleted");
                ctx.result(userDeleted);
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
            String loginValid = gson.toJson("Credentials valid");
            ctx.result(loginValid);
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
                String verified = gson.toJson("User is an admin");
                ctx.result(verified);
                ctx.status(200);
            }
            else{
                String unverified = gson.toJson("User is not an admin");
                ctx.result(unverified);
                ctx.status(200);
            }
        }
        catch (UserNotFound e){
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };
}
