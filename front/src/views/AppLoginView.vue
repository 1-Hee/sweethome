<template>
  <div>
    <div class="login-container" id="login-modal-form" style="display: none">
      <p class="login-title">로그인</p>
      <hr class="login-hr" />
      <div class="login-img-container">
        <a href="#" @click="spinIcon">
          <i id="cookie-logo" class="fa-solid fa-cookie-bite"></i>
        </a>
      </div>
      <form class="id-form" action="#" method="post">
        <div class="id">
          <input type="text" class="id-input default-input" name="id" placeholder="아이디" v-model="loginUser.id" />
        </div>
        <div class="password">
          <input
            type="password"
            class="password-input default-input"
            name="password"
            placeholder="비밀번호"
            v-model="loginUser.password"
          />
        </div>
        <div class="button-div">
          <input
            type="button"
            id="do-login-btn"
            class="defbtn default-input login-btn"
            value="로그인"
            @click="Login"
            @keyup.enter="Login"
          />
        </div>
      </form>
      <div class="mv-regist-container flex-even">
        <a href="#" class="item" id="do-regist-btn" @click="showRegistModal">회원가입</a>
        <span>|</span>
        <a href="#" class="item" id="do-find-user-btn" @click="showFindUserInfo">계정을 잊으셨나요?</a>
      </div>
    </div>
    <div id="background1" style="display: none"></div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
const memberStore = "memberStore";

export default {
  name: "AppLoginView",
  data() {
    return {
      loginUser: {},
    };
  },
  methods: {
    ...mapGetters(memberStore, ["getLoginMember", "getToken"]),
    ...mapActions(memberStore, ["doLoginMember"]),
    clearUserInfo() {
      this.loginUser = {};
    },
    async Login() {
      await this.doLoginMember(this.loginUser);
      this.clearUserInfo();
      setTimeout(() => {
        if (this.getToken() == null) {
          alert("로그인에 실패하였습니다. 다시 시도해주세요.");
        } else {
          this.closeLoginModal();
        }
      }, 100);
    },
    closeLoginModal() {
      document.getElementById("login-modal-form").setAttribute("style", "display: none");
      document.getElementById("background1").setAttribute("style", "display: none");
    },
    showRegistModal(e) {
      document.getElementById("regist-modal-form").setAttribute("style", "display: block");
      document.getElementById("background2").setAttribute("style", "display: block");
      document.getElementById("login-modal-form").setAttribute("style", "display: none");
      document.getElementById("background1").setAttribute("style", "display: none");
      this.clearUserInfo();
    },
    showFindUserInfo(e) {
      document.getElementById("user-inquiry-div").setAttribute("style", "display: block");
      document.getElementById("background3").setAttribute("style", "display: block");
      document.getElementById("login-modal-form").setAttribute("style", "display: none");
      document.getElementById("background1").setAttribute("style", "display: none");
      this.clearUserInfo();
    },
    spinIcon() {
      if (!document.getElementById("cookie-logo").classList.contains("rotate")) {
        document.getElementById("cookie-logo").classList.add("rotate");
        setTimeout(() => {
          document.getElementById("cookie-logo").classList.remove("rotate");
        }, 1500);
      }
    },
  },
  mounted() {},
  computed: {
    getUser() {
      return this.getLoginMember();
    },
  },
};
</script>

<style>
@import url("../assets/css/common.css");
@import url("../assets/css/login-form.css");
</style>
