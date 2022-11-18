<template>
  <div>
    <!-- 공지사항 리스트 영역-->
    <hr />
    <div class="table-container">
      <div class="board-write-container">
        <button v-if="detectAdmin" class="board-write-btn" @click="BoardWrite">글쓰기</button>
      </div>
      <table class="custom-table" id="table">
        <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>조회수</th>
            <th>작성일</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, index) in initList" :key="index" @click="view(item.articleNo)">
            <td>{{ index + 1 }}</td>
            <td>{{ item.title }}</td>
            <td>{{ item.userId }}</td>
            <td>{{ item.hit }}</td>
            <td>{{ item.registDate }}</td>
          </tr>
        </tbody>
      </table>
      <ul class="page-tab">
        <li class="pg-first" v-show="initPgInfo.navigateFirstPage > 1" @click="getList(selectNo - 1)">
          <a href="#">◀</a>
        </li>
        <li
          class="pg-common"
          :class="{ select: getPgInfo().pageNum == key }"
          v-for="(key, index) in getPgInfo().navigatepageNums"
          :key="index"
          @click="getList(key)"
        >
          <a href="#">{{ key }}</a>
        </li>
        <li class="pg-last" v-show="initPgInfo.navigateLastPage != initPgInfo.pages" @click="getList(selectNo + 1)">
          <a href="#">▶</a>
        </li>
      </ul>
    </div>
    <!-- 공지사항 리스트 영역-->
  </div>
</template>

<script>
import { mapMutations, mapGetters, mapActions } from "vuex";
const noticeStore = "noticeStore";
const memberStore = "memberStore";

export default {
  name: "NoticeList",
  data() {
    return {
      noticeList: [],
      pgInfo: {},
      selectNo: "",
    };
  },
  created() {
    let param = {
      pageNo: this.getPgInfo().pageNum,
      listSize: 10,
    };
    this.noticeList = this.setNoticeList(param);
    this.pgInfo = this.getPgInfo();
    this.selectNo = this.getPgInfo().pageNum;
  },
  methods: {
    ...mapGetters(noticeStore, ["getNoticeList", "getNotice", "getPgInfo"]),
    ...mapGetters(memberStore, ["getLoginMember"]),
    ...mapActions(noticeStore, ["setNoticeList", "setNotice"]),
    ...mapMutations(memberStore, ["EDIT_LAST_PAGE_NO"]),
    async view(articleNo) {
      this.setNotice(articleNo);
      setTimeout(() => {
        this.$emit("notice-view");
      }, 100);
    },
    BoardWrite() {
      this.EDIT_LAST_PAGE_NO(1);
      this.$emit("board-write");
    },
    async getList(pgNo) {
      this.selectNo = pgNo;
      let param = {
        pageNo: pgNo,
        listSize: 10,
      };
      this.setNoticeList(param);
    },
  },
  mounted() {},
  computed: {
    detectAdmin() {
      return this.getLoginMember().grade == 9;
    },
    initList() {
      let param = {
        pageNo: 1,
        listSize: 10,
      };
      return this.getNoticeList(param);
    },
    initPgInfo() {
      let tempPgInfo = this.getPgInfo();
      this.selectNo = tempPgInfo.pageNum;
      return tempPgInfo;
    },
  },
};
</script>

<style>
@import url("../../assets/css/common.css");
@import url("../../assets/css/board-list.css");
</style>
