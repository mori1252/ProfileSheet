<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>プロフィールシート</title>
</head>
<body>

<!-- ここでエラーメッセージを表示 -->
<c:if test="${not empty error}">
    <p style="color: red;">${error}</p>
</c:if>

<form action="LoginServlet" method="post">
ユーザーID:<input type="text" name="id"><br>
パスワード:<input type="password" name="pass"><br>
<input type="submit" value="ログイン">
</form>
</body>
</html>