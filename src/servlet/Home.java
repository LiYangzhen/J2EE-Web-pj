package servlet;

import dao.ImageDAO;
import domain.Image;
import impl.ImageDAOJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Home extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ImageDAO imageDAO = new ImageDAOJdbcImpl();
        List<Image>  images = imageDAO.getLimit(0);
        generate(req, resp, images);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ImageDAO imageDAO = new ImageDAOJdbcImpl();
        List<Image> images = imageDAO.getRand();
        generate(req, resp, images);
    }

    private void generate(HttpServletRequest req, HttpServletResponse resp, List<Image> images) throws ServletException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        for (Image image : images) {
            String str = "<li class='thumbnail' title='" + image.getTitle() + "'>\n" +
                    "<a href='details.jsp?imageid=" + image.getImageId() + "'>\n" +
                    "<div class='img-box'>\n" +
                    "<img src='travel-images/small/" + image.getPATH() + "' alt='图片' width='260' height='200'>\n" +
                    "</div>\n" +
                    "<div><h3>" + image.getTitle() + "</h3 >\n" +
                    "<p>" + image.getDescription() + "</p>\n" +
                    "</div>\n" +
                    "</a>\n" +
                    "</li>\n";
            stringBuilder.append(str);
        }
        req.setAttribute("content", stringBuilder);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
