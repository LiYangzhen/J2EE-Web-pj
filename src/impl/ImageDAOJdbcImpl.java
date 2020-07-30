package impl;

import dao.DAO;
import dao.ImageDAO;
import domain.Image;

import java.lang.reflect.Array;
import java.util.*;


public class ImageDAOJdbcImpl extends DAO<Image> implements ImageDAO {
    @Override
    public List<Image> getAll() {
        String sql = "SELECT ImageId,Title,Description,PATH FROM travelimage";
        return getForList(sql);
    }

    public List<Image> getLimit(int num) {
        String sql = "select travelimagefavor.ImageID ,travelimage.PATH,travelimage.Title,travelimage.Description ,count(*) from travelimagefavor JOIN travelimage ON travelimage.ImageID=travelimagefavor.ImageID group by travelimage.ImageID order by count(*) DESC LIMIT " + num + ",10";
        return getForList(sql);
    }

    public List<Image> getRand() {
        String sql = "SELECT ImageID ,PATH,Title,Description FROM travelimage ORDER BY RAND() LIMIT 10";
        return getForList(sql);
    }

    public List<Image> getResult(String str, int page, String goal) {
        page = page * 9;
        String sql = "SELECT ImageID,PATH,Title,Description FROM travelimage WHERE " + goal + " LIKE '%" + str + "%' LIMIT " + page + ",9";
        return getForList(sql);
    }

    public List<Image> browseCountry(String country, int page) {
        page = page * 9;
        String sql = "SELECT ImageID,PATH,Title FROM travelimage JOIN geocountries ON travelimage.CountryCodeISO=geocountries.ISO WHERE CountryName='" + country + "' LIMIT " + page + ",9";
        return getForList(sql);
    }

    public int browseCountryNum(String country) {
        String sql = "SELECT ImageID FROM travelimage JOIN geocountries ON travelimage.CountryCodeISO=geocountries.ISO WHERE CountryName='" + country + "'";
        return getForList(sql).size();
    }

    public List<Image> browseCity(String city, int page) {
        page = page * 9;
        String sql = "SELECT ImageID,PATH,Title FROM travelimage JOIN geocities ON travelimage.CityCode=geocities.GeoNameID WHERE AsciiName LIKE '" + city + "' LIMIT " + page + ",9";
        return getForList(sql);
    }

    public int browseCityNum(String city) {
        String sql = "SELECT ImageID FROM travelimage JOIN geocities ON travelimage.CityCode=geocities.GeoNameID WHERE AsciiName LIKE '" + city + "'";
        return getForList(sql).size();
    }

    public List<Image> browseTheme(String theme, int page) {
        page = page * 9;
        String sql = "SELECT ImageID,PATH,Title FROM travelimage WHERE Content='" + theme + "' LIMIT " + page + ",9";
        return getForList(sql);
    }

    public int browseThemeNum(String theme) {
        String sql = "SELECT ImageID,PATH,Title FROM travelimage WHERE Content='" + theme + "'";
        return getForList(sql).size();
    }

    public List<Image> browseCountryTheme(String country, String theme, int page) {
        page = page * 9;
        String sql = "SELECT ImageID,PATH,Title FROM travelimage JOIN geocountries ON travelimage.CountryCodeISO=geocountries.ISO WHERE Content='" + theme + "' AND CountryName='" + country + "' LIMIT " + page + ",9";
        return getForList(sql);
    }

    public int browseCountryThemeNum(String country, String theme) {
        String sql = "SELECT ImageID,PATH,Title FROM travelimage JOIN geocountries ON travelimage.CountryCodeISO=geocountries.ISO WHERE Content='" + theme + "' AND CountryName='" + country;
        return getForList(sql).size();
    }

    public List<Image> browseCityTheme(String city, String theme, int page) {
        page = page * 9;
        String sql = "SELECT ImageID,PATH,Title FROM travelimage JOIN geocities ON travelimage.CityCode=geocities.GeoNameID WHERE Content='" + theme + "' AND AsciiName LIKE '" + city + "' LIMIT " + page + ",9";
        return getForList(sql);
    }

    public int browseCityThemeNum(String city, String theme) {
        String sql = "SELECT ImageID,PATH,Title FROM travelimage JOIN geocities ON travelimage.CityCode=geocities.GeoNameID WHERE Content='" + theme + "' AND AsciiName LIKE '" + city + "'";
        return getForList(sql).size();
    }

    @Override
    public int getResultNum(String str) {
        String sql = "SELECT ImageID FROM travelimage WHERE Title LIKE '%" + str + "%'";
        return getForList(sql).size();
    }

    public Image getDetails(int imageId, int UID) {
        Image image = new Image();
//        String sql = "select Content,Description,Title,PATH from travelimage where ImageID=?";
//        Image image1 = get(sql, imageId);
//        image.setContent(image1.getContent());
//        image.setDescription(image1.getDescription());
//        image.setTitle(image1.getTitle());
//        image.setPATH(image1.getPATH());
//
//        sql = "select travelimagefavor.UID from travelimagefavor join travelimage on travelimage.ImageID=travelimagefavor.ImageID where travelimage.ImageID="+imageId;
//        List<Image> images = getForList(sql);
//        image.setCollectedNum(images.size());
//        for (Image value : images) {
//            if (value.getUID() == UID) {
//                image.setCollected(true);
//            }
//        }

//        sql = "select CountryName from travelimage join geocountries on travelimage.CountryCodeISO=geocountries.ISO where ImageID=?";
//        sql = "select AsciiName from travelimage join geocities on travelimage.CityCode=geocities.GeoNameID where ImageID=?";

        return image;
    }

    @Override
    public void save(Image image) {

    }

    public int getNum(String sql) {
        return getForList(sql).size();
    }

    @Override
    public Image get(int imageId) {
        String sql = "SELECT ImageId,Title,Description,PATH FROM travelimage WHERE ImageId = ?";
        return get(sql, imageId);
    }

    @Override
    public void delete(int imageId) {
        String sql = "DELECT FROM travelimage WHERE ImageID=?";
        update(sql,imageId);
    }

    @Override
    public long getCountWithTitle(String title) {
        String sql = "SELECT count(ImageId) FROM travelimage WHERE Title = ?";
        return (long) getForValue(sql, title);
    }

    public List<Image> getMyPhotos(int UID,int page) {
        page=page*9;
        String sql = "SELECT ImageID,PATH,Title,Description FROM travelimage WHERE UID=" + UID+" LIMIT "+page+",9";
        return getForList(sql);
    }

    @Override
    public int getMyPhotosNum(int UID) {
        String sql= "SELECT ImageID FROM travelimage WHERE UID="+UID;
        return getForList(sql).size();
    }
}
