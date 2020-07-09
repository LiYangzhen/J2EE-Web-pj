<%--
  Created by IntelliJ IDEA.
  User: LYZ
  Date: 2020/7/9
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    if (request != null) {
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String password = request.getParameter("password1");
        response.getWriter().println("UserName: " + username);
        response.getWriter().println("<br>");
        response.getWriter().println("FirstName: " + firstName);
        response.getWriter().println("<br>");
        response.getWriter().println("LastName: " + lastName);
        response.getWriter().println("<br>");
        response.getWriter().println("Email: " + email);
        response.getWriter().println("<br>");
        response.getWriter().println("Address: " + address);
        response.getWriter().println("<br>");
        response.getWriter().println("Password: " + password);
    }
%>
</body>
</html>
