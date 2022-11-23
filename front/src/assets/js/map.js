/* 카카오 맵 init 부분 */
export function kakomapInit() {
  var mapContainer = document.getElementById("map"), // 지도를 표시할 div
    mapOption = {
      center: new kakao.maps.LatLng(37.54699, 127.09598), // 지도의 중심좌표
      level: 2, // 지도의 확대 레벨
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
}

// 카카오맵 init 모듈
export function kakaoModule(){
  if (window.kakao && window.kakao.maps) {
    this.initMap();
  } else {
    const script = document.createElement("script");
    /* global kakao */
    script.onload = () => kakao.maps.load(this.kakomapInit());
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAOMAP_KEY}&libraries=services`;
    document.head.appendChild(script);
  }
}


// 주소를 기준으로 카카오맵 이동
export function searchByAddress(address) {
  const mapContainer = document.getElementById("map"), // 지도를 표시할 div
    mapOption = {
      center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
      level: 3, // 지도의 확대 레벨
    };

  // 지도를 생성합니다
  const map = new kakao.maps.Map(mapContainer, mapOption);

  // 주소-좌표 변환 객체를 생성합니다
  const geocoder = new kakao.maps.services.Geocoder();

  // 주소로 좌표를 검색합니다
  geocoder.addressSearch(address, function (result, status) {
    // 정상적으로 검색이 완료됐으면
    if (status === kakao.maps.services.Status.OK) {
      var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

      // 결과값으로 받은 위치를 마커로 표시합니다
      let marker = new kakao.maps.Marker({
        map: map,
        position: coords,
      });

      var iwContent = `<div class="addr-dong-info-box"><p>찾으시는 장소가 여기인가요?</p><hr /><p>${address}</p></div>`; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
      // var iwContent = `<div class="addr-apt-info-box"><p>풍림빌라</p><hr /><p>계덕동</p></div>`;
      var iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

      // 인포윈도우를 생성하고 지도에 표시합니다
      var infowindow = new kakao.maps.InfoWindow({
        map: map, // 인포윈도우가 표시될 지도
        position: coords,
        content: iwContent,
        removable: iwRemoveable,
      });
      infowindow.open(map, marker);

      // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
      map.setCenter(coords);
    }
  });
}

export function addMarker() {
  var mapContainer = document.getElementById("map"), // 지도를 표시할 div
    mapOption = {
      center: new kakao.maps.LatLng(37.54699, 127.09598), // 지도의 중심좌표
      level: 4, // 지도의 확대 레벨
    };

  var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

  var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png", // 마커이미지의 주소입니다
    imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
    imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

  // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
  var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
    markerPosition = new kakao.maps.LatLng(37.54699, 127.09598); // 마커가 표시될 위치입니다

  // 마커를 생성합니다
  var marker = new kakao.maps.Marker({
    position: markerPosition,
    image: markerImage, // 마커이미지 설정
  });

  // 마커가 지도 위에 표시되도록 설정합니다
  marker.setMap(map);
}

// 부동산 테이블 생성 메서드
// export function makeList(data) {
//   const container = document.getElementById("apt-items-container");
//   //container.innerHTML = ``;
//   //console.dir(data);

//   for (let i = 0; i < data.length; i++) {
//     let table = document.createElement("table");
//     table.setAttribute("class", "apt-info");
//     // infoUl.setAttribute("onclick", "setCenter(this.dataset.lat, this.dataset.lng);");
//     let tr1 = document.createElement("tr");
//     let th1 = document.createElement("th");
//     th1.innerText = "아파트 이름";
//     let td1 = document.createElement("td");
//     td1.innerText = data[i].apartmentName;
//     tr1.appendChild(th1);
//     tr1.appendChild(td1);
//     let tr2 = document.createElement("tr");
//     let th2 = document.createElement("th");
//     th2.innerText = "거래 금액";
//     let td2 = document.createElement("td");
//     td2.innerText = data[i].dealAmount;
//     tr2.appendChild(th2);
//     tr2.appendChild(td2);
//     let tr3 = document.createElement("tr");
//     let th3 = document.createElement("th");
//     th3.innerText = "거래 일시";
//     let td3 = document.createElement("td");
//     td3.innerText = data[i].date;
//     tr3.appendChild(th3);
//     tr3.appendChild(td3);
//     let tr4 = document.createElement("tr");
//     let th4 = document.createElement("th");
//     th4.innerText = "전용 면적";
//     let td4 = document.createElement("td");
//     td4.innerText = data[i].area;
//     tr4.appendChild(th4);
//     tr4.appendChild(td4);
//     let td5 = document.createElement("td");
//     td5.colSpan = 2;
//     let span = document.createElement("span");
//     span.innerText = data[i].dong;
//     let a1 = document.createElement("a");
//     a1.innerText = "👍";
//     let a2 = document.createElement("a");
//     a2.innerText = "🤍🧡";
//     td5.appendChild(span);
//     td5.appendChild(a1);
//     td5.appendChild(a2);
//     td5.setAttribute("style", "text-align:right; margin-right:10px;");
//     table.appendChild(tr1);
//     table.appendChild(tr2);
//     table.appendChild(tr3);
//     table.appendChild(tr4);
//     table.appendChild(td5);
//     table.dataset.aptName = data[i].apartmentName;
//     console.dir(table);
//     table.addEventListener("cilck");
//     container.appendChild(table);
//   }
// }

export function markKeywordMarker(keyword) {
  // 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
  var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });

  var mapContainer = document.getElementById("map"), // 지도를 표시할 div
    mapOption = {
      center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
      level: 3, // 지도의 확대 레벨
    };

  // 지도를 생성합니다
  var map = new kakao.maps.Map(mapContainer, mapOption);

  // 장소 검색 객체를 생성합니다
  var ps = new kakao.maps.services.Places();

  // 키워드로 장소를 검색합니다
  ps.keywordSearch(keyword, placesSearchCB);

  // 키워드 검색 완료 시 호출되는 콜백함수 입니다
  function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {
      // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
      // LatLngBounds 객체에 좌표를 추가합니다
      var bounds = new kakao.maps.LatLngBounds();

      for (var i = 0; i < data.length; i++) {
        displayMarker(data[i]);
        bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
      }

      // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
      map.setBounds(bounds);
    }
  }

  // 지도에 마커를 표시하는 함수입니다
  function displayMarker(place) {
    // 마커를 생성하고 지도에 표시합니다
    var imageSrc = "https://cdn-icons-png.flaticon.com/128/5583/5583006.png", // 마커이미지의 주소입니다
      imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
      imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

    // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
      markerPosition = new kakao.maps.LatLng(place.y, place.x); // 마커가 표시될 위치입니다

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
      map: map,
      position: markerPosition,
      image: markerImage, // 마커이미지 설정
    });

    // 마커에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(marker, "click", function () {
      var coords = new kakao.maps.LatLng(place.y, place.x);
      var iwContent = `<div class="addr-dong-info-box" style="width:150px; height:max-content;"><p>SWEET HOME</p><hr /><p>${place.place_name}</p></div>`;
      var iwRemoveable = true;

      // 인포윈도우를 생성하고 지도에 표시합니다
      var infowindow = new kakao.maps.InfoWindow({
        map: map, // 인포윈도우가 표시될 지도
        position: coords,
        content: iwContent,
        removable: iwRemoveable,
      });
      infowindow.open(map, marker);
    });
  }
}



// 위 경도로 주소 검색
export function getAddressByPOS(lng, lat) {

  var geocoder = new kakao.maps.services.Geocoder();
  geocoder.coord2Address(lng, lat,  
    function(result, status) {
    if (status === kakao.maps.services.Status.OK) {
      console.dir(result);
    }
    });
}





function test(){
  var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 1 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

var marker = new kakao.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
    infowindow = new kakao.maps.InfoWindow({zindex:1}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다

// 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
searchAddrFromCoords(map.getCenter(), displayCenterInfo);

// 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
    searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
            var detailAddr = !!result[0].road_address ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
            detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';
            
            var content = '<div class="bAddr">' +
                            '<span class="title">법정동 주소정보</span>' + 
                            detailAddr + 
                        '</div>';

            // 마커를 클릭한 위치에 표시합니다 
            marker.setPosition(mouseEvent.latLng);
            marker.setMap(map);

            // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
            infowindow.setContent(content);
            infowindow.open(map, marker);
        }   
    });
});

// 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
kakao.maps.event.addListener(map, 'idle', function() {
    searchAddrFromCoords(map.getCenter(), displayCenterInfo);
});

function searchAddrFromCoords(coords, callback) {
    // 좌표로 행정동 주소 정보를 요청합니다
    geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);         
}

function searchDetailAddrFromCoords(coords, callback) {
    // 좌표로 법정동 상세 주소 정보를 요청합니다
    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
}

// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
function displayCenterInfo(result, status) {
    if (status === kakao.maps.services.Status.OK) {
        var infoDiv = document.getElementById('centerAddr');

        for(var i = 0; i < result.length; i++) {
            // 행정동의 region_type 값은 'H' 이므로
            if (result[i].region_type === 'H') {
                infoDiv.innerHTML = result[i].address_name;
                break;
            }
        }
    }    
}
}
