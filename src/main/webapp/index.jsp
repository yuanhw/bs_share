<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>prompt</title>
</head>
<body>
<h1>服务成功启动！</h1>
<form action="http://192.168.43.43:8080/demo/user/uploadImg.do" method="post"
      enctype="multipart/form-data">
    <input type="text" name="phone">
    <input type="file" name="imgFile">
    <input type="submit" value="提交">
</form>
</body>
</html>
