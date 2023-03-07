package com.FitnessApp.services.user;

import com.FitnessApp.entities.User;

import java.util.List;

public interface UserServices {

    User getUserByUsernameService(String username);
    User createUser(User user);
    boolean deleteUserService(String username);
    List<User> getAllUsersService();
    User checkLoginCredentialsService(String username, String password);
    boolean isUserAdminService(String username);
}
