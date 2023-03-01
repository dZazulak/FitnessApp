package com.FitnessApp.daos.user;

import com.FitnessApp.entities.User;

import java.util.List;

public interface UserDAO {

    int createMaxUserId();
    User getUserById(int userId);
    User createUser(User user);
    boolean usernameIsTaken(String username);
    boolean deleteUser(int userId);
    List<User> getAllUsers();
    User checkLoginCredentials(String username, String password);
    boolean isUserAdmin(int userId);


}
