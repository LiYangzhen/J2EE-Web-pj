package servlet;

import dao.ImageDAO;
import impl.ImageDAOJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Details extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ImageDAO imageDAO = new ImageDAOJdbcImpl();



        String sql = "select Content,Description,Title,PATH from travelimage where ImageID=?";

        sql = "select travelimagefavor.UID from travelimagefavor join travelimage on travelimage.ImageID=travelimagefavor.ImageID where travelimage.ImageID=?";
        sql = "select CountryName from travelimage join geocountries on travelimage.CountryCodeISO=geocountries.ISO where ImageID=?";
        sql = "select AsciiName from travelimage join geocities on travelimage.CityCode=geocities.GeoNameID where ImageID=?";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
