<%--
  Created by IntelliJ IDEA.
  User: teleport
  Date: 2020/8/30
  Time: 6:41 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

  <form action="/upload" enctype="multipart/form-data" method="post">
    <input type="file" name="file"/>
    <input type="submit" value="upload">
  </form>
  <br>
  <a href="/download">点击下载</a>
  </body>
</html>
