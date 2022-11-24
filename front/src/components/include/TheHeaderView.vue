<template>
  <div>
    <!-- 헤더 영역 -->
    <header class="header">
      <div class="main-logo-container">
        <i class="fa-solid fa-cookie-bite"></i>
        <a href="#" @click="goIndex" class="main-title">SWEET HOME</a>
      </div>
      <div class="menu-container">
        <ul class="nav-ul">
          <li class="head-menu">
            <div class="drop-down-menu">
              <button class="defbtn drop-down-btn">전체</button>
              <ul class="drop-down-items" id="board-menu">
                <li>
                  <i class="fa-solid fa-building"></i>
                  <a class="drop-down-item" href="#">회사 소개</a>
                </li>
                <li @click="detectLogin(noticeList)">
                  <i class="fa-solid fa-list-ul"></i>
                  <a class="drop-down-item" href="#">공지 사항</a>
                </li>
                <li @click="detectLogin(boardList)">
                  <i class="fa-solid fa-align-justify"></i>
                  <a class="drop-down-item" href="#">자유게시판</a>
                </li>
                <li>
                  <i class="fa-brands fa-youtube"></i>
                  <a class="drop-down-item" href="https://www.youtube.com/watch?v=ahgLgfGCZPs">부동산채널</a>
                </li>
              </ul>
            </div>
          </li>
          <li class="head-menu">
            <a href="#" class="" @click="goMap">부동산 매물 조회</a>
          </li>
          <li class="head-menu">
            <a href="#" class="" @click="go404">이달의 부동산 매물</a>
          </li>
          <li class="head-menu">
            <a href="#" class="" @click="go500">부동산 Stock</a>
          </li>
        </ul>
        <ul class="nav-ul">
          <li class="head-menu" v-if="getLoginMember == null" @click="showLoginModal">
            <a class="login-btn" id="do-login-btn">로그인</a>
          </li>
          <li v-if="getLoginMember != null" class="head-menu user-greet b-hover">
            <span
              ><span>{{ getLoginMember.name }}</span> 님 안녕하세요</span
            >
          </li>
          <li
            v-if="getLoginMember != null"
            class="head-menu b-hover"
            id="profile-li"
            @mouseenter="showProfileMenu"
            @mouseleave="hideProfileMenu"
          >
            <div class="drop-down-menu">
              <button class="defbtn drop-down-btn">
                <img
                  id="user-img-header"
                  :src="CURL + '/' + getLoginMember.saveFolder + '/' + getLoginMember.saveFile"
                />
              </button>
              <ul class="drop-down-items" id="profile-menu">
                <li>
                  <i class="fa-regular fa-circle-user"></i>
                  <a class="drop-down-item" href="#" @click="detectLogin(myPage)">마이 페이지</a>
                </li>
                <li>
                  <i class="fa-solid fa-heart"></i>
                  <a class="drop-down-item" href="#" @click="goLike">찜목록</a>
                </li>
                <li @click="logOut">
                  <i class="fa-solid fa-right-from-bracket"></i>
                  <a class="drop-down-item" href="#">로그아웃</a>
                </li>
              </ul>
            </div>
          </li>
        </ul>
      </div>
    </header>
    <div class="divider-min" style="background-color: white; z-index: 3"></div>
    <app-login-view></app-login-view>
    <app-regist-view></app-regist-view>
    <app-user-inquiry></app-user-inquiry>
    <div class="wating-bg hide" id="wating-bg"></div>
    <i class="fa-solid fa-circle-notch wating-icon hide" id="waiting-circle"></i>

    <!-- 헤더 영역 -->
  </div>
</template>

<script>
import header from "@/assets/js/header";
import loginForm from "@/assets/js/login-form";

import AppLoginView from "@/views/AppLoginView";
import AppRegistView from "@/views/AppRegistView";
import AppUserInquiry from "@/views/AppUserInquiry";

import { mapActions, mapGetters, mapMutations } from "vuex";
const memberStore = "memberStore";

export default {
  name: "TheHeaderView",
  data() {
    return {
      loginUser: {},
      code: 9,
      isTokenError: false,
      CURL: "C:/SSAFY8/workspace/08-1.VueProject/sweethome/back/BootSaveHome/src/main/webapp/upload/",
    };
  },
  methods: {
    ...mapActions(memberStore, ["checkMemberById"]),
    ...mapMutations(memberStore, ["REMOVE_MEMBER"]),
    showLoginModal() {
      document.getElementById("login-modal-form").setAttribute("style", "display: block;");
      document.getElementById("background1").setAttribute("style", "display: block;");
    },
    goIndex() {
      this.$router.push({ name: "Index" }).catch(() => {});
    },
    noticeList() {
      this.$router.push({ name: "NoticeList" }).catch(() => {});
    },
    boardList() {
      setTimeout(() => {
        this.$router.push({ name: "BoardList" }).catch(() => {});
      }, 100);
    },
    myPage() {
      this.$router.push({ name: "MyPage" }).catch(() => {});
    },
    goMap() {
      this.$router.push({ name: "MapView" }).catch(() => {});
    },
    go404() {
      this.$router.push({ name: "App404Error" }).catch(() => {});
    },
    go500() {
      this.$router.push({ name: "App500Error" }).catch(() => {});
    },
    goLike() {
      this.$router.push({ name: "UserLikeApt" }).catch(() => {});
    },
    async logOut() {
      this.REMOVE_MEMBER();
      this.goIndex();
    },
    showProfileMenu(e) {
      document.getElementById("profile-menu").setAttribute("style", "display:block;");
    },
    hideProfileMenu(e) {
      document.getElementById("profile-menu").setAttribute("style", "display:none;");
    },
    async detectLogin(methods) {
      let token = sessionStorage.getItem("access-token");
      // console.log(actoken);
      // console.log(rftoken);
      this.checkMemberById(token);

      setTimeout(() => {
        // console.log(this.getTokenError);
        if (this.getTokenError || token == null) {
          this.showLoginModal();
          alert("로그인 이후 이용할 수 있는 기능입니다.");
          this.REMOVE_MEMBER();
        } else {
          //console.log(this.getTokenError);
          methods();
        }
      }, 100);
    },
  },
  components: {
    AppLoginView,
    AppRegistView,
    AppUserInquiry,
  },
  created() {
    // this.loginUser = this.getLoginMember;
    this.isTokenError = this.getTokenError;
  },
  mounted() {
    // this.$store.state.loginUser = null;
    loginForm.init();
    header.allMenuInit();
  },
  computed: {
    ...mapGetters(memberStore, ["getLoginMember", "clearMemberInfo", "getTokenError"]),
    // getUser() {
    //   return this.getLoginMember();
    // },
  },
  // watch: {
  //   isTokenError: function (value, oldValue) {
  //     console.log(value, oldValue);
  //   },
  // },
  updated() {},
};
</script>

<style>
@import url("../../assets/css/common.css");
@import url("../../assets/css/header.css");
</style>
