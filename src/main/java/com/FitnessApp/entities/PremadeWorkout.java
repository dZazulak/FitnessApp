package com.FitnessApp.entities;

import java.util.Objects;

public class PremadeWorkout {

    private int premadeId;
    private String workoutName;
    private String description;

    public PremadeWorkout(){}
    public PremadeWorkout(int premadeId, String workoutName, String description) {
        this.premadeId = premadeId;
        this.workoutName = workoutName;
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PremadeWorkout that = (PremadeWorkout) o;
        return getPremadeId() == that.getPremadeId() && getWorkoutName().equals(that.getWorkoutName()) && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPremadeId(), getWorkoutName(), getDescription());
    }

    @Override
    public String toString() {
        return "PremadeWorkout{" +
                "premadeId=" + premadeId +
                ", workoutName='" + workoutName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
