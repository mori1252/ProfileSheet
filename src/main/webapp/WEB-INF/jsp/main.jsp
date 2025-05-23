<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>プロフィールシート</title>
</head>
<body>

<h2>利用者一覧</h2>

<!--<c:if test="${empty userList}">-->
<!--<p>利用者情報がありません。</p>-->
<!--</c:if>-->
<ul>
<c:forEach var="user" items="${userList}">
<li>
<a href="<c:url value='/Profile?id=${user.id}'/>">
<c:out value="${user.name}" />
</a>
</li>
</c:forEach>
</ul>

<p><a href="<c:url value='/LogoutServlet'/>">ログアウト</a></p>

</body>
</html>
