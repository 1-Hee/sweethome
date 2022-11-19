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

export function checkTableName(value) {
  alert(value, "입니다");
}

// TODO. 승범이형 이 만든 함수 + 서버에서 매물 가져오는 코드...
// 부동산 테이블 생성 메서드
export function makeList(data) {
  const container = document.getElementById("apt-items-container");
  //container.innerHTML = ``;
  //console.dir(data);

  for (let i = 0; i < data.length; i++) {
    let table = document.createElement("table");
    table.setAttribute("class", "apt-info");
    // infoUl.setAttribute("onclick", "setCenter(this.dataset.lat, this.dataset.lng);");
    let tr1 = document.createElement("tr");
    let th1 = document.createElement("th");
    th1.innerText = "아파트 이름";
    let td1 = document.createElement("td");
    td1.innerText = data[i].apartmentName;
    tr1.appendChild(th1);
    tr1.appendChild(td1);
    let tr2 = document.createElement("tr");
    let th2 = document.createElement("th");
    th2.innerText = "거래 금액";
    let td2 = document.createElement("td");
    td2.innerText = data[i].dealAmount;
    tr2.appendChild(th2);
    tr2.appendChild(td2);
    let tr3 = document.createElement("tr");
    let th3 = document.createElement("th");
    th3.innerText = "거래 일시";
    let td3 = document.createElement("td");
    td3.innerText = data[i].date;
    tr3.appendChild(th3);
    tr3.appendChild(td3);
    let tr4 = document.createElement("tr");
    let th4 = document.createElement("th");
    th4.innerText = "전용 면적";
    let td4 = document.createElement("td");
    td4.innerText = data[i].area;
    tr4.appendChild(th4);
    tr4.appendChild(td4);
    let td5 = document.createElement("td");
    td5.colSpan = 2;
    let span = document.createElement("span");
    span.innerText = data[i].dong;
    let a1 = document.createElement("a");
    a1.innerText = "👍";
    let a2 = document.createElement("a");
    a2.innerText = "🤍🧡";
    td5.appendChild(span);
    td5.appendChild(a1);
    td5.appendChild(a2);
    td5.setAttribute("style", "text-align:right; margin-right:10px;");
    table.appendChild(tr1);
    table.appendChild(tr2);
    table.appendChild(tr3);
    table.appendChild(tr4);
    table.appendChild(td5);
    container.appendChild(table);
  }
}

/* 커스텀 마커를 등록하기 위한 자바스크립트 */

function ScrollTop() {
  document.getElementById("map-items").scrollTo({ left: 0, top: 0, behavior: "smooth" });
}
