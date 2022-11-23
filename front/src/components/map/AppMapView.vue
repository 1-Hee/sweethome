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
            @click="addMarkerByKeyword(item.dong + ' ' + item.apartmentName)"
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
                <span>{{ item.dong }}</span>
                <a>👍</a>
                <a>🤍🧡</a>
              </td>
            </tr>
          </table>
        </div>
      </div>
      <div id="map"></div>
      <i v-if="isWait" id="waiting-circle" class="fa-solid fa-circle-notch wating-icon"></i>
      <div v-if="isWait" class="wating-bg"></div>
    </div>
    <!--맵 컨텐츠 영역-->
  </div>
</template>

<script>
import axios from "axios";
import { kakomapInit, searchByAddress, kakaoModule, markKeywordMarker } from "@/assets/js/map";
import { mapGetters, mapActions, mapMutations } from "vuex";
const aptStore = "aptStore";

export default {
  name: "AppMapView",
  data() {
    return {
      sidoList: [],
      gugunList: [],
      dongList: [],
      sido: "",
      gugun: "",
      dong: "",
      selectedSidoText: "",
      selectedGugunText: "",
      selectedDongText: "",
      map: null,
      isWait: false,
      pageNo: 1,
      searchKeyword: "",
      searchName: "",
    };
  },
  created() {},
  methods: {
    ...mapActions(aptStore, ["setAptDataList", "setAptDataListDong", "setAptDataAptName"]),
    ...mapMutations(aptStore, ["SET_APT_DATA_LIST_NULL"]),
    showWaiting(time) {
      if (!this.isWait) {
        this.isWait = true;
        setTimeout(() => {
          this.isWait = false;
        }, time);
      }
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
        else if (type === "dong") this.dongList = data;
        else if (type === "pos") this.aptList = data;
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

      searchByAddress(this.selectedSidoText + " " + this.selectedDongText);
      let param = {
        pgNo: this.pageNo,
        listSize: 20,
      };
      await this.setAptDataList(this.dong, param);
    },

    // 카카오맵 마커 추가메서드
    addMarkerByKeyword(keyword) {
      this.searchKeyword = "";
      this.dong = "";
      this.apartmentName = keyword.split(" ")[1];
      markKeywordMarker(keyword);

      let param = {
        aptName: this.apartmentName,
        pgNo: this.pageNo,
        listSize: 20,
      };
      this.SET_APT_DATA_LIST_NULL();
      this.showWaiting(1500);
      this.setAptDataAptName(param);
    },

    // 무한스크롤
    async watchScroll() {
      const scrollBody = document.querySelector("#apt-items-container");
      if (Math.round(scrollBody.scrollHeight - scrollBody.scrollTop) <= scrollBody.clientHeight) {
        this.pageNo++;

        if (this.searchKeyword != "") {
          this.showWaiting(1500);
          let param = {
            dongName: this.searchKeyword,
            pgNo: this.pageNo,
            listSize: 20,
          };
          this.setAptDataListDong(param);
        } else if (this.apartmentName != "") {
          this.showWaiting(1500);
          let param = {
            aptName: this.apartmentName,
            pgNo: this.pageNo,
            listSize: 20,
          };
          this.setAptDataAptName(param);
        } else {
          this.showWaiting(100);
          let param = {
            pgNo: this.pageNo,
            listSize: 20,
          };
          await this.setAptDataList(this.dong, param);
        }
      }
    },
    // 스크롤 위로 올리는 메서드
    ScrollTop(e) {
      e.target.scrollTo({ left: 0, top: 0, behavior: "smooth" });
    },

    // 검색어 기준 매물 설정 및 위치 조정...
    async searchByKeyword() {
      let item = localStorage.getItem("keyword");
      // console.log(item == null);
      if (item != null) {
        searchByAddress(item);
        // console.log(item);
        localStorage.removeItem("keyword");

        let tempDong = item.split(" ");
        for (let i = tempDong.length - 1; i >= 0; i--) {
          if (tempDong[i].split("-").length > 1) continue;
          else if (tempDong[i].split("-").length == 1 && !isNaN(tempDong[i].split("-")[0])) continue;
          else if (tempDong[i].split("-").length == 1 && isNaN(tempDong[i].split("-")[0])) {
            // console.log("잘 분기됨 : ", tempDong[i].split("-")[0]);
            let dongName = tempDong[i].split("-")[0];
            this.searchKeyword = dongName;
            let param = {
              dongName: dongName,
              pgNo: this.pageNo,
              listSize: 20,
            };
            await this.setAptDataListDong(param);
            this.showWaiting(2000);
            break;
          }
        }
        // console.dir(tempDong);
      }
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
    // kakaoModule();

    this.showWaiting(100);
    this.searchByKeyword();
  },
  computed: {
    ...mapGetters(aptStore, ["getAptDataList", "getPgInfo"]),
  },
  updated() {},
};
</script>

<style scoped>
@import url("../../assets/css/common.css");
@import url("../../assets/css/map.css");
</style>
