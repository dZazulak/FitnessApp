package com.FitnessApp.services.user;

import com.FitnessApp.entities.User;

import java.util.List;

public interface UserServices {

    User getUserByIdService(int userId);
    User createUser(User user);
    boolean deleteUserService(int userId);
    List<User> getAllUsersService();
    User checkLoginCredentialsService(String username, String password);
    boolean isUserAdminService(int userId);
}
