<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/9/16
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
    errorPage="error.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Main</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet">
    <style>
        body{
            background: url("../wjy/main.jpg");
            background-size: 100%;
            background-repeat: none;
            font-size: 20px;
        }
        .container{
            display: flex;
            align-items: center;
            flex-direction: column;
        }
        .tittle{
            font-size: 36px;
            margin-top: 20px;
            margin-bottom: 20px;
            color: black;
        }
        .content{
            width:100%;
            /*border: 3px solid red;*/

            border-radius: 8px;
        }
        .myrow{
            display: flex;
            height: 100%;
            align-items: center;
        }

        .item{
            height: 300px;
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            color: black;
            background-color: rgba(246,238,114,0.1);
            flex-direction: column;
        }
        .item:hover{
            cursor: pointer;
        }
        a{
            text-underline: none;
        }
        span{
            color: #CEF2EC;
        }
        span1{
            color: 	black;
        }
    </style>

</head>
<body>
<%
    String user = (String) request.getSession().getAttribute("user");
    session.setAttribute("user",user);
%>
    <div class="container">
    <div class="head">
        <div class="tittle">Welcome <span><%=user%></span></div>
    </div>
    <div class="content">
        <div class="myrow" style="margin-bottom: 3px;">
            <div class="item" style="margin-right: 3px">
                <a href="../recite.jsp" />
                <span1>
                    <p>Everyday</p>
                    <p>Recite</p>
                    <p>Words.</p>
                </span1>
                </a>
            </div>
            <div  class="item" style="margin-right: 3px">
                <a href="../guess.jsp" />
                <span1><p>Play</p>
                    <p>And</p>
                    <p>Guess.</p>
                </span1>
                </a>
            </div>
        </div>
        <div class="myrow" >
            <div  class="item" style="margin-right: 3px">
                <a href="../Servlet/Search" />
                <span1>
                    <p>Search</p>
                    <p>And</p>
                    <p>Statistics.</p>
                </span1>
                </a>
            </div>
            <div class="item" style="margin-right: 3px">
                <a href="../Servlet/Review" />
                <span1>
                    <p>Here!</p>
                    <p>Review</p>
                    <p>words.</p>
                </span1>
                </a>
            </div>
        </div>
    </div>
</div>

</body>
</html>