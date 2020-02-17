<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/9/25
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         errorPage="error.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Login</title>
    <style type="text/css">
        body {
            <!-- 使用虚拟链路访问-- >
            font-size: 20 px;
            background-size: 100 %;
            background-repeat:no-repeat;
            background:url('../wjy/main.jpg');
        }
        .word{
            float:left;
            text-align:center;
            font-size: 36px;
            margin-left: 200px;
            margin-top: 150px;
        }
        .container {
            float: right;
            text-align:center;
            margin-right: 120px;
            margin-top: 80px;
        }
        .container input {
            width: 320px;
            display: block;
            height: 30px;
            line-height: 24px;
            margin: 25px auto;
            -webkit-transition: all 0s ease-in 0.1ms;
            -moz-transition: all 0s ease-in 0.1ms;
            transition: all 0s ease-in 0.1ms;
        }
        span{
            color: #CEF2EC;
        }
    </style>
</head>

<body>
    <div class="word">
        <p>Just for open and free.</p>
        <p>Begin to learn English efficiently</p>
        <p>Please keep on it</p>
    </div>
    <div class="container">
        <form name="regForm" action="../Servlet/Login" method="post">
            <label><span>Username</span></label>
            <input type="text" name="username" placeholder="please input username" /><br />
            <label><span>Password</span></label>
            <input type="password" name="password" placeholder="please input password" /><br />
            <input type="submit" value="Login" name="login" />
        </form>

    <p>
    <form action="register.jsp" method="post">
        <input type="submit" value="Register" name="register" />
    </form>
    </p>
    </div>
</body>
</html>
