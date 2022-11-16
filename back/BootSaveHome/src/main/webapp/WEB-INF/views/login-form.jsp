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
    <link rel="stylesheet" href="${root}/assets/css/login-form.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <title>WHERE IS MY HOME</title>

</head>
<body>
    <div class="login-container">
        <c:if test="${not empty msg}">
        	<script>alert(`${msg}`);</script>
        </c:if>
        <p class="login-title">로그인</p>
        <hr class="login-hr"/>
        <div class="login-img-container"><a href="${root}/"><img src="${root}/assets/img/home.png" width="72px" height="72px"></a></div>
        <form class="id-form" action="${root}/member/login" method="post">
            <div class="id"><input type="text" class="id-input form-control" name="id" placeholder="아이디"></div>
            <div class="password"><input type="password" class="password-input form-control" name="password" placeholder="비밀번호"></div>
            <div class="button-div"><input type="button" onclick="submit()" class="btn btn-success form-control" value="로그인"></div>
        </form>
        <form class="mv-regist-form">
            <div class="mv-regist-container d-flex justify-content-center">
                <a href="${root}/member/signup" class="item">회원가입</a>
                <a href="${root}/member/find" class="item">비밀번호 초기화</a>
            </div>
        </form>
    </div>
      
</body>
</html>