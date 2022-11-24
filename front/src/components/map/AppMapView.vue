<template>
  <div>
    <!--맵 컨텐츠 영역-->
    <hr />

    <div class="map-container">
      <div class="items" @dblclick="ScrollTop">
        <div class="select-box">
          <select
            id="sido"
            class="sido-select"
            @change="
              getCode('gugun', sidoList[sido].code);
              setSidoText();
            "
            v-model="sido"
            @click="clearSido"
          >
            <!-- <option value="">시/도</option> -->
            <option v-for="(sido, index) in sidoList" :key="index" :value="index">{{ sido.name }}</option>
          </select>
          <select
            id="gugun"
            class="gugun-select"
            @change="
              getCode('dong', gugunList[gugun].code);
              setGugunText();
            "
            v-model="gugun"
            @click="clearGugun"
          >
            <!-- <option value="">시/군/구</option> -->
            <option v-for="(gugun, index) in gugunList" :key="index" :value="index">{{ gugun.name }}</option>
          </select>
          <select
            id="dong"
            class="dong-select"
            @change="
              getCode('pos', dong);
              setDongText();
            "
            v-model="dong"
          >
            <!-- <option value="">읍/면/동</option> -->
            <option v-for="(dong, index) in dongList" :key="index" :value="dong.code">{{ dong.name }}</option>
          </select>
          <h2>아파트 거래정보</h2>
        </div>
        <div class="apt-items-container" id="apt-items-container" @scroll="watchScroll">
          <table
            class="apt-info"
            v-for="(item, index) in getAptDataList"
            :key="index"
            @click="
              addMarkerByPOS(item);
              showRoadView(item.lat, item.lng);
            "
          >
            <tr>
              <th>아파트 이름</th>
              <td>{{ item.apartmentName }}</td>
            </tr>
            <tr>
              <th>거래 금액</th>
              <td>{{ item.dealAmount }}</td>
            </tr>
            <tr>
              <th>거래 일시</th>
              <td>{{ item.date }}</td>
            </tr>
            <tr>
              <th>전용 면적</th>
              <td>{{ item.area }} m2</td>
            </tr>
            <tr>
              <td colspan="2">
                <span>{{ item.address.split(" ")[1] + " " + item.address.split(" ")[2] }}</span>
                <a @click.prevent="likeItem(item)">👍</a>
              </td>
            </tr>
          </table>
        </div>
      </div>
      <div id="map">
        <div class="category-container">
          <ul id="category">
            <li id="BK9" data-order="0">
              <span class="category_bg bank"></span>
              은행
            </li>
            <li id="MT1" data-order="1">
              <span class="category_bg mart"></span>
              마트
            </li>
            <li id="PM9" data-order="2">
              <span class="category_bg pharmacy"></span>
              약국
            </li>
            <li id="OL7" data-order="3">
              <span class="category_bg oil"></span>
              주유소
            </li>
            <li id="CE7" data-order="4">
              <span class="category_bg cafe"></span>
              카페
            </li>
            <li id="CS2" data-order="5">
              <span class="category_bg store"></span>
              편의점
            </li>
          </ul>
        </div>
        <div id="roadview" :class="{ show: isClick, hide: !isClick }"></div>
        <button class="size-btn size-btn1 hide" @click="size720">720px</button>
        <button class="size-btn size-btn2 hide" @click="size1024">1024px</button>
        <button class="size-btn size-btn3 hide" @click="size100">100%</button>
      </div>
    </div>
    <!--맵 컨텐츠 영역-->
  </div>
</template>

<script>
import axios from "axios";
import { kakomapInit, searchByAddressKakao, markByPos, fx, markByPos2 } from "@/assets/js/map";
import { mapGetters, mapActions, mapMutations } from "vuex";
const aptStore = "aptStore";
const memberStore = "memberStore";

export default {
  name: "AppMapView",
  data() {
    return {
      sidoList: [],
      gugunList: [],
      dongList: [],
      AptDataList: [],
      sido: "",
      gugun: "",
      dong: "",
      selectedSidoText: "",
      selectedGugunText: "",
      selectedDongText: "",
      map: null,
      isWait: false,
      pageNo: 1,
      prevDiv: 0,
      division: 0,
      apartmentName: "",
      address: "",
      isClick: false,
    };
  },
  created() {
    // // this.SET_APT_DATA_LIST_NULL();
    // // this.AptDataList = [];
    // this.AptDataList = this.getAptDataList;
    // console.dir(this.AptDataList);
  },
  methods: {
    ...mapActions(aptStore, [
      "setAptDataList",
      "setAptDataListDong",
      "setAptDataAptName",
      "setAptListLatLng",
      "addAptDataLike",
    ]),
    ...mapMutations(aptStore, ["SET_APT_DATA_LIST_NULL"]),
    showWaiting() {
      document.getElementById("wating-bg").classList.remove("hide");
      document.getElementById("waiting-circle").classList.remove("hide");
    },
    clearSido() {
      this.gugunList = [];
      this.dongList = [];
    },
    clearGugun() {
      this.dongList = [];
    },

    // 카카오맵 init 메서드
    initMap() {
      kakomapInit();
    },
    async getCode(type, code) {
      await axios({
        url: `http://localhost:8080/apt/box?type=${type}&code=${code}`,
        method: "get",
      }).then(({ data }) => {
        if (type === "gugun") this.gugunList = data;
        else if (type === "dong") {
          this.dongList = data;
        } else if (type === "pos") this.aptList = data;
      });
    },

    // 백앤드로부터 가져온 동코드 정보를 지역 변수(?) 로 저장
    setSidoText() {
      const sidoEl = document.getElementById("sido");
      this.selectedSidoText = sidoEl.options[sido.selectedIndex].text;
    },
    setGugunText() {
      const gugunEL = document.getElementById("gugun");
      this.selectedGugunText = gugunEL.options[gugun.selectedIndex].text;
    },
    async setDongText() {
      const dongEl = document.getElementById("dong");
      this.selectedDongText = dongEl.options[dong.selectedIndex].text;
      this.searchKeyword = "";
      this.searchAptName = "";
      const container = document.getElementById("apt-items-container");
      container.innerHTML = ``;
      this.SET_APT_DATA_LIST_NULL(); // 배열 초기화 하고,
      this.pageNo = 1; // 페이지번호 초기화
      this.division = 0; // 분기점 초기화;
      searchByAddressKakao(this.selectedSidoText + " " + this.selectedDongText); // 맵 이동한 후
      this.searchByDongCode(); // 매물을 불러온다.
    },

    // 카카오맵 마커 추가메서드 + 클릭 기준으로 그 매물 아파트 이름으로 리로드
    async addMarkerByPOS(item) {
      this.SET_APT_DATA_LIST_NULL(); // 초기화 한 후
      this.pageNo = 1;
      this.division = 1;

      this.apartmentName = item.apartmentName;
      await this.searchByAptName();
      // console.dir(await this.getAptDataList);
      setTimeout(() => {
        let data = this.getAptDataList;
        markByPos(data);
      }, 1500);
    },
    async searchByDongCode() {
      this.isClick = false;
      document.querySelectorAll(".size-btn").forEach((el) => {
        el.classList.add("hide");
      });

      // 셀렉트 박스 기준으로 배열 불러오는 메서드
      this.showWaiting();
      let param = {
        pgNo: this.pageNo,
        listSize: 20,
      };
      await this.setAptDataList(this.dong, param);
    },
    async searchByAptName() {
      this.isClick = true;
      document.querySelectorAll(".size-btn").forEach((el) => {
        el.classList.remove("hide");
      });
      // 매물 아이템 클릭하면 매물 아이템 기준으로 매물 초기화
      let param = {
        aptName: this.apartmentName,
        pgNo: this.pageNo,
        listSize: 20,
      };
      this.showWaiting();
      await this.setAptDataAptName(param);
    },

    // 검색상자!
    async searchByAddress(address) {
      searchByAddressKakao(address);
    },
    // 검색어 기준 매물 설정 및 위치 조정...
    async searchByAddressInit() {
      let item = localStorage.getItem("keyword");
      localStorage.removeItem("keyword");
      //console.log(item);
      this.address = item;
      this.division = 2;
      this.pageNo = 1;
      this.searchByAddress(item);
      //this.searchByAddress(item);
    },

    // 무한스크롤
    async watchScroll() {
      const scrollBody = document.querySelector("#apt-items-container");
      if (Math.round(scrollBody.scrollHeight - scrollBody.scrollTop) <= scrollBody.clientHeight) {
        this.pageNo++;
        //console.log(this.division);
        this.showWaiting();
        switch (this.division) {
          case 0: // 셀렉트 박스
            this.searchByDongCode();
            break;
          case 1: // 매물 클릭
            this.searchByAptName();
            break;
          case 2: // 주소입력 시
            this.searchByAddress();
            break;
        }
      }
    },

    likeItem(item) {
      alert("찜목록에 추가되었습니다");
      let params = {
        userId: this.getLoginMember.id,
        aptNo: item.aptNo,
      };
      this.addAptDataLike(params);

      // console.log(index);
      // console.dir(this.AptDataList[index]);
    },
    // 스크롤 위로 올리는 메서드
    ScrollTop(e) {
      e.target.scrollTo({ left: 0, top: 0, behavior: "smooth" });
    },

    showRoadView(lat, lng) {
      // console.log(lat, lng);
      var roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
      var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
      var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

      var position = new kakao.maps.LatLng(lat, lng);

      // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
      roadviewClient.getNearestPanoId(position, 50, function (panoId) {
        roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
      });
    },
    // 로드뷰 사이즈 720px
    size720() {
      document.getElementById("roadview").setAttribute("style", "width:720px; height:468px");
    },
    size1024() {
      document.getElementById("roadview").setAttribute("style", "width:1024px; height:720px");
    },
    size100() {
      document.getElementById("roadview").setAttribute("style", "width:100%; height:100%");
    },
  },
  mounted() {
    axios({
      url: `http://localhost:8080/apt/box?type=sido&code=""`,
      method: "get",
    }).then(({ data }) => {
      data.forEach((item) => {
        this.sidoList.push(item);
      });
    });

    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAOMAP_KEY}&libraries=services`;
      document.head.appendChild(script);
    }
    this.SET_APT_DATA_LIST_NULL();
    // console.dir(localStorage.getItem("dongCode"));

    setTimeout(() => {
      // 키워드 검색이 있다면, 그걸 최우선으로 매물 검색
      if (localStorage.getItem("keyword")) {
        this.searchByAddressInit();
      } else {
        setTimeout(() => {
          if (localStorage.getItem("dongCode")) {
            console.log("yes IN...");
            this.dong = localStorage.getItem("dongCode");
            console.dir(this.dong);
            console.dir(localStorage.getItem("dongCode") != null);
            let lat = localStorage.getItem("lat");
            let lng = localStorage.getItem("lng");
            let apartmentName = localStorage.getItem("apartmentName");
            localStorage.clear();
            this.searchByDongCode();
            markByPos2(lat, lng, apartmentName);
          }
        }, 500);
      }
    }, 500);
  },
  computed: {
    ...mapGetters(aptStore, ["getAptDataList", "getPgInfo", "getPOS"]),
    ...mapGetters(memberStore, ["getLoginMember"]),
  },
  updated() {},
};
</script>

<style scoped>
@import url("../../assets/css/common.css");
@import url("../../assets/css/map.css");
#roadview {
  position: absolute;
  left: 0;
  /* width: 500px;
  height: 400px; */

  min-width: 300px;
  min-height: 200px;
  resize: both;
  overflow: hidden;
  border-left: 2px;

  z-index: 30;
  /* border: 1px solid black; */
  /* background-color: rgba(171, 50, 231, 0.995); */
}

.category-container > ul {
  position: absolute;
  list-style: none;
  right: 10px;
  bottom: 10px;
}
.category-container > ul > li {
  display: block;
  float: left;
  margin-left: 10px;
  width: 50px;
  height: 30px;
  line-height: 30px;
  background-color: white;
  border-radius: 5px;
}
.placeinfo {
  display: block;
  width: 100px;
  height: 100px;
  background-color: white;
}
.title {
}
.jibun {
  overflow: hidden;
}
.tel {
}

.category-container > ul > li:hover {
  cursor: pointer;
  transition: 0.3s;
  background-color: lightgrey;
}
#map > .category-container * {
  display: block;
  z-index: 20;
}

.hide {
  transition: 1s;
  display: none;
}
.show {
  transition: 1s;
  border: 1px solid black;
  display: block;
}

.size-btn {
  cursor: pointer;
  width: 60px;
  height: 35px;
  position: absolute;
  top: 5px;
  z-index: 10;
  border: 1px solid black;
  border-radius: 5px;
  background-color: white;
}
.size-btn:hover {
  transition: 0.45s;
  background-color: rgb(205, 203, 203);
}

.size-btn1 {
  right: 265px;
}
.size-btn2 {
  right: 195px;
}
.size-btn3 {
  right: 125px;
}
</style>
