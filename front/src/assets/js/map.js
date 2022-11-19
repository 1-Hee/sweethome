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

// TODO. 승범이형 이 만든 함수 + 서버에서 매물 가져오는 코드...
// 부동산 매물 생성
// function makeList(data) {
//   const container = document.getElementById("map-items");
//   container.innerHTML = ``;
//   // let head = document.createElement("h2");
//   // head.innerText = "아파트 거래 정보";
//   // container.appendChild(head);
//   // container.setAttribute("ondblclick", "ScrollTop();");

//   data.forEach((item) => {
//     let infoUl = document.createElement("ul");
//     infoUl.setAttribute("class", "apt-info");
//     infoUl.dataset.lat = `${item.lat}`;
//     infoUl.dataset.lng = `${item.lng}`;
//     infoUl.setAttribute("onclick", "setCenter(this.dataset.lat, this.dataset.lng);");

//     let name = document.createElement("li");
//     let textNode1 = document.createTextNode(`아파트 이름 : ${item.apartmentName}`);
//     name.appendChild(textNode1);
//     infoUl.appendChild(name);

//     let price = document.createElement("li");
//     let textNode2 = document.createTextNode(`거래 금액 : ${item.dealAmount}`);
//     price.appendChild(textNode2);
//     infoUl.appendChild(price);

//     let area = document.createElement("li");
//     let textNode3 = document.createTextNode(`면적 : ${item.area}`);
//     area.appendChild(textNode3);
//     infoUl.appendChild(area);

//     let day = document.createElement("li");
//     let textNode4 = document.createTextNode(`거래 일시 : ${item.date}`);
//     day.appendChild(textNode4);
//     infoUl.appendChild(day);

//     container.appendChild(infoUl);
//   });
// }

/* 커스텀 마커를 등록하기 위한 자바스크립트 */

function ScrollTop() {
  document.getElementById("map-items").scrollTo({ left: 0, top: 0, behavior: "smooth" });
}
