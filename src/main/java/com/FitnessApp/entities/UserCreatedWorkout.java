package com.FitnessApp.entities;

import java.sql.Date;
import java.util.Objects;

public class UserCreatedWorkout {

    private int userCreatedId;
    private String workoutName;
    private int userId;
    private String description;
    private String dateCreated;

    public UserCreatedWorkout(int userCreatedId, String workoutName, int userId, String description, String dateCreated) {
        this.userCreatedId = userCreatedId;
        this.workoutName = workoutName;
        this.userId = userId;
        this.description = description;
        this.dateCreated = dateCreated;
    }

    public int getUserCreatedId() {
        return userCreatedId;
    }

    public void setUserCreatedId(int userCreatedId) {
        this.userCreatedId = userCreatedId;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreatedWorkout that = (UserCreatedWorkout) o;
        return getUserCreatedId() == that.getUserCreatedId() && getUserId() == that.getUserId() && getWorkoutName().equals(that.getWorkoutName()) && Objects.equals(getDescription(), that.getDescription()) && getDateCreated().equals(that.getDateCreated());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserCreatedId(), getWorkoutName(), getUserId(), getDescription(), getDateCreated());
    }

    @Override
    public String toString() {
        return "UserCreatedWorkout{" +
                "userCreatedId=" + userCreatedId +
                ", workoutName='" + workoutName + '\'' +
                ", userId=" + userId +
                ", description='" + description + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                '}';
    }
}
