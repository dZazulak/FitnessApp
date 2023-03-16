package com.FitnessApp.tests.dao;

import com.FitnessApp.daos.premadeworkout.PremadeDAO;
import com.FitnessApp.daos.premadeworkout.PremadeDAOImp;
import com.FitnessApp.entities.Exercise;
import com.FitnessApp.entities.PremadeWorkout;
import com.FitnessApp.util.DatabaseCreator;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.List;

public class PremadeWorkoutDAOTests {

    PremadeDAO premadeDAO = new PremadeDAOImp();

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
    public void getExercisesFromPremadeWorkout(){
        List<Exercise> exercises = premadeDAO.getAllExercisesByPremadeId(100001);
        System.out.println(exercises);
        Assert.assertTrue(exercises.size() > 1);
    }

    @Test
    public void getPremadeWorkout(){
        PremadeWorkout pm = premadeDAO.getPremadeWorkout(100001);
        Assert.assertEquals(pm.getWorkoutName(), "Complete Arms Workout");
    }

    @Test
    public void userSelectPremadeWorkout(){
        String pm = premadeDAO.userSelectPremadeWorkout(100001, "username2");
        System.out.println(pm);
        Assert.assertEquals("username2", pm);
    }


}
