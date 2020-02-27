/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.UserService;
import models.User;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 797138
 */
public class ManageUsersServlet extends HttpServlet 
{
    private static ArrayList<User> userList;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        try 
        {
            userList = UserService.loadList();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ManageUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("UserList", userList);
        getServletContext().getRequestDispatcher("/WEB-INF/manageUsers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        //processRequest(request, response);
    }

    @Override
    public String getServletInfo() 
    {
        return "Short description";
    }// </editor-fold>

}
