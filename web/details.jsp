<%@ page import="classes.JdbcUtil" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.text.NumberFormat" %><%--
  Created by IntelliJ IDEA.
  User: LYZ
  Date: 2020/7/12
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>图片详情-ImgShow</title>
    <link rel="icon" href="image/logo.svg">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Raleway:400,600" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+SC:wght@400;500;700&display=swap" rel="stylesheet">
    <link href="css/base.css" rel="stylesheet" type="text/css">
    <link href="css/details.css" rel="stylesheet" type="text/css">
    <script src="js/prefixfree.min.js" rel="script" type="text/javascript"></script>
</head>
<body class="details">
<header class="mainImg">
    <nav class="banner">
        <a href="index.jsp" class="logo">
            <img src="image/logo.svg" alt="logo">
            <p>ImgShow</p>
        </a>
        <a href="home" class="link">首页</a>
        <a href="search" class="link">搜索</a>
        <a href="browse" class="link">阅览</a>
        <%
            if (session.getAttribute("UID") != null) {
        %>
        <div class="dropdown-menu">
            <a href="">个人中心</a>
            <ul>
                <li class="menu_item"><a href="upload" class="upload">上传图片</a></li>
                <li class="menu_item"><a href="my_photos" class="my-pictures">我的图片</a></li>
                <li class="menu_item"><a href="my_favourite.jsp" class="collections">我的收藏</a></li>
                <li class="menu_item"><a href="friends.jsp" class="friends">我的好友</a></li>
                <li class="menu_item"><a href="login?logout=true" class="logout">退出登录</a></li>
            </ul>
        </div>
        <%
        } else {
        %>
        <a href="login" class="link">登录</a>
        <%
            }
        %>
    </nav>
</header>

<aside id="sidebar" class="sidebar">
    <a href="javascript:toTop()" id="toTop"><span>︿</span><span>Top</span></a>
</aside>

<main>

    <%
        JdbcUtil util = new JdbcUtil();
        Connection conn = util.getConnection();
        String sql = "select Content,Description,Title,PATH from travelimage where ImageID=?";
        PreparedStatement pst = null;
        ResultSet rs = null;

        int imageid = 0;
        String content = "";
        String description = "";
        String title = "";
        String path = "";
        int UID = -1;
        String countryName = "";
        String cityName = "";
        int favour = 0;
        boolean collected = false;
        String collectedClass="";

        if (request.getParameter("imageid") != null) {
            imageid = Integer.parseInt(request.getParameter("imageid"));
        }

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, imageid);
            rs = pst.executeQuery();
            if (rs.next()) {
                content = rs.getString(1);
                description = rs.getString(2);
                title = rs.getString(3);
                path = rs.getString(4);
            }

            sql = "select travelimagefavor.UID from travelimagefavor join travelimage on travelimage.ImageID=travelimagefavor.ImageID where travelimage.ImageID=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, imageid);
            rs = pst.executeQuery();
            if (session.getAttribute("UID") != null) {
                while (rs.next()) {
                    if (rs.getInt(1) == (int) session.getAttribute("UID")) {
                        collected = true;
                    }
                    favour++;
                }
            }else {
                while (rs.next()) {
                    favour++;
                }
            }
            collectedClass = collected ? "favor" : "";

            sql = "select CountryName from travelimage join geocountries on travelimage.CountryCodeISO=geocountries.ISO where ImageID=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, imageid);
            rs = pst.executeQuery();
            if (rs.next()) {
                countryName = rs.getString(1);
            }

            sql = "select AsciiName from travelimage join geocities on travelimage.CityCode=geocities.GeoNameID where ImageID=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, imageid);
            rs = pst.executeQuery();
            if (rs.next()) {
                cityName = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs, pst, conn);
        }
    %>
    <h2><%=title%></h2>
    <figure>
    <img src='travel-images/medium/<%=path%>'>
    <div class='content'>
        <ul>
            <li> 收藏人数 </li>
            <li class='collection_number'><span > <%=favour%> </span>
                <a href = 'collect?imageid=<%=imageid%>&collected=<%=collected%>' class='<%=collectedClass%> collect' > </a></li>
            </ul>
        <ul>
            <li> 图片信息 </li>
            <li> 主题:<span class='subject'> <%=content%> </span></li>
            <li> 国家:<span class='country'> <%=cityName%> </span></li>
            <li> 城市:<span class='city'> <%=cityName%> </span></li>
            </ul>
        </div>
    </figure>
    <article>
    <p><%=description%></p>
    </article >
</main>
<footer>
    <div class="footer__nav">
        <ul>
            <li>
                <a class="footer__navbar__logo" href="index.jsp">
                    <img src="image/logo.svg" alt="logo"/>
                    <div class="footer__logo__text">
                        <p><span>ImgShow</span> — 免费的精美照片由我们认真负责的TA们提供。</p>
                    </div>
                </a>
            </li>
        </ul>
        <p> © 2020-现在 版权所有 <a href="http://www.beian.miit.gov.cn">陕ICP备20010010号-1</a><a href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=61070202000390">沪公网备案61070202000390号</a></p>
        <ul class="footer__nav__list">
            <li>
                <a class="link" href="" onclick="alert('别攻击就行')">使用条款</a>
            </li>
            <li>
                <a class="link" href="" onclick="alert('我们没有隐私政策')">隐私政策</a>
            </li>
            <li>
                <a class="link" href="">许可证书</a>
            </li>
            <li>
                <a class="link" href="" onclick="alert('盘古开天地1.0版')">版本说明</a>
            </li>
            <li>
                <div class="languageChoose">
                    <img src="image/CN.svg" alt="Chinese">
                    <p>简体中文</p>
                </div>
            </li>
        </ul>
    </div>
</footer>
<script src="js/UIscript.js" rel="script" type="text/javascript"></script>
</body>
</html>
