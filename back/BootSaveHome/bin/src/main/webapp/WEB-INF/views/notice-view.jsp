<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" ></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${root}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="${root}/assets/css/common.css">
    <link rel="stylesheet" href="${root}/assets/css/header.css">
    <link rel="stylesheet" href="${root}/assets/css/notice-view.css">
    <link rel="stylesheet" href="${root}/assets/css/footer.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <title>WHERE IS MY HOME</title>

</head>
<body>
    <!-- 헤더 영역 -->
    <%@ include file="common/header.jsp" %>
    <!-- 헤더 영역 -->
      <hr/>

       <!-- 공지사항 뷰 영역-->
      <div class="view-form-container">
        <div class="modify-div">
          <button type="button" id="modify-btn" class="modify btn btn-default" onclick="showModify()">글 수정</button>
          <button type="button" id="modify-cancel-btn" style="display: none;" class="modify btn btn-default" onclick="modifyCacel()">수정 취소</button>
          <!-- <a href="#" class="modify btn btn-default" onclick="showModify()">글 수정</a> -->
        </div>
        <div class="cancel-div">
          <a class="cancel btn btn-danger" onclick="deleteAlert(${article.articleNo})">글 삭제</a>
        </div>
       </div>
      <div id="view" class="view-container">
        <div class="title-section">
          <h3>공지사항</h3>
        </div>
        <hr>
        <div class="content-box">
          <div class="title">${article.title}</div>
          <div class="article-info-section">
            <span class="writer">${article.userId}</span>
            <span class="date">${article.registDate}</span>
            <span class="hit">${article.hit}</span>
          </div>
          <hr>
          <div class="content">
            ${article.content}
          </div>
        </div>
      </div>

      <form action="${root}/notice/modify" method="post">
        <div id="modify-view" style="display: none;" class="view-container">
          <div class="title-section">
            <h3>공지사항</h3>
          </div>
          <hr>
          <div class="content-box">
            <input type="hidden" value="${article.articleNo}" name="articleno">
            <div class="title"><input type="text" value="${article.title}" name="title"></div>
            <div class="article-info-section">
              <span class="writer">${article.userId}</span>
              <span class="date">${article.registDate}</span>
              <span class="hit">${article.hit}</span>
            </div>
            <hr>
            <div class="content">
              <input type="text" class="content-input" value="${article.content}" name="content">
            </div>
          </div>
          <div class="send-modify"><button class="btn btn-success form-control">수정하기</button></div>
        </div>
      </form>
       <!-- 공지사항 뷰 영역-->


      <!-- Footer 영역 -->
      <%@ include file="common/footer.jsp" %>
      <!-- Footer 영역 -->
      

      <script src="./assets/js/bootstrap.bundle.js"></script>
      <script src="/assets/js/view.js"></script>
</body>
</html>