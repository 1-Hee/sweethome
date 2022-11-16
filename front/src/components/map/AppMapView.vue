<template>
  <div>
    <!--맵 컨텐츠 영역-->
    <hr />

    <div class="map-container">
      <div class="items">
        <div class="select-box">
          <select id="sido" class="sido-select" onchange="getCode('gugun', this.value)">
            <option value="">시/도</option>
          </select>
          <select id="gugun" class="gugun-select" @change="getCode('dong', gugunList[gugun].code)" v-model="gugun">
            <!-- <option value="">시/군/구</option> -->
            <option v-for="(gugun, index) in gugunList" :key="index" :value="index">{{ gugun.name }}</option>
          <select id="gugun" class="gugun-select" onchange="getCode('dong', this.value)">
            <option value="">시/군/구</option>
          </select>
          <select id="dong" class="dong-select" onchange="onChangeOption('pos', this.value)">
            <option value="">읍/면/동</option>
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
export default {
  name: "AppMapView",
};
</script>

<style>
@import url("../../assets/css/common.css");
@import url("../../assets/css/map.css");
</style>
