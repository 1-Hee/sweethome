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
        <a href="#" class="home"><i class="fa-solid fa-cookie-bite"></i></a>
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
  methods: {
    closeInquiryModal() {
      document.getElementById("user-inquiry-div").setAttribute("style", "display: none");
      document.getElementById("background3").setAttribute("style", "display: none");
    },
  },
};
</script>

<style>
@import url("../assets/css/common.css");
@import url("../assets/css/user-inquiry.css");
</style>
