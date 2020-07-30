package servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import classes.JdbcUtil;
import dao.ImageDAO;
import domain.Image;
import impl.ImageDAOJdbcImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(name = "UploadServlet", value = "/upload")
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY1 = "travel-images/upload";
    private static final String UPLOAD_DIRECTORY2 = "travel-images/thumb";
    private static final String UPLOAD_DIRECTORY3 = "travel-images/small";
    private static final String UPLOAD_DIRECTORY4 = "travel-images/medium";
    private static final String UPLOAD_DIRECTORY5 = "travel-images/large";

    // 上传配置
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("imageid") != null) {
            int imageId = Integer.parseInt(req.getParameter("imageid"));
            ImageDAO imageDAO = new ImageDAOJdbcImpl();
            Image image = imageDAO.get(imageId);

            req.setAttribute("title", image.getTitle());
            req.setAttribute("description", image.getDescription());
            req.setAttribute("theme", image.getContent());
            req.setAttribute("country", image.getCountryName());
            req.setAttribute("city", image.getAsciiName());
            req.setAttribute("path", image.getPATH());
        }
        req.getRequestDispatcher("upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("upload") == null) {

            if (!ServletFileUpload.isMultipartContent(req)) {
                // 如果不是则停止
                PrintWriter writer = resp.getWriter();
                writer.println("Error: 表单必须包含 enctype=multipart/form-data");
                writer.flush();
                return;
            }

            // 配置上传参数
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
            factory.setSizeThreshold(MEMORY_THRESHOLD);
            // 设置临时存储目录
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            ServletFileUpload upload = new ServletFileUpload(factory);

            // 设置最大文件上传值
            upload.setFileSizeMax(MAX_FILE_SIZE);

            // 设置最大请求值 (包含文件和表单数据)
            upload.setSizeMax(MAX_REQUEST_SIZE);

            // 中文处理
            upload.setHeaderEncoding("UTF-8");

            // 构造临时路径来存储上传的文件
            // 这个路径相对当前应用的目录
            String uploadPath1 = req.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY1;
            String uploadPath2 = req.getServletContext().getRealPath("./") + UPLOAD_DIRECTORY2;
            String uploadPath3 = req.getServletContext().getRealPath("./") + UPLOAD_DIRECTORY3;
            String uploadPath4 = req.getServletContext().getRealPath("./") + UPLOAD_DIRECTORY4;
            String uploadPath5 = req.getServletContext().getRealPath("./") + UPLOAD_DIRECTORY5;


            // 如果目录不存在则创建
            File uploadDir = new File(uploadPath1);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            try {
                // 解析请求的内容提取文件数据
                @SuppressWarnings("unchecked")
                List<FileItem> formItems = upload.parseRequest(req);


                if (formItems != null && formItems.size() > 0) {
                    // 迭代表单数据
                    if (formItems.get(0) != null) {
                        FileItem item = formItems.get(0);
                        // 处理不在表单中的字段
                        if (!item.isFormField()) {
                            String fileName = new File(item.getName()).getName();
                            String filePath1 = uploadPath1 + File.separator + fileName;
                            File storeFile = new File(filePath1);

                            // 保存文件到硬盘
                            item.write(storeFile);
                            //文件上传成功!

                            String filePath2 = uploadPath2 + File.separator + fileName;
                            String filePath3 = uploadPath3 + File.separator + fileName;
                            String filePath4 = uploadPath4 + File.separator + fileName;
                            String filePath5 = uploadPath5 + File.separator + fileName;

                            System.out.println(fileName);

                            String title = formItems.get(1).getString();
                            String description = formItems.get(2).getString();
                            String theme = formItems.get(3).getString();
                            String country = formItems.get(4).getString();
                            String city = formItems.get(5).getString();

                            JdbcUtil util = new JdbcUtil();
                            Connection conn = util.getConnection();
                            PreparedStatement pst = null;
                            ResultSet rs = null;
                            try {
                                String sql = null;
                                String iso = null;
                                double latitude = 0;
                                double longitude = 0;
                                int cityCode = 0;
                                int imageId = 0;
                                int uid = (int) req.getSession().getAttribute("UID");

                                if (req.getParameter("city") != "placeholder") {
                                    sql = "SELECT Latitude,Longitude,GeoNameID,CountryCodeISO FROM geocities WHERE AsciiName=? LIMIT 1";
                                    pst = conn.prepareStatement(sql);
                                    pst.setString(1, city);
                                    rs = pst.executeQuery();
                                    if (rs.next()) {
                                        latitude = rs.getDouble(1);
                                        longitude = rs.getDouble(2);
                                        cityCode = rs.getInt(3);
                                        iso = rs.getString(4);
                                    }
                                } else {
                                    sql = "SELECT ISO FROM geocountries WHERE CountryName=?";
                                    pst = conn.prepareStatement(sql);
                                    pst.setString(1, country);
                                    rs = pst.executeQuery();
                                    if (rs.next()) {
                                        iso = rs.getString(1);
                                    }
                                }
                                sql = "SELECT MAX(ImageID) FROM travelimage";
                                rs = pst.executeQuery(sql);
                                if (rs.next()) {
                                    imageId = rs.getInt(1) + 1;
                                }

                                sql = "INSERT INTO travelimage (ImageID,Title,Description,Latitude,Longitude,CityCode,CountryCodeISO,UID,PATH,Content) VALUES (?,?,?,?,?,?,?,?,?,?)";
                                pst = conn.prepareStatement(sql);
                                pst.setInt(1, imageId);
                                pst.setString(2, title);
                                pst.setString(3, description);
                                pst.setDouble(4, latitude);
                                pst.setDouble(5, longitude);
                                pst.setInt(6, cityCode);
                                pst.setString(7, iso);
                                pst.setInt(8, uid);
                                pst.setString(9, fileName);
                                pst.setString(10, theme);
                                pst.executeUpdate();

                            } catch (SQLException e) {
                                e.printStackTrace();
                            } finally {
                                util.close(rs, pst, conn);
                            }

                            resp.sendRedirect("my_photos");
                            return;
                        }
                    }
                }
            } catch (Exception ex) {
                String referer = req.getHeader("Referer");
                req.getRequestDispatcher(referer);
            }
        }
    }

    public static void resizeImage(InputStream is, OutputStream os, int size, String format) throws IOException {
        BufferedImage prevImage = ImageIO.read(is);
        double width = prevImage.getWidth();
        double height = prevImage.getHeight();
        double percent = size / width;
        int newWidth = (int) (width * percent);
        int newHeight = (int) (height * percent);
        BufferedImage image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_BGR);
        Graphics graphics = image.createGraphics();
        graphics.drawImage(prevImage, 0, 0, newWidth, newHeight, null);
        ImageIO.write(image, format, os);
        os.flush();
        is.close();
        os.close();
    }
}
