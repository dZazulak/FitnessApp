package com.FitnessApp.tests.service;

import com.FitnessApp.customexceptions.*;
import com.FitnessApp.daos.user.UserDAO;
import com.FitnessApp.daos.user.UserDAOImp;
import com.FitnessApp.entities.User;
import com.FitnessApp.services.user.UserServices;
import com.FitnessApp.services.user.UserServicesImp;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UserServiceTests {

    static UserDAO userDAO = new UserDAOImp();
    static UserServices userServices = new UserServicesImp(userDAO);

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getBadUserId(){
        expectedException.expect(UserNotFound.class);
        expectedException.expectMessage("User not found");
        userServices.getUserByUsernameService("NoUser");
    }

    @Test
    public void badUsernameCreatingAccount(){
        User user = new User(1, "Hash", "password", "Hugh", "Jackman", false);
        expectedException.expect(UsernameAlreadyTaken.class);
        expectedException.expectMessage("Username is taken");
        userServices.createUser(user);
    }

    @Test
    public void missingInformationCreatingAccount(){
        User user = new User(1, " ", " ", "", "", false);
        expectedException.expect(AccountInformationIsNotFilledOut.class);
        expectedException.expectMessage("Account Information incomplete");
        userServices.createUser(user);
    }

    @Test
    public void noUserToDelete(){
        expectedException.expect(UserNotFound.class);
        expectedException.expectMessage("User not found");
        userServices.deleteUserService("NoUser");
    }

    @Test
    public void getAllUsers(){
        userServices.getAllUsersService();
    }

    @Test
    public void validUsernameAndPassword(){
        userServices.checkLoginCredentialsService("Hash", "password");
    }
    @Test
    public void invalidUsernameForLogin(){
        expectedException.expect(InvalidUsername.class);
        expectedException.expectMessage("Invalid username");
        userServices.checkLoginCredentialsService("aaaaa", "Lilly1");
    }

    @Test
    public void invalidPasswordForLogin(){
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid salt version");
        userServices.checkLoginCredentialsService("kaytoups", "failpassword");
    }

    @Test
    public void badUserIdForAdmin(){
        expectedException.expect(UserNotFound.class);
        expectedException.expectMessage("User not found");
        userServices.isUserAdminService("NoUser");
    }
}
