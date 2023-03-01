package com.FitnessApp.services.user;

import com.FitnessApp.customexceptions.*;
import com.FitnessApp.daos.user.UserDAO;
import com.FitnessApp.entities.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class UserServicesImp implements UserServices{

    UserDAO userDAO;

    public UserServicesImp(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Override
    public User getUserByIdService(int userId) {
        try{
            return this.userDAO.getUserById(userId);
        }
        catch (UserNotFound e){
            throw new UserNotFound("User not found");
        }
    }

    @Override
    public User createUser(User user) {
        if (userDAO.usernameIsTaken(user.getUsername())){
            throw new UsernameAlreadyTaken("Username is taken");
        }
        else if (user.getUsername().isBlank() || user.getPassword().isBlank() || user.getFirstName().isBlank() || user.getLastName().isBlank()) {
            throw new AccountInformationIsNotFilledOut("Account Information incomplete");
        }
        else{
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));
            return userDAO.createUser(user);
        }
    }

    @Override
    public boolean deleteUserService(int userId) {
        try{
            userDAO.getUserById(userId);
            return userDAO.deleteUser(userId);
        }
        catch (UserNotFound e){
            throw new UserNotFound("User not found");
        }

    }

    @Override
    public List<User> getAllUsersService() {
        return this.userDAO.getAllUsers();
    }

    @Override
    public User checkLoginCredentialsService(String username, String password) {
        List<User> userList;
        try{
            userList = this.userDAO.getAllUsers();
            for(User current_user : userList){
                if(current_user.getUsername().equals(username)){

                    if(BCrypt.checkpw(password, current_user.getPassword())){
                        return this.userDAO.checkLoginCredentials(username, current_user.getPassword());
                    }
                    else{
                        throw new IllegalArgumentException("Invalid salt version");
                    }
                }
            }
            throw new InvalidUsername("Invalid username");
        }
        catch (InvalidUsername e){
            throw new InvalidUsername("Invalid username");
        }
    }

    @Override
    public boolean isUserAdminService(int userId) {
        try{
            userDAO.getUserById(userId);
            return userDAO.isUserAdmin(userId);
        }
        catch(UserNotFound e){
            throw new UserNotFound("User not found");
        }
    }
}
