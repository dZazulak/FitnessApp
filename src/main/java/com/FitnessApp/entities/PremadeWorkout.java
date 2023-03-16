package com.FitnessApp.entities;

import java.util.Objects;

public class PremadeWorkout {

    private int premadeId;
    private String workoutName;
    private String description;
    private String username;

    public PremadeWorkout(){}
    public PremadeWorkout(int premadeId, String workoutName, String description, String username) {
        this.premadeId = premadeId;
        this.workoutName = workoutName;
        this.description = description;
        this.username = username;

    }

    public int getPremadeId() {
        return premadeId;
    }

    public void setPremadeId(int premadeId) {
        this.premadeId = premadeId;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PremadeWorkout that = (PremadeWorkout) o;
        return getPremadeId() == that.getPremadeId() && Objects.equals(getWorkoutName(), that.getWorkoutName()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getUsername(), that.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPremadeId(), getWorkoutName(), getDescription(), getUsername());
    }

    @Override
    public String toString() {
        return "PremadeWorkout{" +
                "premadeId=" + premadeId +
                ", workoutName='" + workoutName + '\'' +
                ", description='" + description + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
