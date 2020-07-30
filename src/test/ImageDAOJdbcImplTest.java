package test;

import dao.ImageDAO;
import domain.Image;
import impl.ImageDAOJdbcImpl;
import org.junit.Test;

import java.util.List;

public class ImageDAOJdbcImplTest {

    private ImageDAO imageDAO = new ImageDAOJdbcImpl();

    @Test
    public void testGetInt(){
        Image image = imageDAO.get(1);
        System.out.println(image);
    }

    @Test
    public void testGetAll(){
        List<Image> images = imageDAO.getAll();
        System.out.println(images);
    }

    @Test
    public void testGetLimit(){
        List<Image> images = imageDAO.getLimit(0);
        System.out.println(images.get(0));
    }

    @Test
    public void testGet(){
        Image image = imageDAO.get(1);
        System.out.println(image.getTitle());
    }

    @Test
    public void testGetResult(){
 System.out.println(imageDAO.getResult("a",  0, "Title"));
    }

    @Test
    public void testGetResultNum(){
        System.out.println(imageDAO.getResultNum("a"));
    }

    @Test
    public void testGetDesils(){
//        Image image = imageDAO.getDetails(1);
        System.out.println((int)5/2);
    }

    @Test
    public  void test(){
        imageDAO.delete(1);
    }
}
