package com.FitnessApp.tests.service;

import com.FitnessApp.customexceptions.PremadeWorkoutNotFound;
import com.FitnessApp.customexceptions.UserNotFound;
import com.FitnessApp.daos.premadeworkout.PremadeDAO;
import com.FitnessApp.daos.premadeworkout.PremadeDAOImp;
import com.FitnessApp.daos.user.UserDAO;
import com.FitnessApp.daos.user.UserDAOImp;
import com.FitnessApp.services.premadeworkout.PremadeServices;
import com.FitnessApp.services.premadeworkout.PremadeServicesImp;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PremadeWorkoutServiceTests {

    static PremadeDAO premadeDAO = new PremadeDAOImp();
    static UserDAO userDAO = new UserDAOImp();

    static PremadeServices premadeServices = new PremadeServicesImp(premadeDAO, userDAO);

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void badIdForPremadeExercises(){
        expectedException.expect(PremadeWorkoutNotFound.class);
        expectedException.expectMessage("Premade Workout not found");
        premadeServices.getAllExercisesByPremadeIdService(500000);
    }

    @Test
    public void badIdForPremadeWorkout(){
        expectedException.expect(PremadeWorkoutNotFound.class);
        expectedException.expectMessage("Premade Workout not found");
        premadeServices.getPremadeWorkoutService(500000);
    }

    @Test
    public void usernameBlankSelectingPremadeWorkout(){
        expectedException.expect(UserNotFound.class);
        expectedException.expectMessage("User not found");
        premadeServices.userSelectPremadeWorkoutService(0, "");
    }

    @Test
    public void badUsernameSelectingPremadeWorkout(){
        expectedException.expect(UserNotFound.class);
        expectedException.expectMessage("User not found");
        premadeServices.userSelectPremadeWorkoutService(0, "FakeUser");
    }

    @Test
    public void badIdForSelectingPremadeWorkout(){
        expectedException.expect(PremadeWorkoutNotFound.class);
        expectedException.expectMessage("Premade Workout not found");
        premadeServices.userSelectPremadeWorkoutService(500000, "kaytoups");
    }
}
