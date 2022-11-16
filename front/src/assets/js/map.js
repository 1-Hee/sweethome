if(localStorage.getItem("keyword")!=="" && localStorage.getItem("keyword")!=null){
    console.log(localStorage.getItem("keyword"));
    moveByAddress(localStorage.getItem("keyword"));
    localStorage.removeItem("keyword");    	
}

/* 시군구 부분 함수 */
function getCode(type = "sido", code = "") {
    if (type == "gugun") {
    document.getElementById("dong").options.length = 1;
    }
    fetch(`http://localhost:8080/apt/box?type=${type}&code=${code}`)
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
    fetch(`http://localhost:8080/apt/box?type=${type}&code=${dong}`)
    .then((response) => response.json())
    .then((data) => {
    	setCenter(data[0].lat, data[0].lng);
    });
}

/* 셀렉트 박스 만들어지면 서버로부터 값을 가져와서 element를 만드는 메서드 */
function getAptList(value) {
    loadingImg();
    fetch(`http://localhost:8080/apt/apt-list?code=${value}`)
        .then((response) => response.json())
        .then((items) => {
            makeList(items);
    });
}

/* 카카오 맵 init 부분 */
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
mapOption = { 
    center: new kakao.maps.LatLng(37.54699, 127.09598), // 지도의 중심좌표
    level: 2 // 지도의 확대 레벨
};

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
var mapTypeControl = new kakao.maps.MapTypeControl();

// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
var zoomControl = new kakao.maps.ZoomControl();
map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);


function loadingImg(){
	const tempContainer = document.getElementById("map-items");
    tempContainer.innerHTML = ``;

    let tempInfoUl = document.createElement("ul");
 	tempInfoUl.setAttribute("class", "loading");
    let tempText = document.createElement("p");
	let tempTextNode = document.createTextNode("데이터를 불러오는 중입니다. 잠시만 기다려주세요...");
	tempText.appendChild(tempTextNode);            
	tempInfoUl.appendChild(tempText);              	
 	
 	let tempImg = document.createElement("img");    
	tempInfoUl.appendChild(tempImg);              	
	tempContainer.appendChild(tempInfoUl);   
}

/* 주소 값을 기준으로 이동 */
function moveByAddress(dongName) {
    // 주소-좌표 변환 객체를 생성합니다
    var geocoder = new kakao.maps.services.Geocoder();

    // 주소로 좌표를 검색합니다
    geocoder.addressSearch(dongName, function(result, status) {

        // 정상적으로 검색이 완료됐으면 
        if (status === kakao.maps.services.Status.OK) {            
            var arr = dongName.split(" ");
        	
            loadingImg();             

            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
            
            var imageSrc = '/assets/img/marker.png', // 마커이미지의 주소입니다    
            imageSize = new kakao.maps.Size(64, 64), // 마커이미지의 크기입니다
            imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

            // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
            var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
                markerPosition = new kakao.maps.LatLng(result[0].y, result[0].x); // 마커가 표시될 위치입니다

            // 마커를 생성합니다
            var marker = new kakao.maps.Marker({
            map : map,
            position: markerPosition,
            image: markerImage, // 마커이미지 설정 
            clickable: true
            });
            

            // 인포윈도우로 장소에 대한 설명을 표시합니다
            var infowindow = new kakao.maps.InfoWindow({
                content: '<div style="width:150px;text-align:center;padding:6px 0;">이 장소를 찾으셨나요?</div>'
            });
            infowindow.open(map, marker);

            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            map.setCenter(coords);
            
            // 검색 결과를 기준으로 매물을 초기화 합니다.
            fetch(`http://localhost:8080/apt/search?dong=${arr[0]}`)
                .then((response) => response.json())
                .then((data) => {        	
                    makeList(data);          
                });
            
        }
    });  
    
}

function makeList(data){
    const container = document.getElementById("map-items");
    container.innerHTML = ``;
    // let head = document.createElement("h2");
    // head.innerText = "아파트 거래 정보";
    // container.appendChild(head);    
    // container.setAttribute("ondblclick", "ScrollTop();"); 

    
    data.forEach((item) => {
        let infoUl = document.createElement("ul");
        infoUl.setAttribute("class", "apt-info");
        infoUl.dataset.lat = `${item.lat}`;
        infoUl.dataset.lng = `${item.lng}`;
        infoUl.setAttribute("onclick", "setCenter(this.dataset.lat, this.dataset.lng);");    
        
        let name = document.createElement("li");
        let textNode1 = document.createTextNode(`아파트 이름 : ${item.apartmentName}`);
        name.appendChild(textNode1);            	
        infoUl.appendChild(name);           	
                        
        let price = document.createElement("li");
        let textNode2 = document.createTextNode(`거래 금액 : ${item.dealAmount}`);
        price.appendChild(textNode2);
        infoUl.appendChild(price);           	
        
        
        let area = document.createElement("li");
        let textNode3 = document.createTextNode(`면적 : ${item.area}`);
        area.appendChild(textNode3);
        infoUl.appendChild(area);           	

        let day = document.createElement("li");
        let textNode4 = document.createTextNode(`거래 일시 : ${item.date}`);
        day.appendChild(textNode4);
        infoUl.appendChild(day);      
        
        container.appendChild(infoUl);            	
    });
}


/* 커스텀 마커를 등록하기 위한 자바스크립트 */

function setCenter(lat, lng) {            
	
    var imageSrc = '/assets/img/marker.png', // 마커이미지의 주소입니다    
    imageSize = new kakao.maps.Size(64, 64), // 마커이미지의 크기입니다
    imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

    // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
        markerPosition = new kakao.maps.LatLng(lat, lng); // 마커가 표시될 위치입니다

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
	    position: markerPosition,
	    image: markerImage, // 마커이미지 설정 
	    clickable: true
    });
    
    
    // 마커에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'click', function() {
    	loadingImg();
         // 마커 위에 인포윈도우를 표시합니다
    	fetch(`http://localhost:8080/apt/around?lat=${lat}&lng=${lng}`)
        .then((response) => response.json())
        .then((data) => {        	        	
            makeList(data);            
        });
    });

    // 마커가 지도 위에 표시되도록 설정합니다
    marker.setMap(map);  

    // 커스텀 오버레이에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    var content = '<div class="customoverlay">' +
        '  <a href="https://map.kakao.com/link/map/" target="_blank">' +
        '    <span class="title">카카오맵에서 보기</span>' +
        '  </a>' +
        '</div>';

    // 커스텀 오버레이가 표시될 위치입니다 
    var position = new kakao.maps.LatLng(lat, lng);  

    // 커스텀 오버레이를 생성합니다
    var customOverlay = new kakao.maps.CustomOverlay({
        map: map,
        position: position,
        content: content,
        yAnchor: 1 
    });
    
	
    // 이동할 위도 경도 위치를 생성합니다 
    var moveLatLon = new kakao.maps.LatLng(lat, lng);    
    // 지도 중심을 이동 시킵니다
    map.setCenter(moveLatLon);
}

function ScrollTop(){	
	document.getElementById("map-items").scrollTo({ left: 0, top: 0, behavior: "smooth" });
}