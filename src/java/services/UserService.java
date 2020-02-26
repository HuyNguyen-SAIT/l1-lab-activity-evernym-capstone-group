/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.DatabaseServlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

/**
 *
 * @author 810783
 */
public class UserService 
{
    private static ArrayList<User> usersList = new ArrayList<>();
    
    public static void insert(String username, String firstname, String lastname, String password, String email) throws SQLException
    {
        Connection connection = null;
        
        try 
        {
            String dbURL = "jdbc:mysql://localhost:3306/notesdb?zeroDateTimeBehavior=convertToNull";
            String dbUsername = "root";
            String dbPassword = "password";
            
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            
            String preparedQuery = "INSERT INTO user VALUES(?, ?, ?, ?, ?)";
            
            PreparedStatement ps = connection.prepareStatement(preparedQuery);
            ps.setString(1, username);
            ps.setString(2, firstname);
            ps.setString(3, lastname);
            ps.setString(4, password);
            ps.setString(5, email);

            int rows = ps.executeUpdate();
        } 
        catch(SQLException e) 
        {
            e.printStackTrace();
        } 
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(DatabaseServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        connection.close();
    }
    
    public static void delete(String username) throws SQLException
    {
        Connection connection = null;
        
        try 
        {
            String dbURL = "jdbc:mysql://localhost:3306/notesdb?zeroDateTimeBehavior=convertToNull";
            String dbUsername = "root";
            String dbPassword = "password";
            
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            
            String preparedQuery = "DELETE FROM user WHERE Username = ?";

            PreparedStatement ps = connection.prepareStatement(preparedQuery);
            ps.setString(1, username);

            int rows = ps.executeUpdate();
        } 
        catch(SQLException e) 
        {
            e.printStackTrace();
        } 
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(DatabaseServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        connection.close();
    }
    
    public static void update(String username, String firstname, String lastname, String password, String email) throws SQLException
    {
        Connection connection = null;
        
        try 
        {
            String dbURL = "jdbc:mysql://localhost:3306/notesdb?zeroDateTimeBehavior=convertToNull";
            String dbUsername = "root";
            String dbPassword = "password";
            
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            
            String preparedQuery = "UPDATE user " + 
                                   "SET Firstname = ?, Lastname = ?, Password = ?, Email = ?" + 
                                   "WHERE Username = ?";

            PreparedStatement ps = connection.prepareStatement(preparedQuery);
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, password);
            ps.setString(4, email);
            ps.setString(5, username);
            
            int rows = ps.executeUpdate();
        } 
        catch(SQLException e) 
        {
            e.printStackTrace();
        } 
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(DatabaseServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        connection.close();
    }
    
    public static User getUser(String username)
    {
        Connection connection = null;
        User oneUser = null;
        
        try 
        {
             String dbURL = "jdbc:mysql://localhost:3306/notesdb?zeroDateTimeBehavior=convertToNull";
            String dbUsername = "root";
            String dbPassword = "password";
            
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            
            String preparedSQL = "SELECT * FROM USER WHERE Username = ?";

            PreparedStatement ps = connection.prepareStatement(preparedSQL);
            ps.setString(1, username);

            //does binding and returns result
            ResultSet users = ps.executeQuery();
            
            while (users.next()) 
            {
                String usernameFromDB = users.getString("Username");
                String firstnameFromDB = users.getString("Firstname");
                String lastnameFromDB = users.getString("Lastname");
                String passwordFromDB = users.getString("Password");
                String emailFromDB = users.getString("Email");
                
                oneUser = new User(usernameFromDB, firstnameFromDB, lastnameFromDB, passwordFromDB, emailFromDB);
            }
        } 
        catch(SQLException e) 
        {
            e.printStackTrace();
        } 
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(DatabaseServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return oneUser;
    }
    
    public static ArrayList<User> loadList() throws SQLException
    {
        Connection connection = null;
        usersList.clear();
        
        try 
        {
            String dbURL = "jdbc:mysql://localhost:3306/notesdb?zeroDateTimeBehavior=convertToNull";
            String dbUsername = "root";
            String dbPassword = "password";
            
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            
            String preparedSQL = "SELECT * FROM USER";

            PreparedStatement ps = connection.prepareStatement(preparedSQL);

            //does binding and returns result
            ResultSet users = ps.executeQuery();
            
            while (users.next()) 
            {
                String usernameFromDB = users.getString("Username");
                String firstnameFromDB = users.getString("Firstname");
                String lastnameFromDB = users.getString("Lastname");
                String passwordFromDB = users.getString("Password");
                String emailFromDB = users.getString("Email");
                
                User oneUser = new User(usernameFromDB, firstnameFromDB, lastnameFromDB, passwordFromDB, emailFromDB);
                usersList.add(oneUser);
            }
        } 
        catch(SQLException e) 
        {
            e.printStackTrace();
        } 
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(DatabaseServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return usersList;
    }
}