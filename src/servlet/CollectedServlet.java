package servlet;

import classes.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "CollectedServlet", value = "/collect")
public class CollectedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("imageid") != null) {
            int imageid = Integer.parseInt(req.getParameter("imageid"));
            String collected = req.getParameter("collected");
            if (req.getSession().getAttribute("UID") != null) {
                int UID = (int) req.getSession().getAttribute("UID");

                JdbcUtil util = new JdbcUtil();
                Connection conn = util.getConnection();
                PreparedStatement pst = null;
                int rs;
                try {
                    if (collected.equals("true")) {
                        String sql = "DELETE FROM travelimagefavor WHERE ImageID=? AND UID=?";
                        pst = conn.prepareStatement(sql);
                        pst.setInt(1, imageid);
                        pst.setInt(2, UID);
                        rs = pst.executeUpdate();
                    } else {
                        String sql = "INSERT INTO travelimagefavor (ImageID,UID) VALUES (?,?)";
                        pst = conn.prepareStatement(sql);
                        pst.setInt(1, imageid);
                        pst.setInt(2, UID);
                        rs = pst.executeUpdate();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            String referer = req.getHeader("Referer");
            resp.sendRedirect(referer);
        } else {
            resp.sendRedirect("home");
        }
    }
}
