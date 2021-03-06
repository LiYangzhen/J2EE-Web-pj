<%--
  Created by IntelliJ IDEA.
  User: LYZ
  Date: 2020/7/29
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>我的图片-ImgShow</title>
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
        <a href="home" class="logo">
            <img src="image/logo.svg" alt="logo">
            <p>ImgShow</p>
        </a>
        <a href="home" class="link">首页</a>
        <a href="search" class="link">搜索</a>
        <a href="browse" class="link">浏览</a>
        <div class="dropdown-menu highlight">个人中心
            <ul>
                <li class="menu_item"><a href="upload" class="upload">上传图片</a></li>
                <li class="menu_item highlight"><a href="my_photos" class="my-pictures">我的图片</a></li>
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
    <h2>我的图片</h2>
    <section class="imgGroup">
        <ul>
        <%=request.getAttribute("content")%>
        </ul>
    </section>
</main>

<div id="pagination" class="pagination">
    <ul>
        <%
            if (request.getParameter("page")==null) {
                out.print(classes.pages.creatPageNumber("my_photo", (int) request.getAttribute("num"), (int) request.getAttribute("page"), null, null, null, null, null));
            }  else if (request.getParameter("page")!=null) {
                int num = (int) request.getAttribute("num");
                int pages = Integer.parseInt((String) request.getAttribute("page"));
                out.print(classes.pages.creatPageNumber("my_photo", num, pages, null, null, null, null, null));
            }
        %>
    </ul>
</div>

<footer>
    <!--    页脚横幅-->
    <div class="footer__nav">
        <ul>
            <li>
                <a class="footer__navbar__logo" href="home">
                    <img src="image/logo.svg" alt="logo"/>
                    <div class="footer__logo__text">
                        <p><span>ImgShow</span> — 免费的精美照片由我们认真负责的TA们提供。</p>
                    </div>
                </a>
            </li>
        </ul>
        <p> © 2020-现在 版权所有 备案号19302010059</p>
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
