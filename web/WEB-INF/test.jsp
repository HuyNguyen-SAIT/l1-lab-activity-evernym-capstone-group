<%-- 
    Document   : test
    Created on : Feb 14, 2020, 11:50:48 AM
    Author     : 791397
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lecture Activity</title>
    </head>
    <body>
        <h1>Manage Users</h1>
        <br/>
        <form method="POST"/>
        <h3>Users</h3>
        <table class="tg">
            <tr>
                <th class="tg-0pky">Username</th>
                <th class="tg-0lax">First Name</th>
                <th class="tg-0lax">Last Name</th>
                <th class="tg-0lax">Email</th>
                <th class="tg-0lax">Delete</th>
                <th class="tg-0lax">Edit</th>
            </tr>
            <tr>
            <c:forEach var="user" items="${usersList}" varStatus="status">
                <td class="tg-0lax">${user}</td>
                <td class="tg-0lax">${fname}</td>
                <td class="tg-0lax">${lname}</td>
                <td class="tg-0lax">${email}</td>
                <td class="tg-0lax">
                    <input type="hidden" name="action" value="delete" />
                    <input type="submit" value="Delete"/></td>
                <td class="tg-0lax">
                    <input type="hidden" name="action" value="edit" />
                    <input type="submit" value="Edit"/></td>
            </c:forEach>
            </tr>
        </table>
        </form>
        </br>           
        <form method="POST">
        <h3>Add User</h3>
        Username: <input type="text" name="userName"/>
        <br/>
        First Name: <input type="text" name="firstName"/>
        <br/>
        Last Name: <input type="text" name="lastName"/>
        <br/>
        Password: <input type="password" name="password"/>
        <br/>
        Email: <input type="text" name="email"/>
        <br/>
        <input type="submit" value="Save"/>
        <form/>
    <br/>
</body>
</html>
