package com.FitnessApp.controllers;

import com.FitnessApp.customexceptions.ExerciseInformationIsNotFilledOut;
import com.FitnessApp.customexceptions.PremadeExerciseNotFound;
import com.FitnessApp.customexceptions.UserCreatedExerciseNotFound;
import com.FitnessApp.entities.Exercise;
import com.FitnessApp.services.exercise.ExerciseServices;

import java.util.ArrayList;
import com.google.gson.Gson;
import io.javalin.http.Handler;

public class ExerciseController {
    ExerciseServices exerciseServices;

    public ExerciseController(ExerciseServices exerciseServices){
        this.exerciseServices = exerciseServices;
    }

    public Handler getExerciseByPremadeId = ctx -> {
        String exerciseId = ctx.pathParam("exerciseId");
        String premadeId = ctx.pathParam("premadeId");
        ArrayList<Exercise> exerciseArrayList = new ArrayList<>();
        try{
            Exercise exercise = this.exerciseServices.getExerciseByPremadeIdService(Integer.parseInt(exerciseId), Integer.parseInt(premadeId));
            Gson gson = new Gson();
            exerciseArrayList.add(exercise);
            ctx.result(gson.toJson(exerciseArrayList));
            ctx.status(200);
        }
        catch (PremadeExerciseNotFound e){
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };

    public Handler getExerciseByUserCreatedId = ctx -> {
        String exerciseId = ctx.pathParam("exerciseId");
        String userCreatedId = ctx.pathParam("userCreatedId");
        ArrayList<Exercise> exerciseArrayList = new ArrayList<>();
        try{
            Exercise exercise = this.exerciseServices.getExerciseByUserCreatedIdService(Integer.parseInt(exerciseId), Integer.parseInt(userCreatedId));
            Gson gson = new Gson();
            exerciseArrayList.add(exercise);
            ctx.result(gson.toJson(exerciseArrayList));
            ctx.status(200);
        }
        catch (UserCreatedExerciseNotFound e){
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };

    public Handler createExercise = ctx ->{
        try {
            Gson gson = new Gson();
            Exercise newExercise = gson.fromJson(ctx.body(), Exercise.class);
            Exercise createdExercise = this.exerciseServices.createNewExerciseService(newExercise);
            ctx.result(gson.toJson(createdExercise));
            ctx.status(201);
        }
        catch (ExerciseInformationIsNotFilledOut e){
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };

    public Handler editExercise = ctx -> {
        try{
            Gson gson = new Gson();
            Exercise newExercise = gson.fromJson(ctx.body(), Exercise.class);
            Exercise editedExercise = this.exerciseServices.editExerciseService(newExercise.getExerciseId(), newExercise.getUserCreatedId(), newExercise.getExerciseName(), newExercise.getExerciseType(), newExercise.getDescription());
            ctx.result(gson.toJson(editedExercise));
            ctx.status(200);
        }
        catch (UserCreatedExerciseNotFound | ExerciseInformationIsNotFilledOut e){
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };

    public Handler deleteExercise = ctx -> {
        String exerciseId = ctx.pathParam("exerciseId");
        String userCreatedId = ctx.pathParam("userCreatedId");
        try{
            Boolean deleted = this.exerciseServices.deleteExerciseService(Integer.parseInt(exerciseId), Integer.parseInt(userCreatedId));
            Gson gson = new Gson();
            if(deleted){
                ctx.result(gson.toJson("Deleted"));
                ctx.status(200);
            }
        }
        catch (UserCreatedExerciseNotFound e){
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };


}
