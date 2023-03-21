package com.FitnessApp.controllers;

import com.FitnessApp.customexceptions.PremadeWorkoutNotFound;
import com.FitnessApp.customexceptions.UserNotFound;
import com.FitnessApp.entities.Exercise;
import com.FitnessApp.entities.PremadeWorkout;
import com.FitnessApp.services.premadeworkout.PremadeServices;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;
import io.javalin.http.Handler;
public class PremadeWorkoutController {

    PremadeServices premadeServices;

    public PremadeWorkoutController(PremadeServices premadeServices) {
        this.premadeServices = premadeServices;
    }

    public Handler getAllExercises = ctx -> {
        String premadeId = ctx.pathParam("premadeId");
        try {
            List<Exercise> exercises = this.premadeServices.getAllExercisesByPremadeIdService(Integer.parseInt(premadeId));
            Gson gson = new Gson();
            ctx.result(gson.toJson(exercises));
            ctx.status(200);
        } catch (PremadeWorkoutNotFound e) {
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };

    public Handler getPremadeWorkout = ctx -> {
        String premadeId = ctx.pathParam("premadeId");
        List<PremadeWorkout> premadeWorkouts = new ArrayList<>();
        try {
            PremadeWorkout workout = this.premadeServices.getPremadeWorkoutService(Integer.parseInt(premadeId));
            premadeWorkouts.add(workout);
            Gson gson = new Gson();
            ctx.result(gson.toJson(premadeWorkouts));
            ctx.status(200);
        } catch (PremadeWorkoutNotFound e) {
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };

    public Handler userSelectsPremadeWorkout = ctx -> {
        try {
            Gson gson = new Gson();
            PremadeWorkout json = gson.fromJson(ctx.body(), PremadeWorkout.class);
            String selectedUser = this.premadeServices.userSelectPremadeWorkoutService(json.getPremadeId(), json.getUsername());
            ctx.result(gson.toJson(selectedUser));
            ctx.status(200);
        } catch (PremadeWorkoutNotFound | UserNotFound e) {
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };
}