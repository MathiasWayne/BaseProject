<%--
  Created by IntelliJ IDEA.
  User: AloysMack
  Date: 2018/9/6
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
 管理员：${manager.name}<br>
  ${msg}<br>
  当你看到这的时候说明一切都结束了，没有后续了。。。<br>
  <font color="red">5</font>秒后跳转到员工信息
  <script type="text/javascript">
      window.setTimeout(function () {
          location.href="${pageContext.request.contextPath}/base?method=selectAll"
      },5000)
      this.clearTimeout();
  </script>

  <script type="text/javascript">
      var time=5;
      var timeFlag=function () {
          if (time > 0) {
              --time;
              document.getElementsByTagName("font")[0].innerHTML = time;
          }
      }
      window.setInterval(timeFlag,1000);

  </script>


  </body>
</html>
