package com.FitnessApp.app;


import com.FitnessApp.controllers.ExerciseController;
import com.FitnessApp.controllers.PremadeWorkoutController;
import com.FitnessApp.controllers.UserController;
import com.FitnessApp.controllers.UserCreatedWorkoutController;
import com.FitnessApp.daos.exercise.ExerciseDAO;
import com.FitnessApp.daos.exercise.ExerciseDAOImp;
import com.FitnessApp.daos.premadeworkout.PremadeDAO;
import com.FitnessApp.daos.premadeworkout.PremadeDAOImp;
import com.FitnessApp.daos.user.UserDAO;
import com.FitnessApp.daos.user.UserDAOImp;
import com.FitnessApp.daos.usercreatedworkout.UserCreatedDAO;
import com.FitnessApp.daos.usercreatedworkout.UserCreatedDAOImp;
import com.FitnessApp.services.exercise.ExerciseServices;
import com.FitnessApp.services.exercise.ExerciseServicesImp;
import com.FitnessApp.services.premadeworkout.PremadeServices;
import com.FitnessApp.services.premadeworkout.PremadeServicesImp;
import com.FitnessApp.services.user.UserServices;
import com.FitnessApp.services.user.UserServicesImp;
import com.FitnessApp.services.usercreatedworkout.UserCreatedServices;
import com.FitnessApp.services.usercreatedworkout.UserCreatedServicesImp;
import io.javalin.Javalin;
import io.javalin.plugin.bundled.CorsPluginConfig;

import java.security.AllPermission;


public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.plugins.enableCors(cors -> {
                cors.add(CorsPluginConfig::anyHost);
            });
            config.plugins.enableDevLogging();
        });

        UserDAO userDAO = new UserDAOImp();
        UserServices userServices = new UserServicesImp(userDAO);
        UserController userController = new UserController(userServices);

        app.get("/user/{username}", userController.getUser);
        app.get("/users", userController.getAllUsers);
        app.post("/create/user", userController.createUser);
        app.delete("/delete/user/{username}", userController.deleteUser);
        app.post("/user/login", userController.checkLoginCredentials);
        app.get("/user/admin/{username}", userController.isAdmin);

        ExerciseDAO exerciseDAO = new ExerciseDAOImp();
        ExerciseServices exerciseServices = new ExerciseServicesImp(exerciseDAO);
        ExerciseController exerciseController = new ExerciseController(exerciseServices);

        app.get("/pm_exercise/{exerciseId}/{premadeId}", exerciseController.getExerciseByPremadeId);
        app.get("/uc_exercise/{exerciseId}/{userCreatedId}", exerciseController.getExerciseByUserCreatedId);
        app.post("/create/exercise", exerciseController.createExercise);
        app.put("/edit/exercise", exerciseController.editExercise);
        app.delete("/delete/exercise/{exerciseId}/{userCreatedId}", exerciseController.deleteExercise);

        PremadeDAO premadeDAO = new PremadeDAOImp();
        PremadeServices premadeServices = new PremadeServicesImp(premadeDAO, userDAO);
        PremadeWorkoutController premadeController = new PremadeWorkoutController(premadeServices);

        app.get("/pm_workout_exercises/{premadeId}", premadeController.getAllExercises);
        app.get("/pm_workout/{premadeId}", premadeController.getPremadeWorkout);
        app.patch("/pm_workout/select", premadeController.userSelectsPremadeWorkout);

        UserCreatedDAO userCreatedDAO = new UserCreatedDAOImp();
        UserCreatedServices userCreatedServices = new UserCreatedServicesImp(userCreatedDAO, userDAO);
        UserCreatedWorkoutController userCreatedController = new UserCreatedWorkoutController(userCreatedServices);

        app.start();
    }
}
