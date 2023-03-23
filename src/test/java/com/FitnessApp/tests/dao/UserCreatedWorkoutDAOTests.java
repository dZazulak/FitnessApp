package com.FitnessApp.tests.dao;

import com.FitnessApp.customexceptions.UserCreatedWorkoutNotFound;
import com.FitnessApp.daos.usercreatedworkout.UserCreatedDAO;
import com.FitnessApp.daos.usercreatedworkout.UserCreatedDAOImp;
import com.FitnessApp.entities.Exercise;
import com.FitnessApp.entities.PremadeWorkout;
import com.FitnessApp.entities.User;
import com.FitnessApp.entities.UserCreatedWorkout;
import com.FitnessApp.util.DatabaseCreator;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.List;

public class UserCreatedWorkoutDAOTests {
    UserCreatedDAO userCreatedDAO = new UserCreatedDAOImp();

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
    public void createNewWorkout(){
        UserCreatedWorkout workout = new UserCreatedWorkout(150000, "Summer Lift", "kaytoups", "I will start this in the summer", "2023-03-16");
        UserCreatedWorkout createdWorkout = userCreatedDAO.createNewWorkout(workout);
        Assert.assertTrue(createdWorkout.getUserCreatedId() != 0);

    }
    @Test
    public void getExercisesFromUserCreatedWorkout(){
        List<Exercise> exercises = userCreatedDAO.getAllExercisesByUserCreatedId(100001);
        System.out.println(exercises);
        Assert.assertTrue(exercises.size() > 1);
    }

    @Test
    public void getUserCreatedWorkout(){
        UserCreatedWorkout pm = userCreatedDAO.getUserCreatedWorkout(100001);
        Assert.assertEquals(pm.getWorkoutName(), "Back and Bi");
    }

    @Test
    public void deleteUserCreatedWorkout(){
        expectedException.expect(UserCreatedWorkoutNotFound.class);
        expectedException.expectMessage("User Created Workout not found");
        UserCreatedWorkout workout = userCreatedDAO.getUserCreatedWorkout(100002);
        userCreatedDAO.deleteWorkout(workout);
        userCreatedDAO.getUserCreatedWorkout(100002);
    }
}
