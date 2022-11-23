<template>
  <div>
    <!-- 메인 영역 -->
    <hr />
    <div class="banner"><div class="banner-img"></div></div>
    <div class="divider1"></div>

    <div class="main-search-box">
      <p>부동산을 검색해보세요</p>
      <div class="divider-min"></div>
      <div>
        <input
          id="search-box"
          type="search"
          class="default-input"
          @keyup.enter="searchWithKeyWord"
          placeholder="주소를 입력해보세요!"
        />
      </div>
    </div>
    <div class="divider1"></div>

    <div class="main-thumbnail-title">
      <p><span class="m-span">MD</span>가 추천하는 인기 부동산</p>
    </div>

    <div class="divider1"></div>

    <div class="thumbnail-container flex-def">
      <a href="#" class="thumbnail" v-for="(item, index) in top4AptList" :key="index">
        <img src="https://source.unsplash.com/random" />
        <div class="text-caption">
          <h3>{{ item.apartmentName }}</h3>
          <p>{{ item.address }}</p>
          <p><span class="badge r-badge">아파트</span> {{ item.dealAmount }} 만원</p>
        </div>
      </a>
    </div>
    <div class="divider1"></div>
    <div class="main-search-box">
      <h1>태그로 부동산을 검색해보세요</h1>
    </div>
    <div class="tag-box flex-def" id="tag-container" @click.prevent="">
      <div>
        <a v-for="(item, index) in tagList" :key="index" class="badge tag-badge"
          ><span @click.prevent="giveTageSelect">{{ item }}</span></a
        >
      </div>
    </div>
    <div class="divider1"></div>

    <div class="thumbnail-container flex-def">
      <a href="#" class="thumbnail">
        <img src="/assets/img/apt5.jpg" />
        <div class="text-caption">
          <h3>유성CJ나인파크</h3>
          <p>대전시 유성구 봉명동 553-2</p>
          <p><span class="badge o-badge">아파트</span> 559,000,000 WON</p>
        </div>
      </a>
      <a href="#" class="thumbnail">
        <img src="/assets/img/apt6.jpeg" />
        <div class="text-caption">
          <h3>마린시티자이</h3>
          <p>부산시 해운대구 마린시티1로 9</p>
          <p><span class="badge o-badge">아파트</span> 1,700,000,000 WON</p>
        </div>
      </a>
      <a href="#" class="thumbnail">
        <img src="/assets/img/apt7.jpg" />
        <div class="text-caption">
          <h3>농성SK뷰센트럴</h3>
          <p>광주시 서구 농성동 702</p>
          <p><span class="badge o-badge">아파트</span> 655,000,000 WON</p>
        </div>
      </a>
    </div>

    <div class="divider3"></div>
    <div class="content-box">
      <div class="news flex-def">
        <div class="house-news">
          <h2><a href="#">부동산 뉴스</a></h2>
          <hr />
          <ul>
            <li v-for="(item, index) in naverNews" :key="index">
              <a :href="item.link" v-html="item.title"></a>
            </li>
          </ul>
        </div>
        <div class="boards">
          <h2><a href="#">자유게시판</a></h2>
          <hr />
          <ul>
            <li v-for="(item, index) in top4BoardList" :key="index">
              <a
                href="#"
                @click="
                  view(item.articleNo);
                  smooth();
                "
                >{{ item.content }}</a
              >
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div class="divider4"></div>
    <!-- <div id="map"></div> -->
    <!-- 메인 영역 -->
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
const aptStore = "aptStore";
const boardStore = "boardStore";
const noticeStore = "noticeStore";
const naverStore = "naverStore";
const memberStore = "memberStore";

export default {
  name: "AppIndex",
  data() {
    return {
      top4AptList: [],
      top4BoardList: [],
      top4NoticeList: [],
      naverNews: [],
      tagList: [
        "가격이 저렴한",
        "전망이 좋은",
        "조용한",
        "자주 찾는",
        "편의시설을 갖춘",
        "학군이 좋은",
        "백화점 인근",
        "휴양시설이 있는",
      ],
    };
  },
  methods: {
    ...mapActions(aptStore, ["setAptTOP4Items"]),
    ...mapActions(boardStore, ["setFourBoardList", "setBoard"]),
    ...mapActions(noticeStore, ["setFourNoticeList"]),
    ...mapActions(naverStore, ["setNaverNews"]),
    searchWithKeyWord(e) {
      //console.log(e.target.value);
      this.$router.push({ name: "MapView" });
      localStorage.setItem("keyword", e.target.value);
    },
    giveTageSelect(e) {
      // console.dir(document.getElementById("tag-container"));
      document.querySelectorAll(".tag-badge").forEach((el) => {
        // console.dir(el);
        el.classList.remove("tag-badge-selected");
      });
      //e.target.classList.add("tag-badge-selected");
      //console.dir(e.target);
      e.target.parentNode.classList.add("tag-badge-selected");
    },
    async view(articleNo) {
      this.setBoard(articleNo);
      setTimeout(() => {
        this.$router.push({ name: "BoardView" });
      }, 100);
    },
    smooth() {
      document.getElementById("waiting-circle").setAttribute("style", "display:block; transition:.3s;");
      document.getElementById("wating-bg").setAttribute("style", "display:block; transition:.3s;");
      setTimeout(() => {
        document.getElementById("waiting-circle").setAttribute("style", "display:none; transition:.3s;");
        document.getElementById("wating-bg").setAttribute("style", "display:none; transition:.3s;");
      }, 350);
    },
  },
  created() {
    this.setAptTOP4Items();
    this.top4AptList = this.getTop4AptList;
    this.setFourBoardList();
    this.top4BoardList = this.getTop4BoardList;
    this.setFourNoticeList();
    this.top4NoticeList = this.getTop4NoticeList;
    this.setNaverNews();

    // console.dir(this.tagList);
    this.naverNews = [];

    let list = this.getNaverNews;
    for (let i = 0; i < 5; i++) {
      this.naverNews.push(list[i]);
    }

    //console.dir(this.top4AptList);
    // console.dir(this.top4BoardList);
    //console.dir(this.top4NoticeList);
    //console.dir(this.naverNews);
  },
  computed: {
    ...mapGetters(aptStore, ["getTop4AptList"]),
    ...mapGetters(boardStore, ["getTop4BoardList"]),
    ...mapGetters(noticeStore, ["getTop4NoticeList"]),
    ...mapGetters(naverStore, ["getNaverNews"]),
  },
  mounted() {},
};
</script>

<style>
@import url("../assets/css/common.css");
@import url("../assets/css/index.css");
b {
  margin-right: 15px;
  color: rgb(255, 174, 0);
}
span {
  cursor: pointer;
}
</style>
