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
            "insert into user_table values('100003', 'username3', 'password3', 'first_name3', 'last_name3', false);";
            Statement statement = connection.createStatement();
            statement.executeQuery(sql);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void depopulate_tables(){
        try(Connection connection = DatabaseConnection.createConnection()){
            String sql = "delete from user_table where user_id > 100000;";
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
        depopulate_tables();
        System.out.println("Table depopulated");
    }
}
