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
    <title>修改</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/statics/res/bootstrap-3.4.1-dist/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/statics/res/bootstrap-3.4.1-dist/css/bootstrap-theme.min.css">
    <script src="${pageContext.request.contextPath}/statics/res/js/jquery-3.5.1.min.js"></script>
    <%--    <script src="${pageContext.request.contextPath}/statics/res/js/popper.min.js"></script>--%>
    <script src="${pageContext.request.contextPath}/statics/res/bootstrap-3.4.1-dist/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        function submitBtn() {
            window.console.debug("进入ajax！")
            $.ajax({
                type: "POST",
                data: $("#myForm").serialize(),
                url: "${pageContext.request.contextPath}/student/update",
                success: function (result) {
                    if (result == "1") {
                        alert("更新成功");
                        window.location.href = "${pageContext.request.contextPath}/student/getStuList"
                    } else {
                        alert("更新失败");
                    }
                },
                error: function () {
                    alert("服务器出了小差错！")
                }
            })
        }

        function updateData(sid) {
            window.location.href = "${pageContext.request.contextPath}/student/getStuBySid/" + sid;
        }
    </script>
</head>
<body>
<c:import url="header.jsp" charEncoding="utf-8"/>
<div style="text-align: center;margin: 2% 25%">
    <form role="form" id="myForm">
        <div class="form-group">
            <input type="hidden" class="form-control" name="sid" id="sid" value="${stu.sid}">
            <div>
                <label for="sName">姓名</label>
                <input type="text" class="form-control" name="sName" id="sName"
                       value="${stu.SName}" placeholder="请输入姓名">
            </div>

            <div class="radio">
                <label for="optionsRadios1">性别</label>
                <label>
                    <input type="radio" name="sSex" id="optionsRadios1" value="男"
                           <c:if test="${stu.SSex == '男'}">checked</c:if> > 男
                </label>
                <label>
                    <input type="radio" name="sSex" id="optionsRadios2" value="女"
                           <c:if test="${stu.SSex == '女'}">checked</c:if> >女
                </label>
            </div>
            <div>
                <label for="sAge">生日</label>
                <input type="text" class="form-control" name="sAge" id="sAge"
                       value="<fmt:formatDate value="${stu.SAge}" pattern="yyyy-MM-dd"/>"
                       placeholder="请输入生日">
            </div>

        </div>
        <%--       form表单里面的 button的type为submit时，如果没有给action跳转路径，
        就会按照当前页面的url提交数据。如果该button上又加了ajax,就会出现重复提交数据。--%>
        <button onclick="submitBtn()" type="button" class="btn btn-default">提交</button>
        <button onclick="updateData(${stu.sid})" type="button" class="btn btn-default">重置</button>
    </form>
</div>
</body>
</html>