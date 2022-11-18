<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/common.css">
    <link rel="stylesheet" href="/assets/css/header.css">
    <link rel="stylesheet" href="/assets/css/footer.css">
    <link rel="stylesheet" href="/assets/css/map.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <title>WHERE IS MY HOME</title>
</head>
<body>
	<%@ include file="common/header.jsp" %>
    <!--맵 컨텐츠 영역-->
    <div class="search-box-container">
      <div class="select-box">
        <select id="sido" class="sido-select" onchange="getCode('gugun', this.value)">
          <option value="">시/도</option>
        </select>
        <select id="gugun" class="gugun-select" onchange="getCode('dong', this.value)">
          <option value="">시/군/구</option>
        </select>
        <select id="dong" class="dong-select" onchange="onChangeOption('pos', this.value)">
          <option value="">읍/면/동</option>
        </select>  
      </div>
    </div>
    <div class="map-container">
      <div class="items" id="map-items">
        <h3>아파트 거래정보</h3>
      </div>
      <div id="map"></div>
    </div>
    <!--맵 컨텐츠 영역-->         
    <%@ include file="common/footer.jsp" %>
    
    <!--<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1ce48e626c5d2596c0081cbd0e137cda"></script> -->
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1ce48e626c5d2596c0081cbd0e137cda&libraries=services"></script>
    <script src="/assets/js/map.js"></script>
    <script src="/assets/js/bootstrap.bundle.js"></script>
    <script>
    if(localStorage.getItem("keyword")!=="" && localStorage.getItem("keyword")!=null){
    	console.log(localStorage.getItem("keyword"));
	    moveByAddress(localStorage.getItem("keyword"));    	
    }
    
      function getCode(type = "sido", code = "") {
          console.log(`${pageContext.request.contextPath}`); // 
          if (type == "gugun") {
            document.getElementById("dong").options.length = 1;
          }
          fetch(`${pageContext.request.contextPath}/apt/box?type=\${type}&code=\${code}`)
            .then((response) => response.json())
            .then((items) => {
            	 let codeBox = document.getElementById(type);
                 codeBox.options.length = 1;
                 items.forEach(
                   (item) => (codeBox.options[codeBox.options.length] = new Option(item.name, item.code))
                 );
            });
        }
      getCode();
      
        function onChangeOption(type = "pos", dong) {
          getAptList(dong);
          fetch(`${pageContext.request.contextPath}/apt/box?type=\${type}&code=\${dong}`)
            .then((response) => response.json())
            .then((data) => {
              // data = new Option(data.LatLng);
               LAT = parseFloat(data[0].lat);
               LNG = parseFloat(data[0].lng);
               console.log(typeof LAT, "   ", LAT);
               console.log(typeof LNG, "   ", LNG);
            });
        }

        function getAptList(value) {
        fetch(`${pageContext.request.contextPath}/apt/apt-list?code=\${value}`)
          .then((response) => response.json())
          .then((items) => {
            console.dir(items);
            
            const container = document.getElementById("map-items");
            container.innerHTML = ``;
            let head = document.createElement("h3");
            head.innerText = "아파트 거래 정보";
            container.appendChild(head);
            
            items.forEach((item) => {
            	let infoUl = document.createElement("ul");
            	infoUl.setAttribute("class", "apt-info");
            	infoUl.dataset.lat = `\${item.lat}`;
            	infoUl.dataset.lng = `\${item.lng}`;
            	infoUl.setAttribute("onclick", "setCenter(this.dataset.lat, this.dataset.lng);");            	
            	
            	let name = document.createElement("li");
            	let textNode1 = document.createTextNode(`아파트 이름 : \${item.apartmentName}`);
            	name.appendChild(textNode1);            	
            	infoUl.appendChild(name);           	
            	            	
            	let price = document.createElement("li");
            	let textNode2 = document.createTextNode(`거래 금액 : \${item.dealAmount}`);
            	price.appendChild(textNode2);
            	infoUl.appendChild(price);           	
            	
            	
            	let area = document.createElement("li");
            	let textNode3 = document.createTextNode(`면적 : \${item.area}`);
            	area.appendChild(textNode3);
            	infoUl.appendChild(area);           	

            	let day = document.createElement("li");
            	let textNode4 = document.createTextNode(`거래 일시 : \${item.date}`);
            	day.appendChild(textNode4);
            	infoUl.appendChild(day);      
            	
            	container.appendChild(infoUl);            	
            });
            
          });
      }
    </script>    
</body>
</html>