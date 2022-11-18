<template>
  <div>
    <div class="user-inquiry-container" id="user-inquiry-div" style="display: none">
      <p class="user-inquiry-title">회원정보 찾기</p>
      <p>회원 정보를 잊으셨나요?</p>
      <hr class="user-inquiry-hr" />
      <p>회원 가입시 등록한 이메일을 입력해주세요.</p>
      <form>
        <div class="email-conatiner">
          <input type="email" class="email-input default-input" placeholder="이메일을 입력하세요" />
        </div>
        <div class="button-conatiner">
          <button class="defbtn default-input send-email-btn">이메일로 회원 정보 수신</button>
        </div>
      </form>
      <div class="home-container">
        <a href="#" class="home">
          <i id="inq-bite-cookie1" class="fa-solid fa-cookie-bite animate__animated" @click="countCookie"></i>
        </a>
      </div>
      <div class="background" id="background" style="display: none"></div>
    </div>
    <div id="background3" style="display: none" @click="closeInquiryModal"></div>
  </div>
</template>

<script>
export default {
  name: "AppUserInquiry",
  mounted() {},
  data() {
    return {};
  },
  methods: {
    closeInquiryModal() {
      document.getElementById("user-inquiry-div").setAttribute("style", "display: none");
      document.getElementById("background3").setAttribute("style", "display: none");
    },
    countCookie() {
      if (!document.getElementById("inq-bite-cookie1").classList.contains("animate__jello")) {
        document.getElementById("inq-bite-cookie1").classList.add("animate__heartBeat", "colorChange");
        setTimeout(() => {
          document.getElementById("inq-bite-cookie1").classList.remove("animate__heartBeat", "colorChange");
        }, 1000);
      }
    },
  },
};
</script>

<style>
@import url("../assets/css/common.css");
@import url("../assets/css/user-inquiry.css");
</style>
