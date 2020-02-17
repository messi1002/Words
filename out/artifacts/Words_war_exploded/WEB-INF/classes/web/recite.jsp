<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/9/26
  Time: 9:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
    errorPage="error.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Recite</title>
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
        span2{
            color: #CEF2EC;
        }
        span1{
            color: #CAACE2;
        }
    </style>
</head>
<body>
    <%
        String mean1 = (String) request.getAttribute("mean1");
        String mean2 = (String) request.getAttribute("mean2");
    %>
    <div class="container a">
        <div class="head">
            <div class="head-row">
                <div class="head-left"><p style="font-size: 36px"><span1>Everyday Recite new Words.</span1></p></div>
                <div class="head-right"><a href="../main.jsp"><span2>Primary Page</span2></a></div>
            </div>
            <br>
            <div class="head-row">
                <div class="head-left"><p class="in" style="font-size: 18px">Enter the words you recited in the input box.</p>
                    <p class="in" style="font-size: 18px">Clicking save will display translation results on the right side.</p></div>
                <div class="head-right"><a href="../review.jsp"><span2>Review Page</span2></a></div>
            </div>
        </div>
        <hr><br />
        <div class="form-horizontal">
            <form action="../Servlet/Recite" method="post">
                <div class="form-group">
                    <div class="col-sm-3">
                        <input type="text" class="form-control" name="word1" placeholder="please input word">
                    </div>
                    <div class="col-sm-1">
                        <button type="submit" class="btn btn-default">save</button>
                    </div>
                    <div  class="col-sm-7">
                        <div>
                            <%
                                if(mean1==null) {
                            %>
                            <span font-size=18px>Here is translation.</span>
                            <%
                                ;}
                            else{
                            %>
                            <span font-size=16px><%=mean1%></span>
                            <%
                                    ;}
                            %>
                        </div>
                    </div>
                </div>
            </form>
            <form action="../Servlet/Recite" method="post">
                <div class="form-group">
                    <div class="col-sm-3">
                        <input type="text" class="form-control" name="word2" placeholder="please input word">
                    </div>
                    <div class="col-sm-1">
                        <button type="submit" class="btn btn-default">save</button>
                    </div>
                    <div  class="col-sm-7">
                        <div>
                            <%
                                if(mean2==null){
                            %>
                            <span font-size=18px>Here is translation.</span>
                            <%
                                ;}
                            else{
                            %>
                            <span font-size=16px><%=mean2%></span>
                            <%
                                    ;}
                            %>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>
</html>