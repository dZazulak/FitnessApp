package com.FitnessApp.entities;

import java.util.Objects;

public class Exercise {

    private int exerciseId;
    private int userCreatedId;
    private int premadeId;
    private String exerciseName;
    private String exerciseType;
    private String description;

    public Exercise(){}
    public Exercise(int exerciseId, int userCreatedId, int premadeId, String exerciseName, String exerciseType, String description) {
        this.exerciseId = exerciseId;
        this.userCreatedId = userCreatedId;
        this.premadeId = premadeId;
        this.exerciseName = exerciseName;
        this.exerciseType = exerciseType;
        this.description = description;
    }
    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getUserCreatedId() {
        return userCreatedId;
    }

    public void setUserCreatedId(int userCreatedId) {
        this.userCreatedId = userCreatedId;
    }

    public int getPremadeId() {
        return premadeId;
    }

    public void setPremadeId(int premadeId) {
        this.premadeId = premadeId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
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
        Exercise exercise = (Exercise) o;
        return getExerciseId() == exercise.getExerciseId() && getUserCreatedId() == exercise.getUserCreatedId() && getPremadeId() == exercise.getPremadeId() && Objects.equals(getExerciseName(), exercise.getExerciseName()) && Objects.equals(getExerciseType(), exercise.getExerciseType()) && Objects.equals(getDescription(), exercise.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExerciseId(), getUserCreatedId(), getPremadeId(), getExerciseName(), getExerciseType(), getDescription());
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "exerciseId=" + exerciseId +
                ", userCreatedId=" + userCreatedId +
                ", premadeId=" + premadeId +
                ", exerciseName='" + exerciseName + '\'' +
                ", exerciseType='" + exerciseType + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
