<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>プロフィール</title>
  <!-- CSS はプロジェクト直下の css フォルダ内 -->
  <link rel="stylesheet" href="${ctx}/css/styles.css">
</head>
<body>
  <div class="resume-container">
    <!-- ヘッダー：写真と氏名 -->
    <div class="resume-header">
      <div class="photo">
        <img
          src="<c:choose>
                 <c:when test="${not empty account.photoUrl}">
                   ${account.photoUrl}
                 </c:when>
                 <c:otherwise>
                   ${ctx}/images/placeholder.png
                 </c:otherwise>
               </c:choose>"
          alt="プロフィール写真" />
      </div>
      <div class="name-block">
        <h1><c:out value="${account.name}" /></h1>
      </div>
    </div>

    <!-- プロフィール編集フォーム -->
    <form action="${ctx}/Profile" method="post" enctype="multipart/form-data">
      <input type="hidden" name="id" value="${account.id}" />

      <table class="resume">
        <tr>
          <th>生年月日</th>
          <td><input type="date" name="birth" value="${account.birth}" /></td>
        </tr>
        <tr>
          <th>住所</th>
          <td><input type="text" name="address" value="${account.address}" /></td>
        </tr>
        <tr>
          <th>連絡先</th>
          <td><input type="text" name="contact" value="${account.contact}" /></td>
        </tr>
        <tr>
          <th>障がい情報</th>
          <td><input type="text" name="disability" value="${account.disability}" /></td>
        </tr>
        <tr>
          <th>医療情報</th>
          <td><input type="text" name="medical" value="${account.medical}" /></td>
        </tr>
        <tr>
          <th>スキル・自己紹介</th>
          <td><textarea name="skill" rows="5">${account.skill}</textarea></td>
        </tr>
        <tr>
          <th>希望職種</th>
          <td><input type="text" name="targetJob" value="${account.targetJob}" /></td>
        </tr>
      </table>

      <p class="submit">
        <button type="submit">保存</button>
      </p>
    </form>
  </div>
</body>
</html>
