package com.FitnessApp.app;


import com.FitnessApp.controllers.UserController;
import com.FitnessApp.daos.user.UserDAO;
import com.FitnessApp.daos.user.UserDAOImp;
import com.FitnessApp.services.user.UserServices;
import com.FitnessApp.services.user.UserServicesImp;
import io.javalin.Javalin;


public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        UserDAO userDAO = new UserDAOImp();
        UserServices userServices = new UserServicesImp(userDAO);
        UserController userController = new UserController(userServices);

        app.get("/user/{userId}", userController.getUser);
        app.get("/users", userController.getAllUsers);
        app.post("/create/user", userController.createUser);
        app.delete("delete/user/{userId}", userController.deleteUser);
        app.post("/user/login", userController.checkLoginCredentials);
        app.get("/user/admin/{userId}", userController.isAdmin);

        app.start();
    }
}
