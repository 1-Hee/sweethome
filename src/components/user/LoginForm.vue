<template>
  <div>
    <div class="login-container" id="login-modal">
      <p class="login-title">로그인</p>
      <hr class="login-hr" />
      <div class="login-img-container">
        <a href="#">
          <i class="fa-solid fa-cookie-bite"></i>
        </a>
      </div>
      <form class="id-form" action="#" method="post">
        <div class="id">
          <input type="text" class="id-input default-input" name="userId" placeholder="아이디" v-model="User.userId" />
        </div>
        <div class="password">
          <input
            type="password"
            class="password-input default-input"
            name="password"
            placeholder="비밀번호"
            v-model="User.password"
          />
        </div>
        <div class="button-div">
          <input type="button" class="defbtn default-input login-btn" @click="verifyUser" value="로그인" />
        </div>
      </form>
      <div class="mv-regist-container flex-even">
        <a href="#" class="item">회원가입</a>
        <span>|</span>
        <a href="#" class="item">계정을 잊으셨나요?</a>
      </div>
    </div>
    <div class="background" id="background" style="display: none"></div>
  </div>
</template>

<script>
export default {
  name: "LoginForm",
  mounted() {},
};
</script>

<style>
@import url("../../assets/css/common.css");
@import url("../../assets/css/login-form.css");
</style>
