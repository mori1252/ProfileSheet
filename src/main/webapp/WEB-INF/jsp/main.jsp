<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>利用者一覧</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
<h2>利用者一覧</h2>
<c:if test="${empty userList}">
  <p>利用者情報がありません。</p>
</c:if>
<c:if test="${not empty userList}">
<ul class="user-list">
  <c:forEach var="user" items="${userList}">
    <li>
      <a href="<c:url value='/Profile?id=${user.id}'/>">
        <c:out value="${user.name}" />
      </a>
      <form action="<c:url value='/DeleteUserServlet'/>" method="post" style="display:inline;">
        <input type="hidden" name="id" value="${user.id}">
        <button type="submit" class="delete-btn" onclick="return confirm('本当に削除しますか？');">削除</button>
      </form>
    </li>
  </c:forEach>
</ul>
</c:if>
<p><a href="<c:url value='/LogoutServlet'/>">ログアウト</a></p>
</body>
</html>
