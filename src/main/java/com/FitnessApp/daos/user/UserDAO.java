package com.FitnessApp.daos.user;

import com.FitnessApp.entities.User;

import java.util.List;

public interface UserDAO {

    int createMaxUserId();
    User getUserByUsername(String username);
    User createUser(User user);
    boolean usernameIsTaken(String username);
    boolean deleteUser(String username);
    List<User> getAllUsers();
    User checkLoginCredentials(String username, String password);
    boolean isUserAdmin(String username);


}
