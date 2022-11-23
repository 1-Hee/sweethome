<template>
  <div>
    <!-- 공지사항 뷰 영역-->
    <hr />
    <div class="view-form-container flex-def">
      <div class="left-menu">
        <a href="#" class="defbtn btn-back" @click="backToList">뒤로가기</a>
      </div>

      <div class="right-menu" v-if="Board.userId == getLoginMember.id">
        <div class="modify-div">
          <button type="button" id="modify-btn" class="modify defbtn" :class="{ show: !isActiveModify }">
            글 수정
          </button>
          <button
            type="button"
            id="modify-cancel-btn"
            style="display: none"
            class="modify defbtn"
            :class="{ show: isActiveModify }"
          >
            수정 취소
          </button>
        </div>
        <div class="cancel-div">
          <a class="cancel defbtn" id="delete-btn" @click="deleteBoard(Board.articleNo)">글 삭제</a>
        </div>
      </div>
    </div>
    <div id="view" class="view-container" :class="{ show: !isActiveModify }">
      <div class="title-section">
        <h1 id="category-text">자유게시판</h1>
      </div>
      <div class="content-box">
        <hr />
        <div class="title" id="title"><span class="badge dgr-badge">제목</span> {{ Board.title }}</div>
        <div class="article-info-section">
          <span class="writer" id="writer">{{ Board.userId }}</span>
          <span class="date" id="date">{{ Board.registDate }}</span>
          <span class="hit" id="hit">({{ Board.hit }})</span>
        </div>
        <hr />
        <textarea class="content-textarea content" id="content" readonly :value="Board.content"> </textarea>
      </div>
    </div>

    <div id="modiform" style="display: none" :class="{ show: isActiveModify }">
      <div id="modify-view" class="view-container">
        <div class="title-section">
          <h1 id="category-text">자유게시판</h1>
        </div>
        <hr />
        <div class="content-box">
          <input id="articleNoHidden" type="hidden" name="articleNo" />
          <div class="title">
            <span class="badge dgr-badge">제목</span
            ><input type="text" id="mtitle" name="title" class="default-input modi-title" v-model="Board.title" />
          </div>
          <div class="article-info-section">
            <span class="writer" id="mwriter">{{ Board.userId }}</span>
            <span class="date" id="mdate">{{ Board.registDate }}</span>
            <span class="hit" id="mhit">({{ Board.hit }})</span>
          </div>
          <hr />
          <div class="content">
            <textarea class="content-input default-input" id="mcontent" name="content" v-model="Board.content">
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
const boardStore = "boardStore";
const memberStore = "memberStore";

export default {
  name: "BoardView",
  data() {
    return {
      Board: {},
      pgInfo: {},
      isActiveModify: false,
    };
  },
  methods: {
    ...mapGetters(boardStore, ["getBoardList", "getBoard", "getPgInfo"]),
    ...mapActions(boardStore, ["modifyBoard", "removeBoard", "setBoardList"]),
    async sendModifyBoard() {
      this.modifyBoard(this.Board);

      this.backToList();
    },
    async deleteBoard(articleNo) {
      if (confirm("정말로 삭제하시겠습니까?")) {
        this.removeBoard(articleNo);
        this.backToList();
      }
    },
    backToList() {
      let param = {
        pageNo: this.pgInfo.pageNum,
        listSize: 10,
      };
      this.setBoardList(param);
      setTimeout(() => {
        this.$emit("board-list");
      }, 100);
    },
  },
  computed: {
    ...mapGetters(memberStore, ["getLoginMember"]),
  },
  mounted() {},
  created() {
    this.Board = this.getBoard();
    this.pgInfo = this.getPgInfo();
    //console.log(this.getLoginMember);
    //console.dir(this.Board);
  },
};
</script>

<style>
@import url("../../assets/css/common.css");
@import url("../../assets/css/board-list.css");
.show {
  display: block;
}
</style>
