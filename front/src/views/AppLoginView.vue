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
            @click="dologinUser"
          />
        </div>
      </form>
      <div class="mv-regist-container flex-even">
        <a href="#" class="item" id="do-regist-btn">회원가입</a>
        <span>|</span>
        <a href="#" class="item" id="do-find-user-btn">계정을 잊으셨나요?</a>
      </div>
    </div>
    <div id="background1" style="display: none"></div>
  </div>
</template>

<script>
import loginForm from "@/assets/js/login-form";
import axios from "axios";

export default {
  name: "AppLoginView",
  data() {
    return {
      loginUser: {},
    };
  },
  methods: {
    dologinUser() {
      axios({
        url: `http://localhost:8080/member/login`,
        method: "post",
        data: this.loginUser,
      }).then(({ data }) => {
        // console.dir(data);
        this.$store.state.loginUser = data;
        this.loginUser = {};
      });
      this.closeLoginModal();
    },
    closeLoginModal() {
      document.getElementById("login-modal-form").setAttribute("style", "display: none");
      document.getElementById("background1").setAttribute("style", "display: none");
    },
    spinIcon() {
      if (!document.getElementById("cookie-logo").classList.contains("rotate")) {
        document.getElementById("cookie-logo").classList.add("rotate");
        setTimeout(() => {
          document.getElementById("cookie-logo").classList.remove("rotate");
        }, 2000);
      }
    },
  },
  mounted() {
    loginForm.init();
  },
};
</script>

<style>
@import url("../assets/css/common.css");
@import url("../assets/css/login-form.css");
</style>
