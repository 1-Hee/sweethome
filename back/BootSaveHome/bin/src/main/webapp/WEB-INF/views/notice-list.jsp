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
    <link rel="stylesheet" href="${root}/assets/css/notice-list.css">
    <link rel="stylesheet" href="${root}/assets/css/footer.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <title>WHERE IS MY HOME</title>

</head>
<body>
<!-- 헤더 영역 -->
<%@ include file="common/header.jsp" %>
<!-- 헤더 영역 -->
      <!-- 공지사항 리스트 영역-->
      <hr/>
      <div class="table-container">
        <div class="list-header">
          <h2 class="list-title">공지사항</h2>
          <a href="${root}/notice/write" >
          <button type="button" id="write-btn" class="write-btn btn btn-write">글쓰기</button>
          </a>
        </div>
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>조회수</th>
                    <th>작성일</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="article" items="${list}">
                <tr>
                  <td>${article.articleNo}</td>
                  <td><a href="${root}/notice/view?no=${article.articleNo}">${article.title}</a></td>
                  <td>${article.userId}</td>
                  <td>${article.hit}</td>
                  <td>${article.registDate}</td>
                </tr>                                
            </c:forEach>
            </tbody>
        </table>
        <ul class="page-tab">
          <li><a href="#">&lt</a></li>
          <li class="select"><a href="#" onclick="giveSelect(this)">1</a></li>
          <li><a href="#" onclick="giveSelect(this)">2</a></li>
          <li><a href="#" onclick="giveSelect(this)">3</a></li>
          <li><a href="#" onclick="giveSelect(this)">4</a></li>
          <li><a href="#" onclick="giveSelect(this)">5</a></li>
          <li><a href="#">&gt</a></li>
        </ul>
      </div>
       <!-- 공지사항 리스트 영역-->


      <!-- Footer 영역 -->
      <%@ include file="common/footer.jsp" %>
      <!-- Footer 영역 -->
      

      <script src="/assets/js/bootstrap.bundle.js"></script>
      <script src="/assets/js/list.js"></script>
</html>