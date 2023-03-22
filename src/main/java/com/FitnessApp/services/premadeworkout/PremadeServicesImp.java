package com.FitnessApp.services.premadeworkout;

import com.FitnessApp.customexceptions.PremadeWorkoutNotFound;
import com.FitnessApp.customexceptions.UserNotFound;
import com.FitnessApp.daos.premadeworkout.PremadeDAO;
import com.FitnessApp.daos.user.UserDAO;
import com.FitnessApp.entities.Exercise;
import com.FitnessApp.entities.PremadeWorkout;


import java.util.List;

public class PremadeServicesImp implements PremadeServices {

    PremadeDAO premadeDAO;
    UserDAO userDAO;

    public PremadeServicesImp(PremadeDAO premadeDAO, UserDAO userDAO){
        this.premadeDAO = premadeDAO;
        this.userDAO = userDAO;
    }

    @Override
    public List<Exercise> getAllExercisesByPremadeIdService(int premadeId) {
        List<Exercise> exercises;
        exercises = premadeDAO.getAllExercisesByPremadeId(premadeId);
        if(exercises.size() < 1){
            throw new PremadeWorkoutNotFound("Premade Workout not found");
        }
        else{
            return exercises;
        }
    }

    @Override
    public PremadeWorkout getPremadeWorkoutService(int premadeId) {
        try{
            return premadeDAO.getPremadeWorkout(premadeId);
        }
        catch (PremadeWorkoutNotFound e){
            throw new PremadeWorkoutNotFound("Premade Workout not found");
        }
    }

    @Override
    public String userSelectPremadeWorkoutService(int premadeId, String username) {
        try{
            if(!username.isBlank()){
                userDAO.getUserByUsername(username);
                premadeDAO.getPremadeWorkout(premadeId);
                return premadeDAO.userSelectPremadeWorkout(premadeId, username);
            }
            else{
                throw new UserNotFound("User not found");
            }
        }
        catch (PremadeWorkoutNotFound e ){
            throw new PremadeWorkoutNotFound("Premade Workout not found");
        }
        catch (UserNotFound e){
            throw new UserNotFound("User not found");
        }
    }

    @Override
    public String userDeselectPremadeWorkoutService(int premadeId, String username) {
        try{
            if(!username.isBlank()){
                userDAO.getUserByUsername(username);
                premadeDAO.getPremadeWorkout(premadeId);
                return premadeDAO.userDeselectPremadeWorkout(premadeId, username);
            }
            else{
                throw new UserNotFound("User not found");
            }
        }
        catch (PremadeWorkoutNotFound e ){
            throw new PremadeWorkoutNotFound("Premade Workout not found");
        }
        catch (UserNotFound e){
            throw new UserNotFound("User not found");
        }
    }
}
