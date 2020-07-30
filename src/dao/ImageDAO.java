package dao;

import domain.Image;

import java.util.List;

public interface ImageDAO {

    public List<Image> getAll();

    public void save(Image image);

    public Image get(int imageId);

    public void delete(int imageId);

    public List<Image> getLimit(int num);

    public List<Image> getRand();

    public Image getDetails(int imageId, int UID);

    public List<Image> getResult(String str, int page, String goal);

    public int getResultNum(String str);

    public int getNum(String sql);

    public List<Image> browseCountry(String country, int page);

    public int browseCountryNum(String country);

    public List<Image> browseCity(String city, int page);

    public int browseCityNum(String city);

    public List<Image> browseTheme(String theme, int page);

    public int browseThemeNum(String theme);

    public List<Image> browseCountryTheme(String country, String theme, int page);

    public int browseCountryThemeNum(String country, String theme);

    public List<Image> browseCityTheme(String city, String theme, int page);

    public int browseCityThemeNum(String city, String theme);

    /**
     * 返回和title相同记录数
     *
     * @param title
     * @return
     */
    public long getCountWithTitle(String title);

    public List<Image> getMyPhotos(int UID, int page);

    public int getMyPhotosNum(int UID);
}
