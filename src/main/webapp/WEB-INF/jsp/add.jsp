<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: kgc
  Date: 2020/5/18
  Time: 下午4:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>添加学生</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/statics/res/bootstrap-3.4.1-dist/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/statics/res/bootstrap-3.4.1-dist/css/bootstrap-theme.min.css">
    <script src="${pageContext.request.contextPath}/statics/res/js/jquery-3.5.1.min.js"></script>
    <%--    <script src="${pageContext.request.contextPath}/statics/res/js/popper.min.js"></script>--%>
    <script src="${pageContext.request.contextPath}/statics/res/bootstrap-3.4.1-dist/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/res/js/studentlist.js"></script>
    <script type="text/javascript">
        function submitBtn() {
            window.console.debug("进入ajax！")
            var errorInfo = $("#sHead").next();
            $.ajax({
                type: "POST",
                //$("#myForm").serialize()
                data: new FormData($('form')[0]),
                contentType: false,
                processData: false,
                url: "${pageContext.request.contextPath}/student/add.html",
                dataType: "json",
                success: function (result) {
                    if (result == "上传成功") {
                        alert("添加成功");
                        window.location.href = "${pageContext.request.contextPath}/student/getStuList"
                    } else {
                        alert(result);
                    }

                }
            })
        }

    </script>
</head>
<body>

<c:import url="header.jsp" charEncoding="utf-8"/>

<div style="text-align: center;margin: 2% 25%">
    <form role="form" id="myForm" enctype="multipart/form-data"
          action="${pageContext.request.contextPath}/student/addsave" method="post">
        <div class="form-group">
            <div>
                <label for="sName">姓名</label>
                <input type="text" class="form-control" name="sName" id="sName"
                       placeholder="请输入姓名">
            </div>

            <div class="radio">
                <label for="optionsRadios1">性别</label>
                <label>
                    <input type="radio" name="sSex" id="optionsRadios1" value="男" checked> 男
                </label>
                <label>
                    <input type="radio" name="sSex" id="optionsRadios2" value="女">女
                </label>
            </div>
            <div>
                <label for="sid">学号</label>
                <input type="text" class="form-control" name="sid" id="sid"
                       placeholder="请输入学号">
            </div>
            <div>
                <label for="sAge">生日</label>
                <input type="text" class="form-control" name="sAge" id="sAge"
                       placeholder="请输入生日">
            </div>
            <div class="form-group">
                <input type="hidden" id="errorInfo" value="${requestScope.errorInfo}">
                <label for="sHeadPic">上传头像</label>
                <input type="file" name="sHeadPic" id="sHeadPic">
                <p style="color:#ff0000;text-align: left;margin-top: 5px">
                    <c:choose>
                        <c:when test="${requestScope.errorInfo != null}">${requestScope.errorInfo}</c:when>
                        <c:otherwise>*图片大小请保持在2M以下*图片格式必须是jpg,jpeg,png,pneg</c:otherwise>
                    </c:choose>
                </p>
            </div>
        </div>
        <%--        onclick="submitBtn()"--%>
        <button onclick="submitBtn()" type="button" class="btn btn-default">添加</button>
        <button type="reset" class="btn btn-default">重置</button>
    </form>
</div>


</body>
</html>