<%--
  Created by IntelliJ IDEA.
  User: AloysMack
  Date: 2018/9/6
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>管理员登录</title>
     <%--表单校验需要的js和css文件--%>
    <link rel="stylesheet" href="css/screen.css">
    <script src="js/messages_zh.js"></script>
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <script type="text/javascript" src="js/jquery.validate.min.js"></script>
    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
    </script>
    <style type="text/css">
        #vcode{
            width:150px;
            height: 50px;
        }
    </style>
</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">管理员登录</h3>
    <form id="checked" action="${pageContext.request.contextPath}/loginServlet" method="post" >
        <div class="form-group">
            <label for="user">用户名：</label>
            <input type="text" name="user" class="form-control" id="user" value="${requestScope.name}" placeholder="请输入用户名"/>
        </div>

        <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" name="password" class="form-control" id="password" value="${requestScope.password}" placeholder="请输入密码"/>
        </div>

        <div class="form-inline">
            <label for="vcode">验证码：</label>
            <input type="text" name="verifycode"  class="form-control" id="verifycode" placeholder="请输入验证码" style="width: 120px;" required/>
          <img src="${pageContext.request.contextPath}/veriyCodeServlet" title="看不清点击刷新" id="vcode"/>
        </div>
        <hr/>
        <div class="form-group" style="text-align: center;">
            <input class="btn btn btn-primary" type="submit" value="登录" >
        </div>
    </form>

    <!-- 出错显示的信息框 -->
    <div class="alert alert-warning alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" >
            <span>&times;</span></button>
        <strong>${msg}</strong>
    </div>
</div>


<script type="text/javascript">
    document.getElementById("vcode").onclick=function () {
        document.getElementById("vcode").src="${pageContext.request.contextPath}/veriyCodeServlet?time="+new Date().getTime();
    }

</script>
<%--表单检验的js--%>
<script type="text/javascript">
    $(function () {
        $("#checked").validate({
            <!--定义校验规则-->
            rules:{
                //  需要将username修改为，标签中对应的名称
                user:{
                    required:true,
                    rangelength:[3,10]
                },
                password:{
                    required:true,
                    rangelength:[3,20]
                },
                verifycode:{
                    required:true,
                    rangelength:[0,4]
                }
            },
            <!--验证信息-->
            messages: {
                user: {
                    required: "用户名不能为空",
                    rangelength: "用户名的长度为2~10"
                },
                password: {
                    required: "密码不能为空",
                    rangelength: "密码的长度为3-10"
                },
                verifycode:{
                    required:"验证码不能为空",
                    rangelength:"验证码长度0-4"
                }
            }
        });
    });
</script>
</body>
</html>