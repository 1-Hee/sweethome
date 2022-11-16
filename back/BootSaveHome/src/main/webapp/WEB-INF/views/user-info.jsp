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
    <link rel="stylesheet" href="${root}/assets/css/user-info.css">
    <link rel="stylesheet" href="${root}/assets/css/footer.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <title>WHERE IS MY HOME</title>

</head>
<body>
    <!-- 헤더 영역 -->
    <%@ include file="common/header.jsp" %>
    <!-- 헤더 영역 -->
      <!-- 사용자 정보 영역 -->
      <hr>
      <div class="user-info-conatiner">
        <div class="user-info-head">
            <h3>프로필 수정</h3>
            <p>마이홈 대표 프로필과 별명을 수정할 수 있습니다.</p>
        </div>
        <hr>
        <div class="user-info-box">
            <ul>                
                <li>
                    <div class="sort">프로필</div>
                    <div>
                        <img src="${root}/assets/img/user.png">
                        <label class="upload-file btn btn-custom" for="file"> 사진 업로드
                            <input id="file" type="file"/>
                        </label>
                    </div>
                </li>
                <li>
                    <div class="sort">사용자 정보</div>
                    <div class="user-info">
                        <div>ID : <span>${member.id}</span></div>
                        <div>이름 : <span>${member.name}</span></div>                              
                        <input type="hidden"/>
                        <div class="password">
                            <form action="${root}/member/update" method="post">
                                <label for="password">비밀번호 변경 :                                     
                                    <input type="password" class="form-input" name="password"/>                                    
                                </label>
                                <button class="btn btn-custom" onclick="submit()">변경하기</button>
                            </form>
                        </div>
                        <div class="email">
                            <form action="${root}/member/update" method="post">
                                <label for="email">이메일 변경 : 
                                    <input id="email" type="email" class="form-input" name="fullEmail"/>                                    
                                </label>
                                <button class="btn btn-custom" onclick="submit()">수정하기</button>
                            </form>
                        </div>                        
                    </div>
                </li>
            </ul>
        </div>
      </div>

        <!-- Footer 영역 -->
        <%@ include file="common/footer.jsp" %>
      <!-- Footer 영역 -->
      

      <script src="./assets/js/bootstrap.bundle.js"></script>
</body>
</html>