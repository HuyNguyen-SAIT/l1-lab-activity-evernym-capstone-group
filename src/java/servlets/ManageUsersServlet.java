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
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        try 
        {
            userList = UserService.loadList();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ManageUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("Heading", "Add User");
        session.setAttribute("UserList", userList);
        getServletContext().getRequestDispatcher("/WEB-INF/manageUsers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String action = request.getParameter("action");
        
   
        if (action.equals("delete"))
        {
            String j_userName = request.getParameter("userName");
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
            String j_userName = request.getParameter("userName");
            User u = UserService.getUser(j_userName);
            String j_firstName = u.getFirstName();
            String j_lastName = u.getLastName();
            String j_password = u.getPassword();
            String j_email = u.getEmail();
            request.setAttribute("Heading", "Edit");
            request.setAttribute("userName1", j_userName);
            request.setAttribute("readonly", "readonly");
            request.setAttribute("firstName1", j_firstName);
            request.setAttribute("lastName1", j_lastName);
            request.setAttribute("password1", j_password);
            request.setAttribute("email1", j_email);

            
            //response.sendRedirect(request.getContextPath() + "/manageUsers");
            getServletContext().getRequestDispatcher("/WEB-INF/manageUsers.jsp").forward(request, response);
           
        }
        else if (action.equals("save"))
        {
            String j_userName = request.getParameter("userName");
            User u = UserService.getUser(j_userName);
            String j_firstName = request.getParameter("firstName");
            String j_lastName = request.getParameter("lastName");
            String j_password = request.getParameter("password");
            String j_email = request.getParameter("email");
            try 
            {
                if (u == null){
                    UserService.insert(j_userName, j_firstName, j_lastName, j_password, j_email);
                }else{
                    UserService.update(j_userName, j_firstName, j_lastName, j_password, j_email);
                }
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