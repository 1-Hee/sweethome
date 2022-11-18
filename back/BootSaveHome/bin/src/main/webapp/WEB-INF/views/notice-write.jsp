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
    <link rel="stylesheet" href="${root}/assets/css/write.css">
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

    <!-- 글쓰기 뷰 영역-->
    <div class="write-form-container">
        <form action="${root}/notice/write" method="post">
            <table class="write-table">
                <tr>
                    <th class="category">구분</th>
                     <td class="input-item">
                        <p>공지사항</p>
                    </td>
                </tr>
                <tr>
                    <th class="category">제목</th>
                    <td class="input-item">
                        <input type="text" class="title" name="title">
                    </td>
                </tr>
                <tr>
                    <th class="category">내용</th>
                    <td class="input-item content-item">
                        <input type="text" class="content" name="content">
                    </td>
                </tr>
                <tr>
                    <th class="category">파일첨부</th>
                    <td class="input-item">
                        <input type="file" multiple="multiple" class="file" name="file">
                    </td>
                </tr>
            </table>
            <button id="write-btn" class="write-btn btn btn-success form-control" type="button" onclick="submit()">글쓰기</button>
        </form>
    </div>
    
    <!-- 글쓰기 뷰 영역-->
    <!-- Footer 영역 -->
    <%@ include file="common/footer.jsp" %>
    <!-- Footer 영역 -->
    
    <script src="/assets/js/bootstrap.bundle.js"></script>
    <script src="/assets/js/write.js"></script>
</body>
</html>