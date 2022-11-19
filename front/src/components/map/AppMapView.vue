<template>
  <div>
    <!--맵 컨텐츠 영역-->
    <hr />

    <div class="map-container">
      <div class="items">
        <div class="select-box">
          <select
            id="sido"
            class="sido-select"
            @change="
              getCode('gugun', sidoList[sido].code);
              setSidoText();
            "
            v-model="sido"
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
        <div class="items" id="map-items">
          <ul class="apt-info">
            <li>아파트 이름</li>
            <li>거래 금액</li>
            <li></li>
            <li></li>
          </ul>
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
import { kakomapInit, searchByAddress } from "@/assets/js/map";

export default {
  name: "AppMapView",
  data() {
    return {
      sidoList: [],
      gugunList: [],
      dongList: [],
      aptList: [],
      sido: "",
      gugun: "",
      dong: "",
      selectedSidoText: "",
      selectedGugunText: "",
      selectedDongText: "",
      map: null,
      isWait: false,
    };
  },
  methods: {
    showWaiting() {
      if (!this.isWait) {
        this.isWait = true;
        setTimeout(() => {
          this.isWait = false;
        }, 1000);
      }
    },
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

    setSidoText() {
      const sido = document.getElementById("sido");
      this.selectedSidoText = sido.options[sido.selectedIndex].text;
    },
    setGugunText() {
      const gugun = document.getElementById("gugun");
      this.selectedGugunText = gugun.options[gugun.selectedIndex].text;
    },
    setDongText() {
      const dong = document.getElementById("dong");
      this.selectedDongText = dong.options[dong.selectedIndex].text;
      console.log(this.selectedSidoText, this.selectedGugunText, this.selectedDongText);
      searchByAddress(this.selectedDongText);
    },
    giveSelect() {},
  },
  mounted() {
    axios({
      url: `http://localhost:8080/apt/box?type=sido&code=""`,
      method: "get",
    }).then(({ data }) => {
      this.sidoList = data;
      // console.dir(this.sidoList);
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
  },
  computed: {
    getFullCode() {
      console.log(this.dong);
    },
  },
  updated() {
    // console.log("변경 있음");
    // this.initMap();
  },
};
</script>

<style>
@import url("../../assets/css/common.css");
@import url("../../assets/css/map.css");
</style>
