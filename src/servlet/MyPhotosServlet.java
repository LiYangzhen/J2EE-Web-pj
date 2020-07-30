package servlet;

import classes.JdbcUtil;
import com.sun.deploy.util.SessionProperties;
import dao.ImageDAO;
import domain.Image;
import impl.ImageDAOJdbcImpl;

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
import java.util.List;

@WebServlet(name = "MyPhotosServlet", value = "/my_photos")
public class MyPhotosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("page") == null && req.getParameter("delete") == null) {
            ImageDAO imageDAO = new ImageDAOJdbcImpl();
            HttpSession session = req.getSession();
            int num = imageDAO.getMyPhotosNum((int) session.getAttribute("UID"));
            List<Image> images = imageDAO.getMyPhotos((int) session.getAttribute("UID"), 0);
            StringBuilder stringBuilder = new StringBuilder();
            for (Image image : images) {
                String str = "<li class='thumbnail' title='" + image.getTitle() + "'>\n" +
                        "<a href='details.jsp?imageid=" + image.getImageId() + "'>\n" +
                        "<div class='img-box'>\n" +
                        "<img src='travel-images/small/" + image.getPATH() + "' alt='图片'>\n" +
                        "</div>\n" +
                        "<div><h3>" + image.getTitle() + "</h3 >\n" +
                        "<p>" + image.getDescription() + "</p>\n" +
                        "</div>\n" +
                        "</a>\n" +
                        "<div class='editBox'>\n" +
                        "<a href='upload?imageid=" + image.getImageId() + "'>编辑</a>\n" +
                        "<a href='my_photos?imageid=" + image.getImageId() + "&delete=true'>删除</a>\n" +
                        "</div>\n" +
                        "</li>\n";
                stringBuilder.append(str);
            }
            req.setAttribute("content", stringBuilder);
            req.setAttribute("page", 0);
            req.setAttribute("num", num);
        } else if (req.getParameter("page") != null && req.getParameter("delete") == null) {
            ImageDAO imageDAO = new ImageDAOJdbcImpl();
            HttpSession session = req.getSession();
            int num = imageDAO.getMyPhotosNum((int) session.getAttribute("UID"));
            int page = Integer.parseInt(req.getParameter("page"));
            List<Image> images = imageDAO.getMyPhotos((int) session.getAttribute("UID"), page);
            StringBuilder stringBuilder = new StringBuilder();
            for (Image image : images) {
                String str = "<li class='thumbnail' title='" + image.getTitle() + "'>\n" +
                        "<a href='details.jsp?imageid=" + image.getImageId() + "'>\n" +
                        "<div class='img-box'>\n" +
                        "<img src='travel-images/small/" + image.getPATH() + "' alt='图片'>\n" +
                        "</div>\n" +
                        "<div><h3>" + image.getTitle() + "</h3 >\n" +
                        "<p>" + image.getDescription() + "</p>\n" +
                        "</div>\n" +
                        "</a>\n" +
                        "<div class='editBox'>\n" +
                        "<a href='upload?imageid=" + image.getImageId() + "'>编辑</a>\n" +
                        "<a href='my_photos?imageid=" + image.getImageId() + "&delete=true'>删除</a>\n" +
                        "</div>\n" +
                        "</li>\n";
                stringBuilder.append(str);
            }
            req.setAttribute("content", stringBuilder);
            req.setAttribute("page", page);
            req.setAttribute("num", num);
        } else if (req.getParameter("delete") != null) {
            JdbcUtil util = new JdbcUtil();
            Connection conn = util.getConnection();
            String sql = "DELETE FROM travelimage WHERE ImageID=?";
            PreparedStatement pst = null;
            ResultSet rs = null;
            try {
                pst = conn.prepareStatement(sql);
                pst.setInt(1,Integer.parseInt(req.getParameter("imageid")));
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                util.close(rs, pst, conn);
            }
            resp.sendRedirect("my_photos");
            return;
        }
        req.getRequestDispatcher("my_photos.jsp").forward(req, resp);
    }

}
