<template>
  <div>
    <div class="regist-container" id="regist-modal">
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
          <input
            type="text"
            class="id-input default-input"
            name="userId"
            placeholder="아이디"
            v-model="newUser.userId"
          />
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
            v-model="confrimPassword"
          />
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
          <input type="button" class="defbtn default-input regist-btn" value="회원가입" @click="registUser" />
        </div>
      </form>
    </div>
    <div class="background" id="background" style="display: none"></div>
  </div>
</template>

<script>
export default {
  name: "RegistForm",
  mounted() {},
};
</script>

<style>
@import url("../../assets/css/common.css");
@import url("../../assets/css/regist-form.css");
</style>
