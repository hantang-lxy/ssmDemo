<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-inverse" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a style="text-align: center" href="${pageContext.request.contextPath}/student/getStuList"
               style="overflow:visible;">
                <img
                        src="//assets.kgc.cn/upload/file/20190416/1555375994280734.png" style="width:90px;height:54px;"
                        alt="课工场"> </a>
        </div>
        <div>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown ">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="glyphicon glyphicon-user">&nbsp;</span>${sessionScope.stuSession.SName}<b
                            class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">修改密码</a></li>
                        <li class="divider"></li>
                        <li><a href="#">查看个人信息</a></li>
                        <li class="divider"></li>
                        <li><a href="#">修改个人信息</a></li>
                        <li class="divider"></li>
                        <li><a href="${pageContext.request.contextPath}/student/add.html">添加学生</a></li>
                    </ul>
                </li>
                <li><a href="${pageContext.request.contextPath}/logout"><span
                        class="glyphicon glyphicon-log-out">&nbsp;</span>退出系统</a></li>
            </ul>

        </div>
    </div>
</nav>