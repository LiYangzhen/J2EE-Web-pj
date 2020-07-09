package classes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String password = request.getParameter("password1");
        System.out.println(firstName instanceof String);
        System.out.println(firstName.equals(""));
        System.out.println(username + firstName + lastName + email + address + password);
        if (username != "" && firstName != "" && lastName != "" && email != "" && address != "" && password != "") {
            request.getRequestDispatcher("welcome.jsp").forward(request,response);
        } else {
            request.getRequestDispatcher("register.jsp").forward(request,response);
        }
    }

}
