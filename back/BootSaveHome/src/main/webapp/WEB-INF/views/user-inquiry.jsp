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
    <link rel="stylesheet" href="${root}/assets/css/user-inquiry.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <title>WHERE IS MY HOME</title>

</head>
<body>    
    <div class="user-inquiry-container">
        <p class="user-inquiry-title">회원정보 찾기</p>
        <p>회원 정보를 잊으셨나요?</p>
        <hr class="user-inquiry-hr"/>
        <p>회원 가입시 등록한 아이디를 입력해주세요.</p>
        <form action="${root}/member/find" method="post">
            <div class="email-conatiner">
                <input type="text" class="email-input form-control" name="id" placeholder="아아디를 입력하세요">
            </div>
            <div class="button-conatiner">
                <button onclick="submit()" class="btn btn-success form-control">이메일로 회원 정보 수신</button>            
            </div>
        </form>
        <div class="home-container">
            <a href="/" class="home" style="background-image:url(${root}/assets/img/home.png) ;"></a>
        </div>
    </div>
      
</body>
</html>