package com.FitnessApp.util;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseCreator {
    public static void populate_tables(){
        try(Connection connection = DatabaseConnection.createConnection()){
            String sql = "insert into user_table values('100001', 'username1', 'password1', 'first_name1', 'last_name1', true);" +
            "insert into user_table values('100002', 'username2', 'password2', 'first_name2', 'last_name2', false);" +
            "insert into user_table values('100003', 'username3', 'password3', 'first_name3', 'last_name3', false);" +
            "insert into pm_wkout_table values('100001', 'Complete Arms Workout', 'A workout made to see growth in your arms');" +
            "insert into pm_wkout_table values('100002', 'Complete Legs Workout', 'Workout your legs for muscle growth');" +
            "insert into uc_wkout_table values('100001', 'Back and Bi', '1', 'Back and Bi Workout', '03-09-2023');" +
            "insert into uc_wkout_table values('100002', 'Chest Killer', '1', 'Big gains', '03-09-2023');" +
            "insert into exercise_table values('100001', '100001', '0', 'Lat Pull Downs', 'Back', 'This will be so I can focus on the back muscles');" +
            "insert into exercise_table values('100002', '100002', '0', 'Chest press', 'Chest', 'I want to make my chest stronger');" +
            "insert into exercise_table values('100003', '0', '100001', 'Bicep Curls', 'Biceps', 'This exercise will use your biceps to lift weights');" +
            "insert into exercise_table values('100004', '0', '100002', 'Leg press', 'Legs', 'This exercise will use your legs to lift weights');";
            Statement statement = connection.createStatement();
            statement.executeQuery(sql);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void depopulate_tables(){
        try(Connection connection = DatabaseConnection.createConnection()){
            String sql = "delete from user_table where user_id > 100000;" +
            "delete from exercise_table where exercise_id > 100000;" +
            "delete from pm_wkout_table where premade_id > 100000;" +
            "delete from uc_wkout_table where usercreated_id > 100000;";
            Statement statement = connection.createStatement();
            statement.executeQuery(sql);
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        System.out.println("Table populating");
        populate_tables();
//        depopulate_tables();
        System.out.println("Table depopulated");
    }
}
