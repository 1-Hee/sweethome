<template>
  <div>
    <!-- 사용자 정보 영역 -->
    <hr />
    <div class="user-info-conatiner">
      <div class="user-info-head">
        <h3>프로필 수정</h3>
        <div class="user-info-text-line">
          <p>마이홈 대표 프로필과 별명을 수정할 수 있습니다.</p>
          <button id="withdrawal" class="withdrawal" @click="doWithdrawal">회원탈퇴</button>
        </div>
      </div>
      <hr />
      <div class="user-info-box">
        <ul>
          <li>
            <div class="sort">프로필</div>
            <div>
              <img src="@/assets/img/user-sample.jpg" />
            </div>
            <button id="modishow" class="modify-btn">변경하기</button>
          </li>
          <li>
            <div class="sort">사용자 정보</div>
            <div class="user-info">
              <div>
                ID : <span> {{ loginUser.id }} </span>
              </div>
              <div>
                이름 : <span> {{ loginUser.name }} </span>
              </div>
              <div>
                나이 : <span> {{ loginUser.age }} </span>
              </div>
              <div>
                이메일 : <span> {{ loginUser.email }} @ {{ loginUser.domain }} </span>
              </div>
              <div>
                등급 :
                <span v-if="loginUser.grade == 1">일반 사용자</span>
                <span v-else-if="loginUser.grade == 9">관리자</span>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>

    <!-- 모달창 -->
    <div id="filter" class="background" style="display: none"></div>
    <div id="modal" class="modi-info-modal" style="display: none">
      <div class="modi-title">
        <p>회원정보 수정</p>
        <button id="modi-close-btn" class="modi-close-btn">X</button>
      </div>

      <form action="#" method="#" enctype="multipart/form-data">
        <div class="user-modi-info user-img">
          <img src="@/assets/img/user-sample.jpg" />
          <label for="file" class="upload-btn">업로드</label>
          <input id="file" style="display: none" type="file" multiple />
        </div>
        <div class="user-modi-info">
          <label for="uid">ID</label>
          <input id="uid" type="text" readonly disabled :value="loginUser.id" />
        </div>
        <div class="user-modi-info">
          <label for="uname">이름 </label>
          <input id="uname" type="text" value="김싸피" name="name" v-model="loginUser.name" />
        </div>
        <div class="user-modi-info">
          <label for="age">나이</label>
          <input id="age" type="number" value="23" name="age" v-model="loginUser.age" />
        </div>
        <div class="user-modi-info modi-email-div">
          <label for="email">이메일</label>
          <input id="email" type="text" value="ssafy1" name="email" v-model="loginUser.email" />
          <span class="email-span"> @ </span>
          <input type="text" value="ssafy.com" name="domain" v-model="loginUser.domain" />
        </div>
        <div class="user-modi-info">
          <label for="password">비밀번호</label>
          <input id="password" type="password" name="password" v-model="newPassword" />
        </div>
        <div class="submit-container">
          <button class="modify-btn" type="button" @click="modifyUser">수정하기</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
// user-info js 적용 필요.
import modal from "@/assets/js/modal";
import axios from "axios";

export default {
  name: "UserInfo",
  data() {
    return {
      loginUser: {},
      newPassword: "",
    };
  },
  mounted() {
    modal.init();
    this.loginUser = this.$store.state.loginUser;
  },
  computed: {
    getUser() {
      this.loginUser = this.$store.state.loginUser;
    },
  },
  methods: {
    async doWithdrawal() {
      let user = this.$store.state.loginUser;
      await axios({
        url: `http://localhost:8080/member/delete/${user.id}`,
        method: "delete",
        data: this.loginUser,
      })
        .then((res) => {
          // console.log(res);
          alert("회원 탈퇴가 완료되었습니다.");
          this.$store.state.loginUser = {};
          this.loginUser = {};
          this.goIndex();
        })
        .catch((err) => {
          alert("회원 탈퇴 중 오류가 발생하였습니다.");
        });
    },
    async modifyUser() {
      if (this.newPassword != "") {
        this.loginUser.password = this.newPassword;
      }
      await axios({
        url: "http://localhost:8080/member/update",
        method: "put",
        data: this.loginUser,
      })
        .then((res) => {
          // console.log(res);
          alert("회원정보 수정이 완료되었습니다.");
        })
        .catch((err) => {
          alert("회원정보 수정 중 오류가 발생하였습니다.");
        });

      this.closeUserInfoModal();
    },
    closeUserInfoModal() {
      document.getElementById("modal").setAttribute("style", "display: none;");
      document.getElementById("filter").setAttribute("style", "display: none;");
    },
    goIndex() {
      this.$emit("go-index");
    },
  },
};
</script>

<style>
@import url("../../assets/css/common.css");
@import url("../../assets/css/user-info.css");
</style>
