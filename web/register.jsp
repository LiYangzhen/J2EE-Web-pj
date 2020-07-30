<%--
  Created by IntelliJ IDEA.
  User: LYZ
  Date: 2020/7/9
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>账户注册-ImgShow</title>
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
    <div class="form_part register">
        <%
            request.setCharacterEncoding("UTF-8");
            String username = request.getParameter("username");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
        %>
        <form action="register" method="post">
            <label>用户名
                <input class="input_box" name="username" type="text" placeholder="不少于8位不高于16位的数字、字母、下划线"
                       pattern="^[a-zA-Z0-9_-]{4,16}$" required
                    <%
                    if (username !=null){
                        out.print("value='"+request.getParameter("username")+"'");
                    }
                %>
                >
                <%
                if (request.getAttribute("content")!=null){
                    out.println(request.getAttribute("content"));
                }
                %>
                <%
                    if ("".equals(username)) {
                        out.print("<p style='color:red;text-align:left;'>You must enter Username.</p>");
                    }
                %>
            </label>
            <label>名<input class="input_box" name="firstName" type="text" required
                <%
                    if (firstName!=null){
                        out.print("value='"+request.getParameter("firstName")+"'");
                    }
                %>
            >
                <% if ("".equals(firstName)) {
                    out.print("<p style='color:red;text-align:left;'>You must enter FirstName.</p>");
                }
                %>
            </label>
            <label>姓<input class="input_box" name="lastName" type="text" required
                <%
                    if (lastName!=null){
                        out.print("value='"+request.getParameter("lastName")+"'");
                    }
                %>
            >
                <%
                    if ("".equals(lastName)) {
                        out.print("<p style='color:red;text-align:left;'>You must enter LastName.</p>");
                    }
                %>
            </label>
            <label>邮箱<input class="input_box" name="email" type="email" placeholder="example: ImgShow@fudan.edu.cn"
                            pattern="^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$"
                            required
                <%
                    if (email!=null){
                        out.print("value='"+request.getParameter("email")+"'");
                    }
                %>
            >
                <%
                    if ("".equals(email)) {
                        out.print("<p style='color:red;text-align:left;'>You must enter Email.</p>");
                    }
                %>
            </label>
            <label>地址<input class="input_box" name="address" type="text" required
                <%
                    if (address!=null){
                        out.print("value='"+request.getParameter("address")+"'");
                    }
                %>
            >
                <% if ("".equals(address)) {
                    out.print("<p style='color:red;text-align:left;'>You must enter Address.</p>");
                }
                %>
            </label>
            <label>密码<input class="input_box" name="password" type="password" placeholder="不少于8位不高于16位的数字、字母"
                            pattern="^[0-9A-Za-z]{8,16}$" id="password1" required onkeyup="validate()">
            </label>
            <label>确认密码<input class="input_box" name="password2" type="password" placeholder="请保持两次输入密码的相同"
                              pattern="^[0-9A-Za-z]{8,16}$"
                              id="password2" required="required" onkeyup="validate()"><span id="check-result"></span>
            </label>

            <input type="submit" value="注册" id="submit">
            <p>已有ImgShow账号? <a href="login"> 立即登录!</a></p>
        </form>
    </div>
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
        <p> © 2020-现在 版权所有 <a href="http://www.beian.miit.gov.cn">陕ICP备20010010号-1</a>
            <a href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=61070202000390">沪公网备案61070202000390号</a>
        </p>
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
                    <p>简体中文</p></div>
            </li>
        </ul>
    </div>
</footer>
<ul class="square">
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
<script>
    function validate() {
        var pw1 = document.getElementById("password1").value;
        var pw2 = document.getElementById("password2").value;
        if (pw1 !== "") {
            if (pw1.toString().length < 8) {
                document.getElementById("check-result").innerHTML = "密码不足8位";
                document.getElementById("submit").disabled = true;
                document.getElementById("submit").style.cursor = "not-allowed";
                return false;
            }else if (pw2 === ""){
                document.getElementById("check-result").innerHTML = "";
                return false;
            }
            else if (pw1 === pw2) {
                document.getElementById("check-result").innerHTML = "";
                document.getElementById("submit").disabled = false;
                document.getElementById("submit").style.cursor = "pointer";
                return true;
            } else {
                document.getElementById("check-result").innerHTML = "两次密码不相同";
                document.getElementById("submit").disabled = true;
                document.getElementById("submit").style.cursor = "not-allowed";
                return false;
            }
        } else {
            document.getElementById("check-result").innerHTML = "请输入密码";
            document.getElementById("submit").disabled = true;
            document.getElementById("submit").style.cursor = "not-allowed";
            return false;
        }
    }
</script>
</body>
</html>
