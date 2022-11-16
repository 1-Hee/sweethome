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
                <li>
                  <i class="fa-solid fa-list-ul"></i>
                  <a class="drop-down-item" href="#" @click="noticeList">공지 사항</a>
                </li>
                <li>
                  <i class="fa-solid fa-align-justify"></i>
                  <a class="drop-down-item" href="#" @click="boardList">자유게시판</a>
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
            <a href="#" class="">이달의 부동산 매물</a>
          </li>
          <li class="head-menu">
            <a href="#" class="">부동산 Stock</a>
          </li>
        </ul>
        <ul class="nav-ul">
          <li class="head-menu" v-show="checkUser"><a class="login-btn" id="do-login-btn">로그인</a></li>
          <li v-show="!checkUser" class="head-menu user-greet b-hover">
            <span
              ><span>{{ getName }}</span> 님 안녕하세요</span
            >
          </li>
          <li v-show="!checkUser" class="head-menu b-hover" id="profile-li">
            <div class="drop-down-menu">
              <button class="defbtn drop-down-btn">
                <img />
              </button>
              <ul class="drop-down-items" id="profile-menu">
                <li>
                  <i class="fa-regular fa-circle-user"></i>
                  <a class="drop-down-item" href="#" @click="myPage">마이 페이지</a>
                </li>
                <li>
                  <i class="fa-solid fa-heart"></i>
                  <a class="drop-down-item" href="#">찜목록</a>
                </li>
                <li>
                  <i class="fa-solid fa-right-from-bracket"></i>
                  <a class="drop-down-item" href="#" @click="logOut">로그아웃</a>
                </li>
              </ul>
            </div>
          </li>
        </ul>
      </div>
    </header>
    <div class="divider-min"></div>
    <app-login-view></app-login-view>
    <app-regist-view></app-regist-view>
    <app-user-inquiry></app-user-inquiry>

    <!-- 헤더 영역 -->
  </div>
</template>

<script>
import header from "@/assets/js/header";
import loginForm from "@/assets/js/login-form";

import AppLoginView from "@/views/AppLoginView";
import AppRegistView from "@/views/AppRegistView";
import AppUserInquiry from "@/views/AppUserInquiry";

export default {
  name: "TheHeaderView",
  data() {
    return {
      isLogin: false,
    };
  },
  methods: {
    goIndex() {
      this.$router.push({ name: "Index" }).catch(() => {});
    },
    noticeList() {
      this.$router.push({ name: "NoticeList" }).catch(() => {});
    },
    boardList() {
      this.$router.push({ name: "BoardList" }).catch(() => {});
    },
    myPage() {
      this.$router.push({ name: "MyPage" }).catch(() => {});
    },
    goMap() {
      this.$router.push({ name: "MapView" }).catch(() => {});
    },
    logOut() {
      this.isLogin = false;
      this.$store.state.loginUser = null;
      this.goIndex();
    },
  },
  components: {
    AppLoginView,
    AppRegistView,
    AppUserInquiry,
  },
  mounted() {
    this.$store.state.loginUser = null;
    loginForm.init();
    header.setLoginModal();
  },
  computed: {
    getName() {
      if (this.isLogin) {
        return "";
      } else {
        return this.$store.state.loginUser.name;
      }
    },
    checkUser() {
      this.isLogin = this.$store.state.loginUser == null;
      if (this.isLogin) {
        header.init();
      }
      return this.$store.state.loginUser == null;
    },
  },
  watch: {},
};
</script>

<style>
@import url("../../assets/css/common.css");
@import url("../../assets/css/header.css");
</style>
