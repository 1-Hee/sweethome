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
            <a href="#" class="">이달의 부동산 매물</a>
          </li>
          <li class="head-menu">
            <a href="#" class="">부동산 Stock</a>
          </li>
        </ul>
        <ul class="nav-ul">
          <li class="head-menu" v-if="getUser == null" @click="showLoginModal">
            <a class="login-btn" id="do-login-btn">로그인</a>
          </li>
          <li v-if="getUser != null" class="head-menu user-greet b-hover">
            <span
              ><span>{{ getUser.name }}</span> 님 안녕하세요</span
            >
          </li>
          <li
            v-if="getUser != null"
            class="head-menu b-hover"
            id="profile-li"
            @mouseenter="showProfileMenu"
            @mouseleave="hideProfileMenu"
          >
            <div class="drop-down-menu">
              <button class="defbtn drop-down-btn">
                <img />
              </button>
              <ul class="drop-down-items" id="profile-menu">
                <li>
                  <i class="fa-regular fa-circle-user"></i>
                  <a class="drop-down-item" href="#" @click="detectLogin(myPage)">마이 페이지</a>
                </li>
                <li>
                  <i class="fa-solid fa-heart"></i>
                  <a class="drop-down-item" href="#">찜목록</a>
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

import { mapGetters } from "vuex";
const memberStore = "memberStore";

export default {
  name: "TheHeaderView",
  data() {
    return {
      loginUser: {},
      code: 9,
    };
  },
  methods: {
    ...mapGetters(memberStore, ["getLoginMember", "clearMemberInfo"]),
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
    async logOut() {
      this.clearMemberInfo();
      this.goIndex();
      location.reload();
    },
    showProfileMenu(e) {
      document.getElementById("profile-menu").setAttribute("style", "display:block;");
    },
    hideProfileMenu(e) {
      document.getElementById("profile-menu").setAttribute("style", "display:none;");
    },
    detectLogin(methods) {
      if (this.getUser == null) {
        this.showLoginModal();
        alert("로그인 이후 이용할 수 있는 기능입니다.");
      } else {
        methods();
      }
    },
  },
  components: {
    AppLoginView,
    AppRegistView,
    AppUserInquiry,
  },
  created() {
    this.loginUser = this.getLoginMember();
  },
  mounted() {
    // this.$store.state.loginUser = null;
    loginForm.init();
    header.allMenuInit();
  },
  computed: {
    getUser() {
      return this.getLoginMember();
    },
  },
  watch: {},
};
</script>

<style>
@import url("../../assets/css/common.css");
@import url("../../assets/css/header.css");
</style>
