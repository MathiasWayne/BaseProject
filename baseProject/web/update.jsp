<%--
  Created by IntelliJ IDEA.
  User: AloysMack
  Date: 2018/9/7
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
  <%--<base href="<%=basePath%>"/>--%>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改用户</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改联系人</h3>
    <form action="${pageContext.request.contextPath}/base?method=update" method="post">
        <<input type="hidden" name="id" value="${userById.id}">
        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name"  name="name" readonly="readonly" value="${userById.name}"/>
        </div>

        <div class="form-group">
            <label>性别：</label>
            <input type="radio" name="gender" value="男"   ${userById.gender=='男'?'checked':''}/> 男
            <input type="radio" name="gender" value="女"   ${userById.gender=='女'?'checked':''}/> 女
        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age"  name="age" value="${userById.age}" />
        </div>

        <div class="form-group">
            <label >籍贯：</label>
            <select name="address" class="form-control" >
                <option value="广东" ${userById.address=="广东"?true:''}>广东</option>
                <option value="广西" ${userById.address=="广西"?true:''}>广西</option>
                <option value="湖南" ${userById.address=="湖南"?true:''}>湖南</option>
            </select>
        </div>

        <div class="form-group">
            <label >QQ：</label>
            <input type="text" class="form-control" name="qq" value="${userById.qq}"/>
        </div>

        <div class="form-group">
            <label >Email：</label>
            <input type="text" class="form-control" name="email" value="${userById.email}"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回"/>
        </div>
    </form>
</div>
</body>
</html>