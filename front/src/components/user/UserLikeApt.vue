<template>
  <div>
    <div class="divider-min"></div>
    <h1 class="like-h1"><span>홍길동</span> 님의 찜목록</h1>
    <div class="divider0"></div>

    <div class="like-thumbnail-container flex-def">
      <a
        href="#"
        class="like-thumbnail"
        v-for="(item, index) in getAptLikeList"
        :key="index"
        @click.prevent="showItem(item)"
      >
        <img :src="imgList[index]" />
        <div class="like-text-caption">
          <h3>{{ item.apartmentName }}</h3>
          <p>{{ item.address }}</p>
          <p><span class="badge r-badge">아파트</span> {{ item.dealAmount }} 만원</p>
        </div>
      </a>
    </div>
    <p class="sentance">당신의 <b>"내 집 마련의 꿈"</b> 을 응원합니다.</p>
    <div class="divider2"></div>
  </div>
</template>

<script>
import { mapMutations, mapGetters, mapActions } from "vuex";
// const aptStore = "aptStore";
const memberStore = "memberStore";

export default {
  name: "UserLikeApt",
  data() {
    return {
      imgList: [],
    };
  },
  methods: {
    ...mapActions(memberStore, ["setAptLikeList"]),
    showWaiting() {
      document.getElementById("wating-bg").classList.remove("hide");
      document.getElementById("waiting-circle").classList.remove("hide");
    },
    showItem(item) {
      //console.dir(item);
      localStorage.setItem("dongCode", item.dongCode);
      localStorage.setItem("lat", item.lat);
      localStorage.setItem("lng", item.lng);
      localStorage.setItem("apartmentName", item.apartmentName);
      this.$router.push({ name: "MapView" });
    },
  },
  created() {
    for (let i = 0; i < 40; i++) {
      const idx = Math.floor(Math.random() * 40) + 1;
      let url = `../assets/img/apt/${idx}.jpg`;
      this.imgList.push(url);
    }

    let userId = this.getLoginMember.id;
    this.setAptLikeList(userId);
  },
  mounted() {
    this.showWaiting();
  },
  computed: {
    ...mapGetters(memberStore, ["getAptLikeList", "getLoginMember"]),
  },
};
</script>

<style>
@import url("../../assets/css/common.css");
@import url("../../assets/css/user-like-apt.css");
</style>
