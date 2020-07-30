package servlet;

import classes.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String password = request.getParameter("password");

        if (!username.equals("") && !firstName.equals("") && !lastName.equals("") && !email.equals("") && !address.equals("") && !password.equals("")) {
            if (isDuplicate(username)) {
                request.setAttribute("content", "<p style='color:red;text-align:left;'>This username has been registered.</p>");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else {
                register(email,username,password);
                response.sendRedirect("login");
            }
        } else {
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    private boolean isDuplicate(String username) {
        JdbcUtil util = new JdbcUtil();
        Connection conn = util.getConnection();
        String sql = "select UserName from traveluser WHERE UserName=?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            rs = pst.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs, pst, conn);
        }
        return false;
    }

    private void register(String email, String username, String password) {
        JdbcUtil util = new JdbcUtil();
        Connection conn = util.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String sql = "INSERT INTO traveluser (Email,UserName,Pass,State,DateJoined,DateLastModified) " +
                    "VALUES (?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, username);
            pst.setString(3, password);
            pst.setInt(4, 1);
            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            pst.setString(5, ft.format(dNow));
            pst.setString(6, ft.format(dNow));

            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
