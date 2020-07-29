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
    <title>学生管理系统</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/statics/res/bootstrap-3.4.1-dist/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/statics/res/bootstrap-3.4.1-dist/css/bootstrap-theme.min.css">
    <script src="${pageContext.request.contextPath}/statics/res/js/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/statics/res/bootstrap-3.4.1-dist/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        function query() {
            var name = $.trim($("#name").val());
            window.location.href = "${pageContext.request.contextPath}/student/getStuList?name=" + name;
        }

        function turnPage(pageNo) {
            var name = $.trim($("#name").val());
            window.location.href = "${pageContext.request.contextPath}/student/getStuList?name=" + name + "&pageNo=" + pageNo;
        }

        function updateData(sid) {
            window.location.href = "${pageContext.request.contextPath}/student/getStuBySid/" + sid;
        }

        function deleteData(sid) {
            window.location.href = "${pageContext.request.contextPath}/student/deleteStuBySid/" + sid;
        }

        function deleteData(sid) {
            $.ajax({
                type: "GET",
                async: false,
                url: "${pageContext.request.contextPath}/student/deleteStuBySid/" + sid,
                success: function (result) {
                    if (result == "1") {
                        alert("删除成功");
                        window.location.href = "${pageContext.request.contextPath}/student/getStuList"
                    } else {
                        alert("删除失败");
                    }
                },
                error: function () {
                    alert("服务器出了小差错！")
                }
            })
        }

        /**
         * 导入用户信息
         */
        function importUser() {
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/student/importFile",
                data: new FormData($("#importForm")[0]),
                processData: false,
                contentType: false,
                dataType: "json",
                success: function (result) {
                    alert(result);
                    window.location.href = "${pageContext.request.contextPath}/student/getStuList"
                },
                error: function (e) {
                    alert("导入失败！");
                }
            })
        }

    </script>
</head>
<body>
<c:import url="header.jsp" charEncoding="utf-8"/>
<div style="text-align: center">
    <div class="col-lg-6" style="text-align: center">
        <div class="input-group" style="text-align: center">
            <input id="name" type="text" class="form-control" value="${name}" placeholder="请输入要查询的学生姓名">
            <span class="input-group-btn">
						<button onclick="query()" class="btn btn-default" type="button">
							查询
						</button>
						<button onclick="exportToExcel()" class="btn btn-default" type="button">
							导出
						</button>
            </span>
        </div>
    </div>

    <br>
    <br>
    <table class="table  table-bordered table-hover" style="text-align: center">
        <thead>
        <tr>
            <th style="text-align: center">学号</th>
            <th style="text-align: center">姓名</th>
            <th style="text-align: center">性别</th>
            <th style="text-align: center">生日</th>
            <th style="text-align: left">修改/删除</th>


        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.datas}" var="student" varStatus="status">
            <tr>
                <td>${student.sid}</td>
                <td>${student.SName}</td>
                <td>${student.SSex}</td>
                <td>
                    <fmt:formatDate value="${student.SAge}" pattern="yyyy年MM月dd日"/>
                </td>
                <td style="text-align: left;border: none">
                    <div class="btn-group">
                        <button onclick="updateData(${student.sid})" type="button" class="btn btn-default">修改</button>
                        <button onclick="deleteData(${student.sid})" type="button" class="btn btn-default">删除</button>
                    </div>
                </td>

            </tr>

        </c:forEach>
        </tbody>
    </table>


    <ul class="pagination">
        <%--            当前页为第一页时，首页和上一页不能点击。下一页和末页同理--%>
        <c:choose>
            <c:when test="${page.pageNo == 1 or page.pageNo == 0}">
                <li class="disabled"><a href="#">首页</a></li>
                <li class="disabled"><a href="#">上一页</a></li>
            </c:when>

            <c:otherwise>
                <li><a href="javascript:turnPage(1)">首页</a>
                </li>
                <li>
                    <a href="javascript:turnPage(${page.pageNo-1})">上一页</a>
                </li>
            </c:otherwise>
        </c:choose>
        <c:forEach items="${rainbow}" var="rainbow" varStatus="status">
            <c:if test="${page.pageNo == rainbow}">
                <li class="active"><a
                        href="javascript:turnPage(${page.pageNo})">${rainbow}</a>
                </li>
            </c:if>
            <c:if test="${page.pageNo != rainbow}">
                <li>
                    <a href="javascript:turnPage(${rainbow})">${rainbow}</a>
                </li>
            </c:if>
        </c:forEach>
        <c:choose>
            <c:when test="${page.totalPages == 0}">
                <li class="disabled"><a href="#">下一页</a></li>
                <li class="disabled"><a href="#">末页</a>
                </li>
            </c:when>
            <c:when test="${page.pageNo == page.totalPages}">
                <li class="disabled"><a href="#">下一页</a></li>
                <li class="disabled"><a href="#">末页</a>
                </li>
            </c:when>

            <c:otherwise>
                <li>
                    <a href="javascript:turnPage(${page.pageNo + 1})">下一页</a>
                </li>
                <li>
                    <a href="javascript:turnPage(${page.totalPages})">末页</a>
                </li>
            </c:otherwise>
        </c:choose>
        <li class="disabled"><a href="#">第${page.pageNo}页/共${page.totalPages}页</a>
        </li>
        <li class="disabled"><a href="#">共${page.totalRows}条记录</a>
        </li>
    </ul>
</div>
<%--导入--%>
<div>
    <form id="importForm" enctype="multipart/form-data">
        <div><input type="file" name="importFile" id="importFile"/>
            <button type="button" onclick="importUser()">用户信息导入</button>
        </div>
    </form>
</div>
<script type="text/javascript">
    function exportToExcel() {
        window.location.href = "${pageContext.request.contextPath}/student/export/" + $("#name").val();
    }
</script>
</body>
</html>