<template>
  <div>
    <!--맵 컨텐츠 영역-->
    <hr />

    <div class="map-container">
      <div class="items">
        <div class="select-box">
          <select id="sido" class="sido-select" @change="getCode('gugun', sidoList[sido].code)" v-model="sido">
            <!-- <option value="">시/도</option> -->
            <option v-for="(sido, index) in sidoList" :key="index" :value="index">{{ sido.name }}</option>
          </select>
          <select id="gugun" class="gugun-select" @change="getCode('dong', gugun.code)">
            <!-- <option value="">시/군/구</option> -->
            <option v-for="(gugun, index) in gugunList" :key="index">{{ gugun.name }}</option>
          </select>
          <select id="dong" class="dong-select" @change="onChangeOption('pos', this.value)">
            <!-- <option value="">읍/면/동</option> -->
            <option v-for="(dong, index) in dongList" :key="index">{{ dong.name }}</option>
          </select>
          <h2>아파트 거래정보</h2>
        </div>
        <div class="items" id="map-items">
          <ul class="apt-info">
            <li>아파트 이름</li>
            <li>거래 금액</li>
            <li>아무트 등등</li>
            <li>블라블라</li>
          </ul>
        </div>
      </div>
      <div id="map"></div>
    </div>
    <!--맵 컨텐츠 영역-->
  </div>
</template>

<script>
import axios from "axios";

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
    };
  },
  methods: {
    async getCode(type, code) {
      console.log("getCode 동작");
      console.log(type, code);
      console.dir(code);
      await axios({
        url: `http://localhost:8080/apt/box?type=${type}&code=${code}`,
        method: "get",
      }).then(({ data }) => {
        if (type === "gugun") this.gugunList = data;
        else if (type === "dong") this.dongList = data;
      });
    },
  },
  mounted() {
    axios({
      url: `http://localhost:8080/apt/box?type=sido&code=""`,
      method: "get",
    }).then(({ data }) => {
      this.sidoList = data;
      console.dir(this.sidoList);
    });
  },
};
</script>

<style>
@import url("../../assets/css/common.css");
@import url("../../assets/css/map.css");
</style>
