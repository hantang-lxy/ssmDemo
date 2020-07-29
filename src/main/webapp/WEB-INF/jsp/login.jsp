<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>登录</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/statics/res/bootstrap-3.4.1-dist/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/statics/res/bootstrap-3.4.1-dist/css/bootstrap-theme.min.css">
    <script src="${pageContext.request.contextPath}/statics/res/js/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/res/bootstrap-3.4.1-dist/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-inverse" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a style="text-align: center" href="${pageContext.request.contextPath}/login"
               style="overflow:visible;">
                <img
                        src="//assets.kgc.cn/upload/file/20190416/1555375994280734.png" style="width:90px;height:54px;"
                        alt="课工场"> </a>
        </div>
    </div>
</nav>

<div style="text-align: center;margin: 2% 25%">
    <form class="form-horizontal" role="form" method="post">
        <div class="form-group">
            <label for="sid" class="col-sm-2 control-label">学号</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="sid" id="sid"
                       placeholder="请输入学号">
            </div>
        </div>
        <div class="form-group">
            <label for="sPwd" class="col-sm-2 control-label">密码</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" name="sPwd" id="sPwd"
                       placeholder="请输入密码">
            </div>
        </div>
        <span style="color: red">${requestScope.msg}</span>
        <br>
        <br>
        <div style="text-align: left" class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">登录</button>
                <button type="reset" class="btn btn-default">重置</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
