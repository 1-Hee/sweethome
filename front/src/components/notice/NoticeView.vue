<template>
  <div>
    <!-- 공지사항 뷰 영역-->
    <hr />
    <div class="view-form-container flex-def">
      <div class="left-menu">
        <a href="#" class="defbtn btn-back" @click="backToList">뒤로가기</a>
      </div>

      <div class="right-menu" v-if="detectAdmin">
        <div class="modify-div">
          <button type="button" id="modify-btn" class="modify defbtn" @click="doModifyNotice">글 수정</button>
          <button
            type="button"
            id="modify-cancel-btn"
            style="display: none"
            class="modify defbtn"
            @click="unDoModifyNotice"
          >
            수정 취소
          </button>
        </div>
        <div class="cancel-div">
          <a class="cancel defbtn" id="delete-btn" @click="deleteBoard(Notice.articleNo)">글 삭제</a>
        </div>
      </div>
    </div>
    <div id="view" class="view-container">
      <div class="title-section">
        <h1 id="category-text">공지사항</h1>
      </div>
      <div class="content-box">
        <hr />
        <div class="title" id="title"><span class="badge dgr-badge">제목</span> {{ Notice.title }}</div>
        <div class="article-info-section">
          <span class="writer" id="writer">{{ Notice.userId }}</span>
          <span class="date" id="date">{{ Notice.registDate }}</span>
          <span class="hit" id="hit">({{ Notice.hit }})</span>
        </div>
        <hr />
        <textarea class="content-textarea content" id="content" readonly :value="Notice.content"> </textarea>
      </div>
    </div>

    <div id="modiform" style="display: none" action="#" method="post">
      <div id="modify-view" class="view-container">
        <div class="title-section">
          <h1 id="category-text">공지사항</h1>
        </div>
        <hr />
        <div class="content-box">
          <input id="articleNoHidden" type="hidden" name="articleNo" />
          <div class="title">
            <span class="badge dgr-badge">제목</span>
            <input type="text" id="mtitle" name="title" class="default-input modi-title" v-model="Notice.title" />
          </div>
          <div class="article-info-section">
            <span class="writer" id="mwriter">{{ Notice.userId }}</span>
            <span class="date" id="mdate">{{ Notice.registDate }}</span>
            <span class="hit" id="mhit">({{ Notice.hit }})</span>
          </div>
          <hr />
          <div class="content">
            <textarea class="content-input default-input" id="mcontent" name="content" v-model="Notice.content">
            </textarea>
          </div>
        </div>
        <div class="send-modify">
          <button type="button" class="defbtn submit-btn" @click="sendModifyBoard">수정하기</button>
        </div>
      </div>
    </div>
    <!-- 공지사항 뷰 영역-->
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
const noticeStore = "noticeStore";
const memberStore = "memberStore";

export default {
  name: "NoticeView",
  data() {
    return {
      Notice: {},
      pgInfo: {},
    };
  },
  methods: {
    ...mapGetters(noticeStore, ["getNoticeList", "getNotice", "getPgInfo"]),
    ...mapGetters(memberStore, ["getLoginMember"]),
    ...mapActions(noticeStore, ["modifyNotice", "removeNotice", "setNoticeList"]),
    async sendModifyBoard() {
      this.modifyNotice(this.Notice);
      this.backToList();
    },
    async deleteBoard(articleNo) {
      if (confirm("정말로 삭제하시겠습니까?")) {
        this.removeNotice(articleNo);
        this.backToList();
      }
    },
    backToList() {
      let param = {
        pageNo: this.pgInfo.pageNum,
        listSize: 10,
      };
      this.setNoticeList(param);
      setTimeout(() => {
        this.$emit("notice-list");
      }, 100);
    },
    // doModifyNotice() {
    //   document.getElementById("view").setAttribute("style", "display:none;");
    //   document.getElementById("modiform").setAttribute("style", "display:block;");
    //   document.getElementById("modify-btn").setAttribute("style", "display:none;");
    //   document.getElementById("modify-cancel-btn").setAttribute("style", "display:block;");
    // },
    // unDoModifyNotice() {
    //   document.getElementById("modiform").setAttribute("style", "display:none;");
    //   document.getElementById("view").setAttribute("style", "display:block;");
    //   document.getElementById("modify-btn").setAttribute("style", "display:block;");
    //   document.getElementById("modify-cancel-btn").setAttribute("style", "display:none;");
    // },
  },
  mounted() {},
  created() {
    this.Notice = this.getNotice();
    console.log(this.Notice);
    this.pgInfo = this.getPgInfo();
  },
  computed: {
    detectAdmin() {
      return this.getLoginMember().grade == 9;
    },
  },
};
</script>

<style>
@import url("../../assets/css/common.css");
@import url("../../assets/css/board-view.css");
</style>
