<%@ page contentType="text/html;charset=UTF-8" language="java"
    errorPage="error.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Review</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet">
    <style>
        body{
            background: url("../wjy/main.jpg");
            font-size: 20px;
        }
        .head-row{
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .a{
            background-color: rgba(107,43,176,.1);
        }
        .head{
            margin-top: 10px;
            padding-left: 30px;
        }
        .form-group{
            padding-bottom: 20px;
            padding-left: 150px;
        }
        .in{ text-indent:2em;}
        span{
            color: #CEF2EC;
        }
        span1{
            color: #CAACE2;
        }
        span2{
            color: black;
        }
    </style>
</head>
<body>
<%
    String[] s = (String[]) request.getAttribute("buffer");
    String user = (String) session.getAttribute("user");
%>
<div class="container a">
    <div class="head">
        <div class="head-row">
            <div class="head-left"><p style="font-size: 36px"><span1>Here!&nbsp;<%=user%></span1></p></div>
            <div class="head-right"><a href="../main.jsp" ><span>Primary Page</span></a></div>
        </div>
        <div class="head-row">
            <div class="head-left"><p class="in" style="font-size: 18px">This page,you can review your words.</p><p class="in" style="font-size: 18px">You know,gain new knowledge by reviewing old.</p></div>
            <div class="head-right"><a href="../recite.jsp"><span>Recite Page</span></a></div>
        </div>
    </div>
    <hr><br />
    <form class="form-horizontal" role="form">
        <div class="form-group">
            <div class="col-sm-3">
                <span2>word</span2>
            </div>
            <div class="col-sm-5">
                <span2>mean</span2>
            </div>
            <div class="col-sm-3">
                <span2>date&nbsp;</span2>
            </div>
        </div>
        <div class="form-group">
            <%
                for (int i = 0; i < s.length; i = i+3) {

            %>
            <div class="col-sm-3">
                <span2><%=s[i]%></span2>
            </div>
            <div class="col-sm-5">
                <span2><%=s[i+1]%></span2>
            </div>
            <div class="col-sm-3">
                <span2><%=s[i+2]%></span2>
            </div>
            <%
                }
            %>
        </div>
    </form>
</div>
</body>
</html>
