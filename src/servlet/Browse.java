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

public class Browse extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("submit2") != null) {
            ImageDAO imageDAO = new ImageDAOJdbcImpl();
            int num = imageDAO.getResultNum(req.getParameter("title"));
            List<Image> images = imageDAO.getResult(req.getParameter("title"), 0, "Title");
            StringBuilder stringBuilder = new StringBuilder();
            for (Image image : images) {
                String str = "<li class='thumbnail' title='" + image.getTitle() + "'>\n" +
                        "<a href='details.jsp?imageid=" + image.getImageId() + "'>\n" +
                        "<div class='img-box'>\n" +
                        "<img src='travel-images/small/" + image.getPATH() + "' alt='图片'>\n" +
                        "</div>\n" +
                        "</a>\n" +
                        "</li>\n";
                stringBuilder.append(str);
            }

            req.setAttribute("content", stringBuilder);
            req.setAttribute("page", 0);
            req.setAttribute("num", num);
        } else if (req.getParameter("submit1") != null) {
            ImageDAO imageDAO = new ImageDAOJdbcImpl();
            int num = 0;
            List<Image> images = null;
            if (req.getParameter("theme") != null && !req.getParameter("theme").equals("placeholder") &&
                    req.getParameter("country").equals("placeholder")) {
                num = imageDAO.browseThemeNum(req.getParameter("theme"));
                images = imageDAO.browseTheme(req.getParameter("theme"), 0);
            } else if ((req.getParameter("theme").equals("placeholder") || req.getParameter("theme") == null) && !req.getParameter("country").equals("placeholder")) {
                if (req.getParameter("city").equals("placeholder") || req.getParameter("city") == null) {
                    num = imageDAO.browseCountryNum(req.getParameter("country"));
                    images = imageDAO.browseCountry(req.getParameter("country"), 0);
                } else {
                    num = imageDAO.browseCityNum(req.getParameter("city"));
                    images = imageDAO.browseCity(req.getParameter("city"), 0);
                }
            } else if (!req.getParameter("theme").equals("placeholder") && req.getParameter("theme") != null && !req.getParameter("country").equals("placeholder")) {
                if (req.getParameter("city").equals("placeholder") || req.getParameter("city") == null) {
                    num = imageDAO.browseCountryThemeNum(req.getParameter("country"), req.getParameter("theme"));
                    images = imageDAO.browseCountryTheme(req.getParameter("country"), req.getParameter("theme"), 0);
                } else {
                    num = imageDAO.browseCityThemeNum(req.getParameter("city"), req.getParameter("theme"));
                    images = imageDAO.browseCityTheme(req.getParameter("city"), req.getParameter("theme"), 0);
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            System.out.println(num);

            if (images != null) {
                for (Image image : images) {
                    String str = "<li class='thumbnail' title='" + image.getTitle() + "'>\n" +
                            "<a href='details.jsp?imageid=" + image.getImageId() + "'>\n" +
                            "<div class='img-box'>\n" +
                            "<img src='travel-images/small/" + image.getPATH() + "' alt='图片'>\n" +
                            "</div>\n" +
                            "</a>\n" +
                            "</li>\n";
                    stringBuilder.append(str);
                }
            }

            req.setAttribute("content", stringBuilder);
            req.setAttribute("page", 0);
            req.setAttribute("num", num);
        }
        req.getRequestDispatcher("browse.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 0;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }
        if (req.getParameter("submit2") != null) {
            ImageDAO imageDAO = new ImageDAOJdbcImpl();
            int num = imageDAO.getResultNum(req.getParameter("title"));
            List<Image> images = imageDAO.getResult(req.getParameter("title"), page, "Title");
            StringBuilder stringBuilder = new StringBuilder();
            for (Image image : images) {
                String str = "<li class='thumbnail' title='" + image.getTitle() + "'>\n" +
                        "<a href='details.jsp?imageid=" + image.getImageId() + "'>\n" +
                        "<div class='img-box'>\n" +
                        "<img src='travel-images/small/" + image.getPATH() + "' alt='图片'>\n" +
                        "</div>\n" +
                        "</a>\n" +
                        "</li>\n";
                stringBuilder.append(str);
            }
            req.setAttribute("content", stringBuilder);
            req.setAttribute("num", num);
            req.setAttribute("page", req.getParameter("page"));
        }else if (req.getParameter("submit1")!=null){
            ImageDAO imageDAO = new ImageDAOJdbcImpl();
            int num = 0;
            List<Image> images = null;
            if (req.getParameter("theme") != null && !req.getParameter("theme").equals("placeholder") &&
                    req.getParameter("country").equals("placeholder")) {
                num = imageDAO.browseThemeNum(req.getParameter("theme"));
                images = imageDAO.browseTheme(req.getParameter("theme"), page);
            } else if ((req.getParameter("theme").equals("placeholder") || req.getParameter("theme") == null) && !req.getParameter("country").equals("placeholder")) {
                if (req.getParameter("city").equals("placeholder") || req.getParameter("city") == null) {
                    num = imageDAO.browseCountryNum(req.getParameter("country"));
                    images = imageDAO.browseCountry(req.getParameter("country"), page);
                } else {
                    num = imageDAO.browseCityNum(req.getParameter("city"));
                    images = imageDAO.browseCity(req.getParameter("city"), page);
                }
            } else if (!req.getParameter("theme").equals("placeholder") && req.getParameter("theme") != null && !req.getParameter("country").equals("placeholder")) {
                if (req.getParameter("city").equals("placeholder") || req.getParameter("city") == null) {
                    num = imageDAO.browseCountryThemeNum(req.getParameter("country"), req.getParameter("theme"));
                    images = imageDAO.browseCountryTheme(req.getParameter("country"), req.getParameter("theme"), page);
                } else {
                    num = imageDAO.browseCityThemeNum(req.getParameter("city"), req.getParameter("theme"));
                    images = imageDAO.browseCityTheme(req.getParameter("city"), req.getParameter("theme"), page);
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            System.out.println(images);

            if (images != null) {
                for (Image image : images) {
                    String str = "<li class='thumbnail' title='" + image.getTitle() + "'>\n" +
                            "<a href='details.jsp?imageid=" + image.getImageId() + "'>\n" +
                            "<div class='img-box'>\n" +
                            "<img src='travel-images/small/" + image.getPATH() + "' alt='图片'>\n" +
                            "</div>\n" +
                            "</a>\n" +
                            "</li>\n";
                    stringBuilder.append(str);
                }
            }

            req.setAttribute("content", stringBuilder);
            req.setAttribute("page", page);
            req.setAttribute("num", num);
            System.out.println(num);
            System.out.println(page);
        }


        req.getRequestDispatcher("browse.jsp").forward(req, resp);
    }
}
