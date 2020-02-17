<%@ page contentType="text/html;charset=UTF-8" language="java"
    errorPage="error.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search</title>
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
            padding-left: 150px;
            padding-bottom: 10px;
        }
        p{
            padding-left: 150px;
            font-size: 20px;
        }
        .a1{
            text-indent:2em;
            padding-left: 50px;
            font-size: 18px;
        }
        .in{ text-indent:2em;}
        span{
            color: #CEF2EC;
        }
        span1{
            color: #CAACE2;
        }
        span2{
            color: rgba(234,118,59,0.8);
        }
        span3{
             padding-left:100px;
        }
        span4{
            padding-left:70px;
        }
    </style>
</head>
<body>
<%
    String number = (String) request.getAttribute("number");
    String primary = (String) request.getAttribute("primary");
    String middle = (String) request.getAttribute("middle");
    String advanced = (String) request.getAttribute("advanced");
    String day = (String)request.getAttribute("day");
    String result = (String) request.getAttribute("result");
%>
<div class="container a">
    <div class="head">
        <div class="head-row">
            <div class="head-left"><span1 style="font-size: 36px">Search and statistics</span1></div>
            <div class="head-right"><a href="../main.jsp" ><span>Primary Page</span></a></div>
        </div>
        <br>
        <br>
        <div class="head-row">
            <div class="head-left"><span4 class="in" style="font-size: 18px">Enter the words what you want search in the input box.<br/></span4>
                <span4 class="in" style="font-size: 18px">Clicking search will display the results.</span4></div>
            <div class="head-right"><a href="../review.jsp"><span>Review Page</span></a></div>
        </div>
    </div>
    <br />
    <form class="form-horizontal" role="form">
        <div class="form-group">
            <div class="col-sm-5">
                <input type="text" class="form-control" name="word" placeholder="please input word">
            </div>
            <div class="col-sm-2">
                <button type="submit" class="btn btn-default">search</button>
            </div>
        </div>

        <%
            if(result == null | result == "")
            {
        %>
        <p font-size :20px>Here are some statistics:</p>
        <br />
        <%
            ;}
            else {
        %>
        <p font-size :20px>The result:</p>
        <%
            ;}
        %>

        <div class="a1">
            <%
                if(result == null|result == ""){
            %>
                <div class="form-group">
                    <div  class="col-sm-4">
                        <div>Now you have recited&nbsp;</div>
                    </div>
                    <div class="col-sm-2">
                        <div><span2><%=day%></span2></div>
                    </div>
                    <div class="col-sm-4">
                        <div>&nbsp;days.</div>
                    </div>
                </div>
                <div class="form-group">
                    <div  class="col-sm-4">
                        <div>Now you have recited&nbsp;</div>
                    </div>
                    <div class="col-sm-2">
                        <div><span2><%=number%></span2></div>
                    </div>
                    <div class="col-sm-4">
                        <div>&nbsp;words.</div>
                    </div>
                </div>
                <div class="form-group">
                    <div  class="col-sm-4">
                       <div>The primary words have&nbsp;</div>
                    </div>
                    <div class="col-sm-2">
                        <div><span2><%=primary%></span2></div>
                    </div>
                    <div class="col-sm-4">
                        <div>&nbsp;numbers.</div>
                    </div>
                </div>
                <div class="form-group">
                    <div  class="col-sm-4">
                        <div>The middle words have&nbsp;</div>
                    </div>
                    <div class="col-sm-2">
                        <div><span2><%=middle%></span2></div>
                    </div>
                    <div class="col-sm-4">
                        <div>&nbsp;numbers.</div>
                    </div>
                </div>
                <div class="form-group">
                    <div  class="col-sm-4">
                        <div>The advanced words have&nbsp;</div>
                    </div>
                    <div class="col-sm-2">
                        <div><span2><%=advanced%></span2></div>
                    </div>
                    <div class="col-sm-4">
                        <div>&nbsp;numbers.</div>
                    </div>
                </div>
            <%
                ;}
                else{
            %>
            <span3 font-size:18px><%=result%></span3>
            <%
                ;}
            %>
        </div>
    </form>
</div>
</body>
</html>
