<%-- 
    Document   : test
    Created on : Feb 14, 2020, 11:50:48 AM
    Author     : Melissa Lee 791397
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
                <c:forEach var="User" items="${UserList}" varStatus="status">
                    <tr>
                        <td class="tg-0lax">${User.userName}</td>
                        <td class="tg-0lax">${User.firstName}</td>
                        <td class="tg-0lax">${User.lastName}</td>
                        <td class="tg-0lax">${User.email}</td>
                    <form action="" method="post">
                        <td class="tg-0lax">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="userName" value="${User.userName}" />
                            <input type="submit" value="Delete"/></td>
                        <td class="tg-0lax">
                    </form>
                    <form action="" method="post">
                        <input type="hidden" name="userName" value="${User.userName}"/>
                        <input type="hidden" name="action" value="edit" />
                        <input type="submit" value="Edit"/></td>
                    </form>
                    </tr>
                </c:forEach>  
            </table>
        </br>           
        <form action="" method="POST">
            <h3>${Heading}</h3>
            Username: <input ${readonly} required type="text" name="userName" value="${userName1}"/>
            <br/>
            First Name: <input required type="text" name="firstName" value="${firstName1}"/>
            <br/>
            Last Name: <input required type="text" name="lastName" value="${lastName1}"/>
            <br/>
            Password: <input required type="text" name="password" value="${password1}"/>
            <br/>
            Email: <input required type="email" name="email" value="${email1}"/>
            <br/>
            <input type="hidden" name="action" value="save">
            <input type="submit" value="Save"/>
        <form/>
    <br/>
</body>
</html>