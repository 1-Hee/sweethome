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
        <div class="apt-items-container" id="apt-items-container" @scroll="watchScroll"></div>
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
import { kakomapInit, searchByAddress, makeList } from "@/assets/js/map";
import { mapGetters, mapActions } from "vuex";
const aptStore = "aptStore";

export default {
  name: "AppMapView",
  data() {
    return {
      sidoList: [],
      gugunList: [],
      dongList: [],
      aptDataList: [],
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
    };
  },
  created() {},
  methods: {
    ...mapGetters(aptStore, ["getAptDataList", "getPgInfo"]),
    ...mapActions(aptStore, ["setAptDataList", "setAptDataListDong"]),
    showWaiting(time) {
      if (!this.isWait) {
        this.isWait = true;
        setTimeout(() => {
          this.isWait = false;
        }, time);
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
    async makeTable(data) {
      makeList(data);
    },
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
      //console.log(this.selectedSidoText, this.selectedGugunText, this.selectedDongText);
      this.searchKeyword = "";
      const container = document.getElementById("apt-items-container");
      container.innerHTML = ``;

      searchByAddress(this.selectedSidoText + " " + this.selectedDongText);
      let param = {
        pgNo: this.pageNo,
        listSize: 20,
      };
      //console.log(this.selectedSidoText);
      await this.setAptDataList(this.dong, param);
      this.aptDataList = await this.getAptDataList();
      await this.makeTable(this.aptDataList);
    },
    async watchScroll() {
      const scrollBody = document.querySelector("#apt-items-container");
      if (Math.round(scrollBody.scrollHeight - scrollBody.scrollTop) <= scrollBody.clientHeight) {
        this.pageNo = this.pageNo + 1;
        if (this.searchKeyword == "") {
          this.showWaiting(100);
          let param = {
            pgNo: this.pageNo,
            listSize: 20,
          };
          await this.setAptDataList(this.dong, param);
          await this.makeTable(this.getAptDataList());
        } else {
          this.showWaiting(1500);
          let param = {
            dongName: this.searchKeyword,
            pgNo: this.pageNo,
            listSize: 20,
          };
          this.setAptDataListDong(param);
          this.makeTable(this.getAptDataList());
        }
      }
    },
    ScrollTop() {
      document.getElementById("apt-items-container").scrollTo({ left: 0, top: 0, behavior: "smooth" });
    },
    async searchByKeyword() {
      let item = localStorage.getItem("keyword");
      console.log(item == null);
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

            setTimeout(() => {
              this.aptDataList = this.getAptDataList();
              console.dir(this.aptDataList);
              this.makeTable(this.aptDataList);
            }, 2000);
            break;
          }
        }

        console.dir(tempDong);
      }
    },
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
    this.showWaiting(100);

    setTimeout(() => {
      this.searchByKeyword();
    }, 100);
  },
  computed: {},
  updated() {
    // console.dir(document.getElementById("apt-items-container"));
    let cont = document.getElementById("apt-items-container");
    for (let i = 0; i < cont.childElementCount; i++) {
      cont.childNodes[i].addEventListener("click", function () {
        console.dir(cont.childNodes[i].childNodes[4].childNodes[0].innerText);
        alert(cont.childNodes[i].childNodes[4].childNodes[0].innerText);
        searchByAddress(cont.childNodes[i].childNodes[4].childNodes[0].innerText);
      });
    }
    //console.dir(document.getElementById("apt-items-container").childNodes);
  },
};
</script>

<style scoped>
@import url("../../assets/css/common.css");
@import url("../../assets/css/map.css");
</style>
