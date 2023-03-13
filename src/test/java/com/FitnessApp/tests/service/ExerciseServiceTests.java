package com.FitnessApp.tests.service;

import com.FitnessApp.customexceptions.ExerciseInformationIsNotFilledOut;
import com.FitnessApp.customexceptions.PremadeExerciseNotFound;
import com.FitnessApp.customexceptions.UserCreatedExerciseNotFound;
import com.FitnessApp.daos.exercise.ExerciseDAO;
import com.FitnessApp.daos.exercise.ExerciseDAOImp;
import com.FitnessApp.entities.Exercise;
import com.FitnessApp.services.exercise.ExerciseServices;
import com.FitnessApp.services.exercise.ExerciseServicesImp;
import com.FitnessApp.util.DatabaseCreator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ExerciseServiceTests {

    static ExerciseDAO exerciseDAO = new ExerciseDAOImp();

    static ExerciseServices exerciseServices = new ExerciseServicesImp(exerciseDAO);

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getBadPremadeId(){
        expectedException.expect(PremadeExerciseNotFound.class);
        expectedException.expectMessage("Premade Exercise not found");
        exerciseServices.getExerciseByPremadeIdService(100001, 500);
    }

    @Test
    public void getBadUserCreatedId(){
        expectedException.expect(UserCreatedExerciseNotFound.class);
        expectedException.expectMessage("User Created Exercise not found");
        exerciseServices.getExerciseByUserCreatedIdService(100001, 500);
    }

    @Test
    public void createExerciseMissingExerciseNameInfo(){
        Exercise exercise = new Exercise(500000, 500000, 0, "", "Back", "Description");
        expectedException.expect(ExerciseInformationIsNotFilledOut.class);
        expectedException.expectMessage("Exercise information is not complete");
        exerciseServices.createNewExerciseService(exercise);
    }

    @Test
    public void createExerciseMissingExerciseTypeInfo(){
        Exercise exercise = new Exercise(500000, 500000, 0, "Good back workout", "", "Description");
        expectedException.expect(ExerciseInformationIsNotFilledOut.class);
        expectedException.expectMessage("Exercise information is not complete");
        exerciseServices.createNewExerciseService(exercise);
    }

    @Test
    public void editExerciseMissingExerciseNameInfo(){
        expectedException.expect(ExerciseInformationIsNotFilledOut.class);
        expectedException.expectMessage("Exercise information is not complete");
        exerciseServices.editExerciseService(0, 0, "", "Legs", "Description");
    }

    @Test
    public void editExerciseMissingExerciseTypeInfo(){
        expectedException.expect(ExerciseInformationIsNotFilledOut.class);
        expectedException.expectMessage("Exercise information is not complete");
        exerciseServices.editExerciseService(0, 0, "Legs", "", "Description");
    }

    @Test
    public void editExerciseBadUserCreatedId(){
        expectedException.expect(UserCreatedExerciseNotFound.class);
        expectedException.expectMessage("User Created Exercise not found");
        exerciseServices.editExerciseService(0, 1000000, "Legs", "Legs", "Description");

    }

    @Test
    public void noExerciseToDelete(){
        expectedException.expect(UserCreatedExerciseNotFound.class);
        expectedException.expectMessage("User Created Exercise not found");
        exerciseServices.deleteExerciseService(0, 50000);
    }
}
