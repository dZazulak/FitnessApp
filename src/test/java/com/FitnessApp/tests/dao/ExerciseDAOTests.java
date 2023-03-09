package com.FitnessApp.tests.dao;

import com.FitnessApp.customexceptions.UserCreatedExerciseNotFound;
import com.FitnessApp.daos.exercise.ExerciseDAO;
import com.FitnessApp.daos.exercise.ExerciseDAOImp;
import com.FitnessApp.entities.Exercise;
import com.FitnessApp.util.DatabaseCreator;
import org.junit.*;
import org.junit.rules.ExpectedException;

public class ExerciseDAOTests {

    ExerciseDAO exerciseDAO = new ExerciseDAOImp();

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
    public void getExerciseByPremadeId(){
        Exercise exercise = exerciseDAO.getExerciseByPremadeId(100003, 100001);
        System.out.println(exercise);
        Assert.assertEquals(exercise.getPremadeId(), 100001);
    }

    @Test
    public void getExerciseByUserCreatedId() {
        Exercise exercise = exerciseDAO.getExerciseByUserCreatedId(100001, 100001);
        System.out.println(exercise);
        Assert.assertEquals(exercise.getUserCreatedId(), 100001);
    }

    @Test
    public void createNewExercise(){
        Exercise exercise = new Exercise(500000, 100001, 0, "Test", "Test", "Test");
        Exercise newExercise = exerciseDAO.createNewExercise(exercise);
        System.out.println(newExercise);
        Assert.assertEquals(newExercise.getUserCreatedId(), 100001);
    }

    @Test
    public void editExercise(){
        Exercise editedExercise = exerciseDAO.editExercise(100001, 100001, "I didn't like the old name", "Still back", "good for the back");
        System.out.println(editedExercise);
        Assert.assertEquals(editedExercise.getExerciseName(), "I didn't like the old name");
        Assert.assertEquals(editedExercise.getExerciseType(), "Still back");
        Assert.assertEquals(editedExercise.getDescription(), "good for the back");
    }

    @Test
    public void deleteExercise(){
        exerciseDAO.deleteExercise(100002, 100002);
        expectedException.expect(UserCreatedExerciseNotFound.class);
        expectedException.expectMessage("User Created Exercise not found");
        exerciseDAO.getExerciseByUserCreatedId(100002, 100002);
    }

}
