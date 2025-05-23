<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>プロフィールシート</title>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
  <h1>プロフィール</h1>
  <form action="UpdateProfileServlet" method="post">
    <table class="resume">
      <tr><th>名前</th><td><input type="text" name="name" value="${account.name}" required></td></tr>
      <tr><th>生年月日</th><td><input type="date" name="birth" value="${account.birth}"></td></tr>
      <tr><th>住所</th><td><input type="text" name="address" value="${account.address}"></td></tr>
      <tr><th>連絡先</th><td><input type="text" name="contact" value="${account.contact}"></td></tr>
      <tr><th>障がい種別</th><td><input type="text" name="disability" value="${account.disability}"></td></tr>
      <tr><th>医療情報</th><td><textarea name="medical">${account.medical}</textarea></td></tr>
      <tr><th>スキル</th><td><input type="text" name="skill" value="${account.skill}"></td></tr>
      <tr><th>希望職種</th><td><input type="text" name="targetJob" value="${account.targetJob}"></td></tr>
    </table>
    <input type="hidden" name="id" value="${account.id}" />
    <p style="text-align: center;"><input type="submit" value="保存"></p>
  </form>
  <p><a href="<c:url value='/UserList'/>">一覧に戻る</a></p>
</body>
</html>
