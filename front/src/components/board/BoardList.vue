<template>
  <div>
    <!-- 공지사항 리스트 영역-->
    <hr />
    <div class="table-container">
      <div class="board-write-container">
        <button class="board-write-btn" @click="BoardWrite">글쓰기</button>
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
          <tr v-for="(item, index) in boardList" :key="index" @click="view(item.articleNo)">
            <td>{{ index + 1 }}</td>
            <td>{{ item.title }}</td>
            <td>{{ item.userId }}</td>
            <td>{{ item.hit }}</td>
            <td>{{ item.registDate }}</td>
          </tr>
        </tbody>
      </table>
      <ul class="page-tab">
        <li class="pg-first"><a href="#">◀</a></li>
        <li class="pg-common select"><a href="#">1</a></li>
        <li class="pg-common"><a href="#">2</a></li>
        <li class="pg-common"><a href="#">3</a></li>
        <li class="pg-common"><a href="#">4</a></li>
        <li class="pg-common"><a href="#">5</a></li>
        <li class="pg-last"><a href="#">▶</a></li>
      </ul>
    </div>
    <!-- 공지사항 리스트 영역-->
  </div>
</template>

<script>
import boardList from "@/assets/js/board-list";
import { getBoardList, getBoard } from "@/api/board";

export default {
  name: "BoardList",
  data() {
    return {
      boardList: [],
    };
  },
  created() {
    boardList.init();
    let param = {
      pageNo: 1,
      listSize: 10,
    };
    getBoardList(
      param,
      ({ data }) => {
        this.boardList = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    async view(articleNo) {
      await getArticle(
        articleNo,
        ({ data }) => {
          localStorage.setItem("board", JSON.stringify(data)); // 이부분 store(vuex) 로 바꿀 것.
        },
        (err) => {
          console.log(err);
        }
      );
      await this.$emit("board-view");
    },
    BoardWrite() {
      this.$emit("board-write");
    },
  },
  mounted() {},
};
</script>

<style>
@import url("../../assets/css/common.css");
@import url("../../assets/css/board-list.css");
</style>
