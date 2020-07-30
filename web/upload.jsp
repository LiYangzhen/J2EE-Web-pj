<%--
  Created by IntelliJ IDEA.
  User: LYZ
  Date: 2020/7/29
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>上传图片-ImgShow</title>
    <link rel="icon" href="image/logo.svg">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Raleway:400,600" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+SC:wght@400;500;700&display=swap" rel="stylesheet">
    <link href="css/base.css" rel="stylesheet" type="text/css">
    <link href="css/upload.css" rel="stylesheet" type="text/css">
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
        <a href="browse" class="link">阅览</a>
        <div class="dropdown-menu highlight">个人中心
            <ul>
                <li class="menu_item highlight"><a href="upload" class="upload">上传图片</a></li>
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
    <form action="upload" method="post" enctype="multipart/form-data">
        <h3>选择图片</h3>
        <label for="ImagesUpload" id="ImgUpBtn" class="ImgUpBtnBox">
                <%
                if (request.getAttribute("title") == null) {
                    out.println("<input type='file' accept='image/*' name='file' id='ImagesUpload' class='uploadHide'>\n" +
                        "            <img src='image/add.svg' class='uploadBtnImg' width='32' height='32'/>\n" +
                        "        </label>\n" +
                        "        <div class='ImagesUpload' id='img-preview'></div>\n" +
                        "        <div id='removeBox'></div>");
                }else {
                out.println("<div class='ImagesUpload' id='img-preview'><img src='travel-images/large/" + request.getAttribute("path") + "'></div>");
            }
        %>

            <label><p>图片标题</p>
                <input type="text" name="title" class="title"
                    <%
                if (request.getAttribute("title") != null) {
                    out.println("value='"+request.getAttribute("title")+"'");
                }
                   %>
                       required>
            </label>
            <label><p>图片描述</p>
                <textarea name="description" class="description" required><%
                    if (request.getAttribute("title") != null) {
                        out.print(request.getAttribute("description"));
                    }
                %>
            </textarea></label>
            <label>
                <select name="theme" required>
                    <option value="placeholder" selected disabled>按主题筛选</option>
                    <option value="scenery">scenery</option>
                    <option value="city">city</option>
                    <option value="people">people</option>
                    <option value="animal">animal</option>
                    <option value="building">building</option>
                    <option value="wonder">wonder</option>
                    <option value="other">other</option>
                </select>
                <select name="country" id="country" onchange="addOption()">
                    <option value="placeholder" selected>按国家筛选</option>
                </select>
                <select name="city" id="city"></select>
            </label>
                <%
        if (request.getAttribute("title") != null) {
                out.println("<input type='submit' name='upload' class='submit' value='修改'>");
        } else {
                out.print(" <input type='submit' name='upload' class='submit' value='上传'>");
            }
        %>
    </form>
</main>

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
<script src="js/UIscript.js" rel="script" type="text/javascript" defer></script>
</body>
</html>
