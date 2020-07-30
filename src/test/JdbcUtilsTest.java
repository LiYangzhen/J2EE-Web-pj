package test;

import db.JdbcUtils;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtilsTest {

    @Test
    public void testGetConnection() throws SQLException, IOException {
        String fileName ="Explosion_1080.jpg";
        InputStream is1 = new FileInputStream(new File("F:\\代码\\J2EE Web\\project\\out\\artifacts\\project_war_exploded2\\travel-images\\upload\\"+fileName));
        InputStream is2 = new FileInputStream(new File("F:\\代码\\J2EE Web\\project\\out\\artifacts\\project_war_exploded2\\travel-images\\upload\\"+fileName));
        InputStream is3 = new FileInputStream(new File("F:\\代码\\J2EE Web\\project\\out\\artifacts\\project_war_exploded2\\travel-images\\upload\\"+fileName));
        InputStream is4 = new FileInputStream(new File("F:\\代码\\J2EE Web\\project\\out\\artifacts\\project_war_exploded2\\travel-images\\upload\\"+fileName));
        OutputStream os1 = new FileOutputStream(new File("F:\\代码\\J2EE Web\\project\\out\\artifacts\\project_war_exploded2\\travel-images\\thumb\\"+fileName));
        OutputStream os2 = new FileOutputStream(new File("F:\\代码\\J2EE Web\\project\\out\\artifacts\\project_war_exploded2\\travel-images\\small\\"+fileName));
        OutputStream os3 = new FileOutputStream(new File("F:\\代码\\J2EE Web\\project\\out\\artifacts\\project_war_exploded2\\travel-images\\medium\\"+fileName));
        OutputStream os4 = new FileOutputStream(new File("F:\\代码\\J2EE Web\\project\\out\\artifacts\\project_war_exploded2\\travel-images\\large\\"+fileName));

        resizeImage(is1, os1, 100, "jpg");
        resizeImage(is2, os2, 320, "jpg");
        resizeImage(is3, os3, 640, "jpg");
        resizeImage(is4, os4, 1024, "jpg");

    }

    public static void resizeImage(InputStream is, OutputStream os, int size, String format) throws IOException {
        BufferedImage prevImage = ImageIO.read(is);
        double width = prevImage.getWidth();
        double height = prevImage.getHeight();
        double percent = size/width;
        int newWidth = (int)(width * percent);
        int newHeight = (int)(height * percent);
        BufferedImage image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_BGR);
        Graphics graphics = image.createGraphics();
        graphics.drawImage(prevImage, 0, 0, newWidth, newHeight, null);
        ImageIO.write(image, format, os);
        os.flush();
        is.close();
        os.close();
    }
}
