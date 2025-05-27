<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>ユーザー新規登録</title>
  <link rel="stylesheet" href="${ctx}/css/style.css">
  <link rel="stylesheet" href="${ctx}/css/register.css">
  <style>
    .required { color: red; font-size: small; margin-left: 4px; }
  </style>
</head>
<body>
  <div class="register-container">
    <c:if test="${registerSuccess}">
      <p class="register-success">登録しました</p>
    </c:if>
   </div>
  <h1>ユーザー新規登録</h1>
  <form action="${ctx}/Register" method="post">
    <table>
      <!-- ID 削除: 自動採番 -->
      <!-- パスワード削除 -->
      <tr>
  <th>氏名</th>
  <td>
    <span class="required">＊必須</span>
    <input type="text" name="name" required />
  </td>
</tr>
<tr>
  <th>住所</th>
  <td>
    <span class="required">＊必須</span>
    <textarea name="address" rows="2" required></textarea>
  </td>
</tr>
<tr>
  <th>生年月日</th>
  <td>
    <span class="required">＊必須</span>
    <input type="date" name="birth" required />
  </td>
</tr>
<tr>
  <th>連絡先</th>
  <td>
    <span class="required">＊必須</span>
    <input type="text" name="contact" required />
  </td>
</tr>
        <th>学歴</th>
        <td><textarea name="education" rows="3"></textarea></td>
      </tr>
      <tr>
        <th>職歴</th>
        <td><textarea name="workHistory" rows="3"></textarea></td>
      </tr>
      <tr>
        <th>希望職種</th>
        <td><textarea name="targetJob" rows="2"></textarea></td>
      </tr>
      <tr>
        <th>資格・免許</th>
        <td><textarea name="certifications" rows="2"></textarea></td>
      </tr>
      <tr>
        <th>自己PR</th>
        <td><textarea name="selfPR" rows="3"></textarea></td>
      </tr>
      <tr>
        <th>趣味・特技</th>
        <td><textarea name="hobbies" rows="2"></textarea></td>
      </tr>
      <tr>
        <th>障がい情報</th>
        <td><textarea name="disability" rows="2"></textarea></td>
      </tr>
      <tr>
        <th>医療情報</th>
        <td><textarea name="medical" rows="2"></textarea></td>
      </tr>
    </table>
    <p class="submit">
      <button type="submit">登録</button>
      <button type="button" onclick="location.href='${ctx}/WelcomeServlet'">戻る</button>
    </p>
  </form>
</body>
</html>