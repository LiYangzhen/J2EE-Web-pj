<%--
  Created by IntelliJ IDEA.
  User: LYZ
  Date: 2020/7/30
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="classes.JdbcUtil" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>我的收藏-ImgShow</title>
    <link rel="icon" href="image/logo.svg">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Raleway:400,600" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+SC:wght@400;500;700&display=swap" rel="stylesheet">
    <link href="css/base.css" rel="stylesheet" type="text/css">
    <link href="css/my_ImageGroup.css" rel="stylesheet" type="text/css">
    <script src="js/prefixfree.min.js" rel="script" type="text/javascript"></script>
</head>
<body>
<header class="mainImg">
    <nav class="banner">
        <a href="index.jsp" class="logo">
            <img src="image/logo.svg" alt="logo">
            <p>ImgShow</p>
        </a>
        <a href="home" class="link">首页</a>
        <a href="search" class="link">搜索</a>
        <a href="browse" class="link">浏览</a>
        <div class="dropdown-menu highlight">个人中心
            <ul>
                <li class="menu_item"><a href="upload" class="upload">上传图片</a></li>
                <li class="menu_item"><a href="my_photos" class="my-pictures">我的图片</a></li>
                <li class="menu_item"><a href="my_favourite.jsp" class="collections">我的收藏</a></li>
                <li class="menu_item"><a href="friends.jsp" class="friends">我的好友</a></li>
                <li class="menu_item"><a href="login?logout=true" class="logout">退出登录</a></li>
            </ul>
        </div>
    </nav>
</header>

<aside id="sidebar" class="sidebar">
    <a href="javascript:toTop()" id="toTop"><span>︿</span><span>Top</span></a>
</aside>

<main>
    <h2>「<%=request.getParameter("username")%>」的收藏</h2>
    <section class="imgGroup">
        <ul>
            <%
                JdbcUtil util = new JdbcUtil();
                Connection conn = util.getConnection();
                String sql = "SELECT travelimagefavor.ImageID,PATH,Title,Description FROM travelimagefavor " +
                        "JOIN travelimage ON travelimage.ImageID=travelimagefavor.ImageID WHERE travelimagefavor.UID=?";
                PreparedStatement pst = null;
                ResultSet rs = null;
                try {
                    pst = conn.prepareStatement(sql);
                    if (request.getParameter("UID") != null) {
                        pst.setInt(1, Integer.parseInt(request.getParameter("UID")));
                    }
                    rs = pst.executeQuery();

                    while (rs.next()) {
                        out.println("<li class='thumbnail' title='" + rs.getString(3) + "'>" +
                                "<a href='details.jsp?imageid=" + rs.getInt(1) + "'>" +
                                "<div class='img-box'>" +
                                "<img src='travel-images/small/" + rs.getString(2) + "' alt='图片'>" +
                                "</div>" +
                                "<div><h3>" + rs.getString(3) + "</h3>" +
                                "<p>" + rs.getString(4) + "</p>" +
                                "</div>" +
                                "</a>" +
                                "</li>");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    util.close(rs, pst, conn);
                }

            %>
        </ul>
    </section>
</main>

<div id="pagination" class="pagination">
    <ul>

    </ul>
</div>

<footer>
    <!--    页脚横幅-->
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

