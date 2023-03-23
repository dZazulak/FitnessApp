package com.FitnessApp.controllers;


import java.util.ArrayList;
import java.util.List;

import com.FitnessApp.customexceptions.PremadeWorkoutNotFound;
import com.FitnessApp.customexceptions.UserCreatedWorkoutNotFound;
import com.FitnessApp.customexceptions.UserNotFound;
import com.FitnessApp.customexceptions.WorkoutInformationIsNotFilledOut;
import com.FitnessApp.entities.Exercise;
import com.FitnessApp.entities.PremadeWorkout;
import com.FitnessApp.entities.UserCreatedWorkout;
import com.FitnessApp.services.usercreatedworkout.UserCreatedServices;
import com.google.gson.Gson;
import io.javalin.http.Handler;
public class UserCreatedWorkoutController {

    UserCreatedServices userCreatedServices;

    public UserCreatedWorkoutController(UserCreatedServices userCreatedServices){
        this.userCreatedServices = userCreatedServices;
    }

    public Handler getAllExercises = ctx -> {
        String userCreatedId = ctx.pathParam("userCreatedId");
        try {
            List<Exercise> exercises = this.userCreatedServices.getAllExercisesByUserCreatedId(Integer.parseInt(userCreatedId));
            Gson gson = new Gson();
            ctx.result(gson.toJson(exercises));
            ctx.status(200);
        } catch (UserCreatedWorkoutNotFound e) {
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };

    public Handler getUserCreatedWorkout = ctx -> {
        String userCreatedId = ctx.pathParam("userCreatedId");
        List<UserCreatedWorkout> userCreatedWorkouts = new ArrayList<>();
        try {
            UserCreatedWorkout workout = this.userCreatedServices.getUserCreatedWorkout(Integer.parseInt(userCreatedId));
            userCreatedWorkouts.add(workout);
            Gson gson = new Gson();
            ctx.result(gson.toJson(userCreatedWorkouts));
            ctx.status(200);
        } catch (UserCreatedWorkoutNotFound e) {
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };

    public Handler createWorkout = ctx -> {
      try{
          Gson gson = new Gson();
          UserCreatedWorkout newWorkout = gson.fromJson(ctx.body(), UserCreatedWorkout.class);
          UserCreatedWorkout createdWorkout = this.userCreatedServices.createNewWorkout(newWorkout);
          ctx.result(gson.toJson(createdWorkout));
          ctx.status(200);
      }
      catch (WorkoutInformationIsNotFilledOut e){
          ctx.result(e.getMessage());
          ctx.status(404);
      }
    };

    public Handler deleteWorkout = ctx -> {
        try {
            Gson gson = new Gson();
            UserCreatedWorkout json = gson.fromJson(ctx.body(), UserCreatedWorkout.class);
            UserCreatedWorkout selectedWorkout = this.userCreatedServices.getUserCreatedWorkout(json.getUserCreatedId());
            Boolean deleted = this.userCreatedServices.deleteWorkout(selectedWorkout);
            if(deleted){
                ctx.result(gson.toJson("Deleted"));
                ctx.status(200);
            }
        } catch (UserCreatedWorkoutNotFound e) {
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };
}
