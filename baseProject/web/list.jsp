<%--
  Created by IntelliJ IDEA.
  User: AloysMack
  Date: 2018/9/6
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
</head>
<body>
<form class="navbar-form navbar-left" role="search" method="post"
      action="${pageContext.request.contextPath}/base?method=query&currentPage=1">

    姓名：
    <div class="form-group">
        <input type="text" class="form-control" placeholder="姓名" name="name">
    </div>
    地址：
    <div class="form-group">
        <input type="text" class="form-control" placeholder="地址" name="address">
    </div>
    性别：
    <div class="form-group">
        <input type="text" class="form-control" placeholder="性别" name="gender">
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
    <input type="hidden" name="method" value="query"/>
</form>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>
    <table border="1" class="table table-bordered table-hover" id="table">
        <tr class="success">
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>籍贯</th>
            <th>QQ</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>

        <%-- <c:forEach items="${users}" var="list" varStatus="vs">
             <tr>
                 <td>${vs.count}</td>
                 <td id="name">${list.name}</td>
                 <td>${list.gender}</td>
                 <td>${list.age}</td>
                 <td>${list.address}</td>
                 <td>${list.qq}</td>
                 <td>${list.email}</td>
                 <td><a  name="update"  class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/base?method=selectById&id=${list.id}">修改</a>&nbsp;<a class="btn btn-default btn-sm"
                                                                              name="delete">删除</a></td>
             </tr>
         </c:forEach>--%>
        <c:forEach items="${list.beanList}" var="list" varStatus="vs">
            <tr>
                <td>${vs.count}</td>
                <td id="name">${list.name}</td>
                <td>${list.gender}</td>
                <td>${list.age}</td>
                <td>${list.address}</td>
                <td>${list.qq}</td>
                <td>${list.email}</td>
                <td><a name="update" class="btn btn-default btn-sm"
                       href="${pageContext.request.contextPath}/base?method=selectById&id=${list.id}">修改</a>&nbsp;<a
                        class="btn btn-default btn-sm"
                        name="delete">删除</a></td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="8" align="center"><a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">添加联系人</a>
            </td>
        </tr>
    </table>
    <nav>
        <h4>共有${list.totalPage}页，当前为第${list.currentPage}页</h4>
        <ul class="pagination">
            <li><a href="${pageContext.request.contextPath}/base?method=select&currentPage=1">首页</a></li>
            <li>
                <c:if test="${list.currentPage>1}">
                    <a href="${pageContext.request.contextPath}/base?method=select&currentPage=${list.currentPage-1}"
                       aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </c:if>
            </li>
            <c:choose>
                <c:when test="${list.totalPage <= 10}">
                    <c:set var="begin" value="1"></c:set>
                    <c:set var="end" value="${list.totalPage}"></c:set>
                </c:when>
                <c:otherwise>
                    <c:set var="begin" value="${list.currentPage-5}"></c:set>
                    <c:set var="end" value="${list.currentPage+4}"></c:set>
                    <c:if test="${begin < 1}">
                        <c:set var="begin" value="1"></c:set>
                        <c:set var="end" value="10"></c:set>
                    </c:if>
                    <c:if test="${end >list.totalPage}">
                        <c:set var="begin" value="${list.totalPage-9}"></c:set>
                        <c:set var="end" value="${list.totalPage}"></c:set>
                    </c:if>
                </c:otherwise>
            </c:choose>
            <c:forEach var="i" begin="${begin}" end="${end}">
                <c:if test="${list.currentPage==i}">
                    <li class="active"><a name="pageList"
                                          href="${pageContext.request.contextPath}/base?method=select&currentPage=${i}">${i}</a>
                    </li>
                </c:if>
                <c:if test="${list.currentPage!=i}">
                    <li><a name="pageList"
                           href="${pageContext.request.contextPath}/base?method=select&currentPage=${i}">${i}</a></li>
                </c:if>
            </c:forEach>
            <li>
                <c:if test="${list.currentPage<list.totalPage}">
                    <a href="${pageContext.request.contextPath}/base?method=select&currentPage=${list.currentPage+1}"
                       aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </c:if>
            </li>
            <li><a href="${pageContext.request.contextPath}/base?method=select&currentPage=${list.totalPage}">尾页</a>
            </li>
        </ul>

    </nav>
</div>

<script type="text/javascript">
    var elementsByName = document.getElementsByName("delete");
    for (var i = 0; i < elementsByName.length; i++) {
        elementsByName[i].onclick = function () {
            if (confirm("确定要删除此用户?")) {
                var name = this.parentNode.parentElement.firstElementChild.nextElementSibling.innerHTML;
                this.href = "${pageContext.request.contextPath}/base?method=delete&name=" + name;
            }
        }
    }
</script>


</body>
</html>
