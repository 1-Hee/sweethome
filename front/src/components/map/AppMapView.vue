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
          <table class="apt-info" v-for="(item, index) in getAptDataList" :key="index" @click="addMarkerByPOS(item)">
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
                <a @click.prevent="likeItem($event, item, index)">👍</a>
              </td>
            </tr>
          </table>
        </div>
      </div>
      <div id="map"></div>
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
      //console.log(fx("삼성화재 유성연수원"));
    },

    // 카카오맵 마커 추가메서드 + 클릭 기준으로 그 매물 아파트 이름으로 리로드
    async addMarkerByPOS(item) {
      this.SET_APT_DATA_LIST_NULL(); // 초기화 한 후
      this.pageNo = 1;
      this.division = 1;

      this.apartmentName = item.apartmentName;
      await this.searchByAptName();
      // fx();
      // console.dir(await this.getAptDataList);
      setTimeout(() => {
        let data = this.getAptDataList;
        markByPos(data);
      }, 1500);
    },
    async searchByDongCode() {
      // 셀렉트 박스 기준으로 배열 불러오는 메서드
      this.showWaiting();
      let param = {
        pgNo: this.pageNo,
        listSize: 20,
      };
      await this.setAptDataList(this.dong, param);
    },
    async searchByAptName() {
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
    // async searchByAddress(address) {
    //   searchByAddressKakao(address);
    //   // this.pos = this.getPOS;
    //   //주소지 기준으로 매물 불러오기,
    //   this.showWaiting();
    //   let param = {
    //     lat: this.searchKeyword,
    //     lng: this.pageNo,
    //   };
    //   this.setAptListLatLng(param);
    // },
    // // 검색어 기준 매물 설정 및 위치 조정...
    // async searchByAddressInit() {
    //   // init...
    //   let item = localStorage.getItem("keyword");
    //   localStorage.removeItem("keyword");
    //   console.log(item);
    //   this.address = item;
    //   this.division = 2;
    //   this.pageNo = 1;
    //   this.searchByAddress(item);
    // },
    // 메인에서 아이템 클릭 해올 때,
    // async searchByPOS() {
    //   let address = localStorage.getItem("address");
    //   searchByAddressKakao(address);
    //   let lat = localStorage.getItem("lat");
    //   let lng = localStorage.getItem("lng");
    //   let param = {
    //     lat: lat,
    //     lng: lng,
    //   };
    //   this.setAptListLatLng(param);
    // },
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

    likeItem(e, item, index) {
      alert("찜목록에 추가되었습니다");
      // console.dir(e.target);
      // e.target.innerHTML = ``;
      // let text = document.createTextNode("♥");
      // e.target.appendChild(text);
      // console.dir(e);
      // console.dir(item);
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

    setTimeout(() => {
      // 키워드 검색이 있다면, 그걸 최우선으로 매물 검색
      if (localStorage.getItem("keyword")) {
        // this.searchByAddressInit();
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
</style>
