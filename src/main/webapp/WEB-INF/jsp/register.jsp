<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>ユーザー登録</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/profile.css">
</head>
<body>
  <h1>新規ユーザー登録</h1>
  <form action="${pageContext.request.contextPath}/Register" method="post">
    <table>
      <tr>
        <th>ID（半角数字）</th>
        <td><input type="number" name="id" required /></td>
      </tr>
      <tr>
        <th>氏名</th>
        <td><input type="text" name="name" required /></td>
      </tr>
      <tr>
        <th>パスワード</th>
        <td><input type="password" name="pass" required /></td>
      </tr>
    </table>
    <p>
      <button type="submit">登録</button>
      <button type="button" onclick="location.href='LoginServlet'">キャンセル</button>
    </p>
  </form>
</body>
</html>
