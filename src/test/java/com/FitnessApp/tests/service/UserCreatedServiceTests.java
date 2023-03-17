package com.FitnessApp.tests.service;

import com.FitnessApp.customexceptions.PremadeWorkoutNotFound;
import com.FitnessApp.customexceptions.UserCreatedWorkoutNotFound;
import com.FitnessApp.customexceptions.UserNotFound;
import com.FitnessApp.customexceptions.WorkoutInformationIsNotFilledOut;
import com.FitnessApp.daos.user.UserDAO;
import com.FitnessApp.daos.user.UserDAOImp;
import com.FitnessApp.daos.usercreatedworkout.UserCreatedDAO;
import com.FitnessApp.daos.usercreatedworkout.UserCreatedDAOImp;
import com.FitnessApp.entities.UserCreatedWorkout;
import com.FitnessApp.services.usercreatedworkout.UserCreatedServices;
import com.FitnessApp.services.usercreatedworkout.UserCreatedServicesImp;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UserCreatedServiceTests {

    static UserCreatedDAO userCreatedDAO = new UserCreatedDAOImp();
    static UserDAO userDAO = new UserDAOImp();
    static UserCreatedServices userCreatedServices = new UserCreatedServicesImp(userCreatedDAO, userDAO);

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void blankWorkoutNameForCreate(){
        UserCreatedWorkout workout = new UserCreatedWorkout(0, "", "kaytoups", "Test", "2023-03-17");
        expectedException.expect(WorkoutInformationIsNotFilledOut.class);
        expectedException.expectMessage("Workout Information is not filled out");
        userCreatedServices.createNewWorkout(workout);
    }

    @Test
    public void blankUsernameForCreateWorkout(){
        UserCreatedWorkout workout = new UserCreatedWorkout(0, "Legs", "", "Test", "2023-03-17");
        expectedException.expect(WorkoutInformationIsNotFilledOut.class);
        expectedException.expectMessage("Workout Information is not filled out");
        userCreatedServices.createNewWorkout(workout);
    }

    @Test
    public void badUsernameForCreateWorkout(){
        UserCreatedWorkout workout = new UserCreatedWorkout(0, "Legs", "FakeUser", "Test", "2023-03-17");
        expectedException.expect(UserNotFound.class);
        expectedException.expectMessage("User not found");
        userCreatedServices.createNewWorkout(workout);
    }

    @Test
    public void negativeWorkoutIdForCreate() {
        UserCreatedWorkout workout = new UserCreatedWorkout(-200, "Legs", "kaytoups", "Test", "2023-03-17");
        expectedException.expect(WorkoutInformationIsNotFilledOut.class);
        expectedException.expectMessage("Workout Information is not filled out");
        userCreatedServices.createNewWorkout(workout);
    }

    @Test
    public void badWorkoutIdForCreate() {
        UserCreatedWorkout workout = new UserCreatedWorkout(500000, "Legs", "kaytoups", "Test", "2023-03-17");
        expectedException.expect(UserCreatedWorkoutNotFound.class);
        expectedException.expectMessage("User Created Workout not found");
        userCreatedServices.createNewWorkout(workout);
    }

    @Test
    public void badIdForUserCreatedExercises(){
        expectedException.expect(UserCreatedWorkoutNotFound.class);
        expectedException.expectMessage("User Created Workout not found");
        userCreatedServices.getAllExercisesByUserCreatedId(500000);
    }

    @Test
    public void badIdForUserCreatedWorkout(){
        expectedException.expect(UserCreatedWorkoutNotFound.class);
        expectedException.expectMessage("User Created Workout not found");
        userCreatedServices.getUserCreatedWorkout(500000);
    }

}
