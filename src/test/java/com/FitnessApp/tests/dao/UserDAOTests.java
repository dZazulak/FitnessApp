package com.FitnessApp.tests.dao;

import com.FitnessApp.customexceptions.UserNotFound;
import com.FitnessApp.customexceptions.UsernameAlreadyTaken;
import com.FitnessApp.daos.user.UserDAO;
import com.FitnessApp.daos.user.UserDAOImp;
import com.FitnessApp.entities.User;
import com.FitnessApp.util.DatabaseCreator;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.List;

public class UserDAOTests {

    UserDAO userDAO = new UserDAOImp();

    User newUser = new User(100005, "test_username", "test_password", "Dave", "Matthews", false);

    @BeforeClass
    public static void setup(){
        DatabaseCreator.depopulate_tables();
        DatabaseCreator.populate_tables();
    }

    @AfterClass
    public static void teardown(){
        DatabaseCreator.depopulate_tables();
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Test
    public void getUserById(){
        User user = userDAO.getUserById(3);
        System.out.println(user);
        Assert.assertEquals(user.getUserId(), 3);
    }

    @Test
    public void createUser(){
        User user = userDAO.createUser(newUser);
        System.out.println(user);
        Assert.assertTrue(user.getUserId() != 0);
    }

    @Test
    public void usernameTaken(){
        expectedException.expect(UsernameAlreadyTaken.class);
        expectedException.expectMessage("Username is taken");
        userDAO.usernameIsTaken("username1");
    }
    @Test
    public void deleteUserById(){
        userDAO.deleteUser(100002);
        expectedException.expect(UserNotFound.class);
        expectedException.expectMessage("User not found");
        userDAO.getUserById(100002);
    }

    @Test
    public void getAllUsers(){
        List<User> users = userDAO.getAllUsers();
        for(User u: users){
            System.out.println(u);
        }
        Assert.assertTrue(users.size() >= 2);
    }

    @Test
    public void checkLoginCredentials(){
        User user = userDAO.getUserById(100001);
        User login = userDAO.checkLoginCredentials(user.getUsername(), user.getPassword());
        System.out.println(login);
        Assert.assertEquals(login.getUserId(), 100001);
    }

    @Test
    public void isAdmin(){
        User user = userDAO.getUserById(100001);
        boolean admin = userDAO.isUserAdmin(user.getUserId());
        Assert.assertTrue(admin);
    }
}
