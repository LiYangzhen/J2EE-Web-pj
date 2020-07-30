<%--
  Created by IntelliJ IDEA.
  User: LYZ
  Date: 2020/7/28
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>搜索页-ImgShow</title>
    <link rel="icon" href="image/logo.svg">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Raleway:400,600" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+SC:wght@400;500;700&display=swap" rel="stylesheet">
    <link href="css/base.css" rel="stylesheet" type="text/css">
    <link href="css/my_ImageGroup.css" rel="stylesheet" type="text/css">
    <link href="css/search.css" rel="stylesheet" type="text/css">
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
        <a href="search" class="highlight link">搜索</a>
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
    <form method="post" action="search">
        <h1>搜索</h1>
        <div class="search_box">
            <input type="radio" value="search_by_title" name="search_type" id="search_by_title"
                <%
                if ((request.getParameter("submit") != null && request.getParameter("title") != null)||(request.getParameter("search_type") != null && request.getParameter("search_type").equals("search_by_title"))){
                    out.print("checked");
                }
            %>>
            <label for="search_by_title">按标题查询</label>
            <input type="search" name="title"
                <%
                 if ((request.getParameter("submit") != null && request.getParameter("title") != null)||(request.getParameter("search_type") != null && request.getParameter("search_type").equals("search_by_title"))){
                    out.print("value='"+request.getParameter("title")+"'");
                }
            %>
            >
            <input type="radio" value="search_by_description" name="search_type" id="search_by_description"
                <%
                if ((request.getParameter("submit") != null && request.getParameter("description") != null)||(request.getParameter("search_type") != null && request.getParameter("search_type").equals("search_by_description"))){
                    out.print("checked");
                }
            %>
            >
            <label for="search_by_description">按描述查询</label>
            <textarea type="search" name="description">
                <%
                    if ((request.getParameter("submit") != null && request.getParameter("description") != null) || (request.getParameter("search_type") != null && request.getParameter("search_type").equals("search_by_description"))) {
                        out.print(request.getParameter("description"));
                    }
                %>
            </textarea>
            <input type="submit" value="搜索" name="search" id="search">
        </div>
    </form>
    <section class="imgGroup">
        <%
            if (request.getAttribute("page") != null) {
                out.print(request.getAttribute("content"));
            }
        %>
    </section>
</main>
<div id="pagination" class="pagination">
    <ul>
        <%
            if ((request.getParameter("search_type") != null && request.getParameter("search_type").equals("search_by_title"))) {
                out.print(classes.pages.creatPageNumber("search1", (int) request.getAttribute("num"), (int) request.getAttribute("page"), null, null, null, request.getParameter("title"), null));
            } else if (request.getParameter("search_type") != null && request.getParameter("search_type").equals("search_by_description")) {
                out.print(classes.pages.creatPageNumber("search2", (int) request.getAttribute("num"), (int) request.getAttribute("page"), null, null, null, null, request.getParameter("description")));
            } else if (request.getParameter("submit") != null && request.getParameter("title") != null) {
                int num = (int) request.getAttribute("num");
                int pages = Integer.parseInt((String) request.getAttribute("page"));
                out.print(classes.pages.creatPageNumber("search1", num, pages, null, null, null, request.getParameter("title"), null));
            } else if (request.getParameter("submit") != null && request.getParameter("description") != null) {
                int num = (int) request.getAttribute("num");
                int pages = Integer.parseInt((String) request.getAttribute("page"));
                out.print(classes.pages.creatPageNumber("search2", num, pages, null, null, null, null, request.getParameter("description")));
            }
        %>
    </ul>
</div>
<footer>
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
