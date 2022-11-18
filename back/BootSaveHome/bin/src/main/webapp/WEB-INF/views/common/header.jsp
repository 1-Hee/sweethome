<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" ></c:set>
<div class="col-md-8 offset-md-3 d-flex justify-content-end m-auto mt-md-4">
		<c:if test="${empty userInfo}">
        <a href="#" class="mx-4">고객센터</a>
        <a href="${root}/member/login" class="">로그인/회원가입</a>        		
		</c:if>
		<c:if test="${not empty userInfo}">
        <a href="#" class="mx-4">고객센터</a>
        <a href="${root}/member/logout" class="">로그아웃</a>        		
		</c:if>
    </div>
    <div class="col-md-12 d-flex justify-content-center">
      <img src="${root}/assets/img/home.png" width="36px" height="36px" class="m-2"/>
      <a href="/" class="main-title m-2">SAVE HOME</a>
    </div>
    <header class="p-3 mb-3">
        <div class="container">
          <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
              <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>
            </a>
    
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
              <li>
                <div class="dropdown">
                <button class="btn" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                  전체
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                  <li><a class="dropdown-item" href="#">회사 소개</a></li>
                  <li><a class="dropdown-item" href="${root}/notice/list">공지 사항</a></li>
                  <li><a class="dropdown-item" href="#">자유게시판</a></li>
                  <li><a class="dropdown-item" href="#">부동산 채널</a></li>
                </ul>
              </div>
            </li>
              <li><a href="${root}/map/gomap" class="nav-link px-2 link-dark">부동산 매물 조회</a></li>
              <li><a href="#" class="nav-link px-2 link-dark">이달의 부동산 매물</a></li>
              <li><a href="#" class="nav-link px-2 link-dark">부동산 Stock</a></li>
            </ul>
    
            <c:if test="${not empty userInfo}">
            <div style="margin-right: 10px;">
	       		<input id="search-box" type="search" class="form-control"  onkeyup="searchWithKeyWord(this.value)" placeholder="주소를 입력해보세요!">
	       	</div>
            <div class="dropdown">
	             <span>홍길동님 안녕하세요</span>
	             <button class="btn" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
	               <img src="${root}/assets/img/user.png" width="36px" height="36px" alt="..." class="img-rounded">
	             </button>
	             <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
	               <li><a class="dropdown-item" href="${root}/member/info">마이페이지</a></li>
	               <li><a class="dropdown-item" href="#">찜목록</a></li>
	             </ul>              
            </div>
            </c:if>
    
          </div>
        </div>
      </header> 