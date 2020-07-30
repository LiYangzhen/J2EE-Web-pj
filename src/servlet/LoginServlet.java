package servlet;

import classes.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        JdbcUtil util = new JdbcUtil();
        Connection conn = util.getConnection();
        String sql = "select UserName,Pass,UID from traveluser WHERE UserName=? AND Pass=?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,username);
            pst.setString(2,password);
            rs = pst.executeQuery();

            if (rs.next()) {
                if (username.equals(rs.getString(1)) && password.equals(rs.getString(2))) {
                    HttpSession session = request.getSession();
                    session.setAttribute("username",username);
                    session.setAttribute("UID",rs.getInt(3));
                    System.out.println(rs.getInt(3));
                    response.sendRedirect("home");
                } else{
                    request.getRequestDispatcher("login.jsp").forward(request,response);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs, pst, conn);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req,resp);
        if (req.getParameter("logout")!=null){
            HttpSession httpsession = req.getSession();
            httpsession.invalidate();
            resp.sendRedirect("home");
        }
    }
}
