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
    <link rel="stylesheet" href="${root}/assets/css/index.css">
    <link rel="stylesheet" href="${root}/assets/css/footer.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <title>WHERE IS MY HOME</title>

</head>
<body>
    <!-- 헤더 영역 -->
    <%@ include file="common/header.jsp" %>
      <!-- 헤더 영역 -->
      <!-- 메인 영역 -->
      <div class="banner"><div class="banner-img"></div></div>
      <div class="divider1"></div>

      <div class="main-search-box d-flex flex-column text-center">
        <p>부동산을 검색해보세요</p>
        <div><input id="search-box" type="search" class="form-control"  onkeyup="searchWithKeyWord(this.value)" placeholder="주소를 입력해보세요!"></div>
      </div>
      <div class="divider1"></div>

      <div class="main-thumbnail-title">
        <p><span class="m-span">MD</span>가 추천하는 인기 부동산</p>
      </div>
      
      <div class="divider1"></div>

      <div class="thumbnail-container row">
        <a href="#" class="thumbnail col-md-3">
          <img src="${root}/assets/img/apt1.jpg"/>
          <div class="text-caption">
              <h3>아이파크삼성 웨스트윙동</h3>
              <p>서울시 강남구 영동대로 640</p>
              <p><span class="badge r-badge">아파트</span> 6,600,000,000 WON</p>
          </div>          
        </a>  
        <a href="#" class="thumbnail col-md-3">
          <img src="${root}/assets/img/apt2.jpg"/>
          <div class="text-caption">
              <h3>갤러리아 포레 101동</h3>
              <p>서울시 성동구 서울숲2길 32-14</p>
              <p><span class="badge r-badge">아파트</span> 5,000,000,000 WON</p>
          </div>
        </a> 
        <a href="#" class="thumbnail col-md-3">
          <img src="${root}/assets/img/apt3.jpg"/>
          <div class="text-caption">
              <h3>한남더힐</h3>
              <p>서울시 용산구 독서당로 111</p>
              <p><span class="badge r-badge">아파트</span> 9,500,000,000 WON</p>
          </div>
        </a>  
        <a href="#" class="thumbnail col-md-3">
          <img src="${root}/assets/img/apt4.jpg"/>
          <div class="text-caption">
              <h3>래미안퍼스티지</h3>
              <p>서울시 서초구 반포대로 275</p>
              <p><span class="badge r-badge">아파트</span> 4,930,000,000 WON</p>
          </div>
        </a>  

      </div>
      <div class="divider1"></div>

      <div class="main-search-box d-flex flex-column text-center">
        <p><h1>태그로 부동산을 검색해보세요</h1></p>
      </div>
      <div class="tag-box row d-flex justify-content-center">
        <a class="col-md-3 badge tag-badge " href="#"><span>자주 찾는</span></a>
        <a class="col-md-3 badge tag-badge " href="#"><span>전망이 좋은</span></a>
        <a class="col-md-3 badge tag-badge " href="#"><span>조용한</span></a>
        <a class="col-md-3 badge tag-badge " href="#"><span>가격이 저렴한</span></a>
        <a class="col-md-3 badge tag-badge " href="#"><span>편의시설을 갖춘</span></a>
        <a class="col-md-3 badge tag-badge " href="#"><span>학군이 좋은</span></a>
        <a class="col-md-3 badge tag-badge " href="#"><span>백화점 인근</span></a>
        <a class="col-md-3 badge tag-badge " href="#"><span>휴양시설이 있는</span></a>
      </div>
      <div class="divider1"></div>


      <div class="thumbnail-container row offset-md-3">
        <a href="#" class="thumbnail col-md-3">
          <img src="${root}/assets/img/apt5.jpg"/>
          <div class="text-caption">
              <h3>유성CJ나인파크</h3>
              <p>대전시 유성구 봉명동 553-2</p>
              <p><span class="badge o-badge">아파트</span> 559,000,000 WON</p>
          </div>          
        </a>  
        <a href="#" class="thumbnail col-md-3">
          <img src="${root}/assets/img/apt6.jpeg"/>
          <div class="text-caption">
              <h3>마린시티자이</h3>
              <p>부산시 해운대구 마린시티1로 9</p>
              <p><span class="badge o-badge">아파트</span> 1,700,000,000 WON</p>
          </div>
        </a> 
        <a href="#" class="thumbnail col-md-3">
          <img src="${root}/assets/img/apt7.jpg"/>
          <div class="text-caption">
              <h3>농성SK뷰센트럴</h3>
              <p>광주시 서구 농성동 702</p>
              <p><span class="badge o-badge">아파트</span> 655,000,000 WON</p>
          </div>
        </a>  
      </div>


      <div class="divider3"></div>
      <div class="content-box">
        <div class="news d-flex flex-row justify-content-center">
          <div class="house-news col-md-3">
            <h2><a href="#">부동산 뉴스</a></h2>
            <hr>
            <ul>
              <li><a href="#">“서울 아파트값 38%가 거품… 극단적 주택규제로 풍선효과”</a>
                <span class="press">세계일보</span>
              </li>
              <li><a href="#">한국주택협회, 제14대 회장 윤영준 선출</a>
                <span class="press">뉴스토마토</span>
              </li>
              <li><a href="#">[서산소식] 공동주택 지하 주차장 침수 방지 실태 점검</a>
                <span class="press">연합뉴스</span>
              </li>
              <li><a href="#">LH 직원 주택구입대출액, 지난해 758% 급증</a>
                <span class="press">쿠키뉴스</span>
              </li>
              <li><a href="#">지방 3억 이하 보유 2주택자 종부세 완화</a>
                <span class="press">한겨레뉴스</span>
              </li>
            </ul>
          </div>
          <div class="boards col-md-3">
            <h2><a href="#">자유게시판</a></h2>
            <hr>
            <ul>
              <li><a href="#">주택 담보 대출 몇 퍼센트까지 해주나요.</a></li>
              <li><a href="#">금리 오르는데 LTV에 관해서 궁금합니다.</a></li>
              <li><a href="#">좋은 매물 찾는 꿀팁 있을까요.</a></li>
              <li><a href="#">자녀에게 아파트를 증여할 때 세금 계산은 어떻게 하죠?</a></li>
              <li><a href="#">국세청에서 아파트 시세가액에 따라 세금을 매기는 방법</a></li>
            </ul>
          </div>
        </div>
      </div>
      <div class="divider4"></div>
      <!-- 메인 영역 -->

      <!-- Footer 영역 -->
      <%@ include file="common/footer.jsp" %>
      <!-- Footer 영역 -->
      
      <script src="${root}/assets/js/bootstrap.bundle.js"></script>
</body>
</html>