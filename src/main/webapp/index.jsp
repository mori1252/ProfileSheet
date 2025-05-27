<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>プロフィールシート</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
  <div class="resume-container">
    <div class="header-box">
      <h1 class="page-title">プロフィールシート</h1>
      <nav class="main-nav">
        <ul class="nav-list">
          <li class="nav-item"><a href="${pageContext.request.contextPath}/LoginServlet">ログイン</a></li>
          <li class="nav-item"><a href="${pageContext.request.contextPath}/Register">ユーザー登録</a></li>
        </ul>
      </nav>
    </div>
  </div>
</body>
</html>
