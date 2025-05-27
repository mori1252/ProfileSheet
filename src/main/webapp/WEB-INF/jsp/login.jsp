<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
  String ctx = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>プロフィールシート</title>
<link rel="stylesheet" href="<%= ctx %>/css/style.css">
<link rel="stylesheet" href="<%= ctx %>/css/login.css">
<style>
  /* エラーメッセージの強調 */
  .error-message { color: #e74c3c; font-weight: bold; text-align: center; margin-bottom: 1.5rem; }
</style>
</head>
<body>
  <div class="login-container">
    <h1 class="page-title">管理者ログイン</h1>

    <!-- エラーメッセージ -->
    <c:if test="${not empty error}">
      <p class="error-message">${error}</p>
    </c:if>

    <form action="LoginServlet" method="post" class="login-form">
      <div class="form-row">
        <label for="id">管理者ID</label>
        <input type="text" name="id" id="id" required>
      </div>
      <div class="form-row">
        <label for="pass">パスワード</label>
        <input type="password" name="pass" id="pass" required>
      </div>
      <div class="form-actions">
        <input type="submit" value="ログイン" class="login-btn">
      </div>
    </form>
  </div>
</body>
</html>