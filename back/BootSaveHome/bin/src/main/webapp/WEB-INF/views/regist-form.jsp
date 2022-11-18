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
    <link rel="stylesheet" href="${root}/assets/css/regist-form.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <title>WHERE IS MY HOME</title>

</head>
<body>    
    <div class="regist-container">
        <p class="regist-title">회원가입</p>
        <hr class="regist-hr"/>
        <div class="regist-img-container"><a href="/"><img src="${root}/assets/img/home.png" width="72px" height="72px"></a></div>
        <form class="regist-form form-inline" action="${root}/member/signup" method="post">
            <div class="name form-group"><input type="text" class="name-input form-control" name="name" placeholder="이름"></div>
            <div class="id"><input type="text" class="id-input form-control" name="id" placeholder="아이디"></div>
            <div class="password"><input type="password" class="password-input form-control" name="password" placeholder="비밀번호"></div>
            <div class="password"><input type="password" class="password-input form-control" name="re-password" placeholder="비밀번호확인"></div>
            <div class="age"><input type="number" class="age-input form-control" name="age" placeholder="나이를 입력하세요..."></div>           
            <div class="email-group d-flex flex-row justify-content-center">
                <div class="email-box"><input type="text" class="email-input form-control" name="email" placeholder="이메일"></div>
                <span class="snail-mark">@</span>
                <div class="domain-box"><input type="text" class="domain-input form-control" name="domain" placeholder="도메인"></div>
            </div>
            <div class="button-div"><input type="button" onclick="submit()" class="btn btn-success form-control" value="회원가입"></div>
        </form>
    </div>
</body>
</html>