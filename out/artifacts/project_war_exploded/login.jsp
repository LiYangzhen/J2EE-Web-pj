<%--
  Created by IntelliJ IDEA.
  User: LYZ
  Date: 2020/7/9
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>账户登录-ImgShow</title>
    <link rel="icon" href="image/logo.svg">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Raleway:400,600" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+SC:wght@400;500;700&display=swap" rel="stylesheet">
    <link href="css/base.css" rel="stylesheet" type="text/css">
    <link href="css/input_form.css" rel="stylesheet" type="text/css">
    <script src="js/prefixfree.min.js" rel="script" type="text/javascript"></script>
</head>

<body>
<main>
    <div class="form_part">
        <form action="./login" method="post">
            <input class="input_box" name="username" type="text" placeholder="用户名" pattern="^[a-zA-Z0-9_-]{4,16}$"
                   required
                <%

                    if (request.getParameter("username") != null){
                        out.print("value='"+request.getParameter("username")+"'");
                    }
                %>
            >
            <input class="input_box" name="password" type="password" placeholder="密码" pattern="^[0-9A-Za-z]{8,16}$"
                   required
                <%
                    if (request.getParameter("password") != null){
                        out.print("value='"+request.getParameter("password")+"'");
                    }
                %>
            >

            <%
                if (request.getParameter("username") != null) {
                    out.println("<p style='color: red'>您输入的用户名或密码错误</p>");
                }
            %>

            <input type="submit" value="登录">
        </form>
        <p>创建一个账号? <a href="register.jsp"> 立即注册!</a></p>
    </div>
</main>

<footer>
    <!--    页脚横幅-->
    <div class="footer__nav">
        <ul>
            <li>
                <a class="footer__navbar__logo" href="${pageContext.request.contextPath}/index.jsp">
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
<ul class="square login">
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
</ul>
</body>
</html>
