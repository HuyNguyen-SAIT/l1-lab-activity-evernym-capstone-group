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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Talha Khamoor 797138
 */
public class ManageUsersServlet extends HttpServlet 
{
    private static ArrayList<User> userList;
    private HttpSession session = null;
    
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
        
        //request.setAttribute("UserList", userList);
        session = request.getSession();
        session.setAttribute("UserList", userList);
        getServletContext().getRequestDispatcher("/WEB-INF/manageUsers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String action = request.getParameter("action");
        String j_userName = request.getParameter("userName");
        String j_firstName = request.getParameter("firstName");
        String j_lastName = request.getParameter("lastName");
        String j_password = request.getParameter("password");
        String j_email = request.getParameter("email");
        
        if (action.equals("delete"))
        {
            try 
            {
                UserService.delete(j_userName);
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(ManageUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            response.sendRedirect(request.getContextPath() + "/manageUsers");  
        }
        else if (action.equals("edit"))
        {
            System.out.println(j_firstName);
            request.setAttribute("userName1", j_userName);
            request.setAttribute("firstName1", j_firstName);
            request.setAttribute("lastName1", j_lastName);
            request.setAttribute("password1", j_password);
            request.setAttribute("email1", j_email);
            
            getServletContext().getRequestDispatcher("/WEB-INF/manageUsers.jsp").forward(request, response);
        }
        else if (action.equals("save"))
        {
            try 
            {
                System.out.println(j_firstName);
                UserService.insert(j_userName, j_firstName, j_lastName, j_password, j_email);
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(ManageUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           response.sendRedirect(request.getContextPath() + "/manageUsers");            
        }
    }

    @Override
    public String getServletInfo() 
    {
        return "Short description";
    }
}
