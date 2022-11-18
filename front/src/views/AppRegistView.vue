<template>
  <div>
    <div class="regist-container" id="regist-modal-form" style="display: none">
      <p class="regist-title">회원가입</p>
      <hr class="regist-hr" />
      <div class="regist-img-container">
        <a href="#">
          <i class="fa-solid fa-cookie-bite"></i>
        </a>
      </div>
      <form class="id-form" action="#" method="post">
        <div class="name">
          <input type="text" class="default-input" name="name" placeholder="이름" v-model="newUser.name" />
        </div>
        <div class="id">
          <input type="text" class="id-input default-input" name="id" placeholder="아이디" v-model="newUser.id" />
        </div>
        <div class="password">
          <input
            type="password"
            class="password-input default-input"
            name="password"
            placeholder="비밀번호"
            v-model="newUser.password"
          />
        </div>
        <div class="password password-confirm">
          <input
            type="password"
            class="password-input default-input"
            name="passwordConfirm"
            placeholder="비밀번호 확인"
            v-model="confirmPassword"
            @keyup="checkIsSamePassWord"
          />
        </div>
        <div class="confirm-password-text" id="confirm-password-text">
          <p class=""></p>
        </div>
        <div class="email">
          <input
            type="text"
            class="default-input email-item"
            name="email"
            placeholder="이메일"
            v-model="newUser.email"
          />
          <span class="email-span">@</span>
          <input
            type="text"
            class="default-input email-item"
            name="domain"
            placeholder="도메인"
            v-model="newUser.domain"
          />
        </div>
        <div class="age">
          <input type="age" class="default-input" name="userId" placeholder="나이" v-model="newUser.age" />
        </div>

        <div class="button-div">
          <input type="button" class="defbtn default-input regist-btn" value="회원가입" @click="doRegistUser" />
        </div>
      </form>
    </div>
    <div id="background2" style="display: none" @click="closeRegistModal"></div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "AppRegistView",
  data() {
    return {
      newUser: {},
      confirmPassword: "",
    };
  },
  methods: {
    goIndex() {
      this.$router.push({ name: "Index" }).catch(() => {});
    },
    async doRegistUser() {
      if (this.newUser.password == this.confirmPassword) {
        await axios({
          url: "http://localhost:8080/member/signup",
          method: "post",
          data: this.newUser,
        })
          .then((res) => {
            if (res.status == "200") {
              alert("회원가입에 성공하였습니다!");
              this.closeRegistModal();
              this.clearUserInfo();
            }
          })
          .catch((err) => {
            alert("회원 가입에 실패하였습니다. 다시 시도해주세요.");
          });
      } else {
        alert("비밀번호가 일치하지 않습니다 올바르게 입력해주세요.");
      }
    },
    clearUserInfo() {
      this.newUser = {};
      this.confirmPassword = "";
      document.getElementById("confirm-password-text").childNodes[0].textContent = "";
    },
    checkIsSamePassWord() {
      if (this.newUser.password == this.confirmPassword) {
        document.getElementById("confirm-password-text").childNodes[0].classList.remove("wrong");
        document.getElementById("confirm-password-text").childNodes[0].classList.add("ok");
        document.getElementById("confirm-password-text").childNodes[0].textContent = "비밀번호가 일치합니다.";
      } else {
        document.getElementById("confirm-password-text").childNodes[0].classList.remove("ok");
        document.getElementById("confirm-password-text").childNodes[0].classList.add("wrong");
        document.getElementById("confirm-password-text").childNodes[0].textContent = "비밀번호가 일치하지 않습니다.";
      }
    },
    closeRegistModal() {
      document.getElementById("regist-modal-form").setAttribute("style", "display: none");
      document.getElementById("background2").setAttribute("style", "display: none");
    },
  },
  mounted() {},
};
</script>

<style>
@import url("../assets/css/common.css");
@import url("../assets/css/regist-form.css");
</style>
