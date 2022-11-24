<template>
  <div>
    <!-- 글쓰기 뷰 영역-->
    <hr />
    <div class="write-form-container">
      <div class="left-menu">
        <a href="#" class="defbtn btn-back" @click="backToList">뒤로가기</a>
      </div>
      <form action="/board/notice/write" method="post">
        <table class="write-table">
          <tr>
            <th class="category">구분</th>
            <td class="input-item">
              <select
                id="board-division-select"
                class="division division-select"
                name="div"
                v-model="division"
                @change="getValue"
              >
                <option v-for="(div, index) in divisionList" :key="index" :value="div.value">{{ div.text }}</option>
              </select>
            </td>
          </tr>
          <tr>
            <th class="category">제목</th>
            <td class="input-item">
              <input type="text" class="title-input default-input" name="title" v-model="newBoard.title" />
            </td>
          </tr>
          <tr>
            <th class="category">내용</th>
            <td class="input-item content-item">
              <textarea class="content content-input default-input" name="content" v-model="newBoard.content">
              </textarea>
            </td>
          </tr>
          <tr>
            <th class="category">파일첨부</th>
            <td class="input-item">
              <input type="file" multiple="multiple" class="file file-input" />
            </td>
          </tr>
        </table>
        <button id="write-btn" class="write-btn btn btn-success form-control" type="button" @click="createArticle">
          글쓰기
        </button>
      </form>
    </div>
    <!-- 글쓰기 뷰 영역-->
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
const boardStore = "boardStore";
const noticeStore = "noticeStore";
const memberStore = "memberStore";

export default {
  name: "BoardWrite",
  data() {
    return {
      divisionList: [
        { text: "선택하세요", value: "0" },
        { text: "공지사항", value: "1" },
        { text: "자유게시판", value: "2" },
      ],
      division: "0",
      newBoard: {
        userId: "",
      },
      pastListNo: 1,
    };
  },
  methods: {
    ...mapActions(boardStore, ["addBoard"]),
    ...mapActions(noticeStore, ["addNotice"]),
    getValue() {
      //console.log(this.division);
    },
    createArticle() {
      if (this.division == "1") {
        this.makeNotice();
      } else if (this.division == "2") {
        this.makeBoard();
      }
    },
    async makeBoard() {
      this.newBoard.userId = this.getLoginMember.id;
      this.addBoard(this.newBoard);
      this.backToList();
    },
    async makeNotice() {
      this.newBoard.userId = this.getLoginMember.id;
      this.addNotice(this.newBoard);
      this.backToList();
    },
    async backToList() {
      setTimeout(() => {
        //        console.log(this.pastListNo);
        if (this.pastListNo == "1") {
          this.$emit("notice-list");
        } else if (this.pastListNo == "2") {
          this.$emit("board-list");
        }
      }, 100);
    },
  },
  created() {
    // console.log(this.getLoginMember.id);
    this.pastListNo = this.getPastListNo;
  },
  mounted() {
    if (!this.detectAdmin) {
      this.divisionList.splice(1, 1);
    }
  },
  computed: {
    ...mapGetters(memberStore, ["getLoginMember", "getPastListNo"]),

    detectAdmin() {
      return this.getLoginMember.grade == 9;
    },
  },
};
</script>

<style>
@import url("../../assets/css/common.css");
@import url("../../assets/css/board-write.css");
/* 뒤로가기 버튼 부분 css만 가져옴 */
.btn-back {
  display: block;
  width: 70px;
  height: 40px;
  border-radius: 10px;
  line-height: 40px;
  text-align: center;
  background-color: lightgray;
  color: white;
  margin-bottom: 30px;
}
</style>
