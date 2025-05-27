<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>プロフィール</title>
  <link rel="stylesheet" href="${ctx}/css/style.css">
  <link rel="stylesheet" href="${ctx}/css/profile.css">
</head>
<body>
  <div class="resume-container">
    <form action="${ctx}/Profile" method="post">
      <div class="resume-card">
        <div class="resume-header">
          <!-- 写真アップロードエリア -->
          <div class="photo" id="drop-area">
            <input type="file" id="photoInput" accept="image/*" style="display:none;" />
            <img id="photoPreview"
                 src="<c:choose>
                        <c:when test='${not empty account.photoBase64}'>
                          ${account.photoBase64}
                        </c:when>
                        <c:otherwise>
                          ${ctx}/images/placeholder.png
                        </c:otherwise>
                      </c:choose>"
                 alt="プロフィール写真" />
            <p id="drop-text">ここに画像をドラッグ</p>
          </div>
          <!-- 基本情報（右側） -->
          <div class="profile-info">
            <p class="right-align"><strong>氏名：</strong>
              <input type="text" name="name" value="${account.name}" />
            </p>
            <p class="right-align"><strong>住所：</strong>
              <input type="text" name="address" value="${account.address}" />
            </p>
            <p class="right-align"><strong>連絡先：</strong>
              <input type="text" name="contact" value="${account.contact}" />
            </p>
            <p class="right-align"><strong>生年月日：</strong>
              <input type="date" name="birth" value="${account.birth}" />
            </p>
          </div>
        </div>
        <table class="resume">
          <tr>
            <th>学歴</th>
            <td><textarea name="education" rows="5">${account.education}</textarea></td>
          </tr>
          <tr>
            <th>職歴</th>
            <td><textarea name="workHistory" rows="5">${account.workHistory}</textarea></td>
          </tr>
          <tr>
            <th>希望職種</th>
            <td><textarea name="targetJob" rows="5">${account.targetJob}</textarea></td>
          </tr>
          <tr>
            <th>資格・免許</th>
            <td><textarea name="certifications" rows="5">${account.certifications}</textarea></td>
          </tr>
          <tr>
            <th>自己PR</th>
            <td><textarea name="selfPR" rows="5">${account.selfPR}</textarea></td>
          </tr>
          <tr>
            <th>趣味・特技</th>
            <td><textarea name="hobbies" rows="5">${account.hobbies}</textarea></td>
          </tr>
          <tr>
            <th>障がい情報</th>
            <td><textarea name="disability" rows="5">${account.disability}</textarea></td>
          </tr>
          <tr>
            <th>医療情報</th>
            <td><textarea name="medical" rows="5">${account.medical}</textarea></td>
          </tr>
        </table>
        <input type="hidden" name="id" value="${account.id}" />
        <input type="hidden" name="photoBase64" id="photoBase64" />
        <p class="submit">
          <button type="submit">保存</button>
          <button type="button" onclick="location.href='${ctx}/LoginServlet'">一覧に戻る</button>
        </p>
      </div>
    </form>
  </div>
  <script src="${ctx}/js/dragdrop.js"></script>
</body>
</html>