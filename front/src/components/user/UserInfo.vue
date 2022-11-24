<template>
  <div>
    <!-- 사용자 정보 영역 -->
    <hr />
    <div class="user-info-conatiner">
      <div class="user-info-head">
        <h3>프로필 수정</h3>
        <div class="user-info-text-line">
          <p>마이홈 대표 프로필과 별명을 수정할 수 있습니다.</p>
          <button id="withdrawal" class="withdrawal" @click="eraseMember">회원탈퇴</button>
        </div>
      </div>
      <hr />
      <div class="user-info-box">
        <ul>
          <li>
            <div class="sort">프로필</div>
            <div>
              <img
                :src="this.getProfileURL"
                onerror="this.src='https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png'"
              />
            </div>
            <button id="modishow" class="modify-btn">변경하기</button>
          </li>
          <li>
            <div class="sort">사용자 정보</div>
            <div class="user-info">
              <div>
                ID : <span> {{ loginUser.id }} </span>
              </div>
              <div>
                이름 : <span> {{ loginUser.name }} </span>
              </div>
              <div>
                나이 : <span> {{ loginUser.age }} </span>
              </div>
              <div>
                이메일 : <span> {{ loginUser.email }} @ {{ loginUser.domain }} </span>
              </div>
              <div>
                등급 :
                <span v-if="loginUser.grade == 1">일반 사용자</span>
                <span v-else-if="loginUser.grade == 9">관리자</span>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>

    <!-- 모달창 -->
    <div id="filter" class="background" style="display: none" @click="closeUserInfoModal"></div>
    <div id="modal" class="modi-info-modal" style="display: none">
      <div class="modi-title">
        <p>회원정보 수정</p>
        <button id="modi-close-btn" class="modi-close-btn">X</button>
      </div>

      <form enctype="multipart/form-data">
        <div class="user-modi-info user-img">
          <img
            id="modal-img"
            :src="this.getProfileURL"
            onerror="this.src='https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png'"
          />
          <label for="file" class="upload-btn">업로드</label>
          <input id="file" style="display: none" type="file" accept="image/*" @change="onFileChange" />
        </div>
        <div class="user-modi-info">
          <label for="uid">ID</label>
          <input id="uid" type="text" readonly disabled :value="loginUser.id" />
        </div>
        <div class="user-modi-info">
          <label for="uname">이름 </label>
          <input id="uname" type="text" value="김싸피" name="name" v-model="loginUser.name" />
        </div>
        <div class="user-modi-info">
          <label for="age">나이</label>
          <input id="age" type="number" value="23" name="age" v-model="loginUser.age" />
        </div>
        <div class="user-modi-info modi-email-div">
          <label for="email">이메일</label>
          <input id="email" type="text" value="ssafy1" name="email" v-model="loginUser.email" />
          <span class="email-span"> @ </span>
          <input type="text" value="ssafy.com" name="domain" v-model="loginUser.domain" />
        </div>
        <div class="user-modi-info">
          <label for="password">비밀번호</label>
          <input id="password" type="password" name="password" v-model="newPassword" />
        </div>
        <div class="submit-container">
          <button class="modify-btn" type="button" @click="modifyUser">수정하기</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
// user-info js 적용 필요.
import modal from "@/assets/js/modal";
import { updateMember, uploadImgFile } from "@/api/member";
import axios from "axios";

import { mapGetters, mapActions, mapMutations } from "vuex";
const memberStore = "memberStore";

export default {
  name: "UserInfo",
  data() {
    return {
      loginUser: {},
      newPassword: "",
      file: [],
    };
  },
  mounted() {
    modal.init();
    // this.loginUser = this.$store.state.loginUser;
  },
  created() {
    this.loginUser = this.getLoginMember;
  },
  computed: {
    ...mapGetters(memberStore, ["getLoginMember", "getToken", "getProfileURL"]),
  },
  methods: {
    ...mapActions(memberStore, ["modifyMember", "removeMember"]),
    ...mapMutations(memberStore, ["SET_PROFILE_URL"]),
    async eraseMember() {
      if (confirm("확인을 누르시면 회원탈퇴가 진행됩니다.")) {
        this.removeMember(this.loginUser.userId);
        this.goIndex();
      }
    },
    onFileChange(e) {
      e.preventDefault();
      var file = e.target.files[0]; //선택된 파일 가져오기
      this.SET_PROFILE_URL(URL.createObjectURL(file));
      document.getElementById("modal-img").src = URL.createObjectURL(file);
      //console.dir(this.getProfileURL);
    },
    async modifyUser() {
      let userInfo = this.loginUser;
      let isReload = false;
      if (this.newPassword != "") {
        isReload = true;
        userInfo.password = this.newPassword;
      }
      // console.log(isReload);
      // console.log("hey?");
      this.modifyMember(userInfo, isReload);
      this.closeUserInfoModal();
      if (isReload) this.goIndex();
    },
    closeUserInfoModal() {
      document.getElementById("modal").setAttribute("style", "display: none;");
      document.getElementById("filter").setAttribute("style", "display: none;");
    },
    goIndex() {
      this.$emit("go-index");
    },
  },
};
</script>

<style>
@import url("../../assets/css/common.css");
@import url("../../assets/css/user-info.css");
</style>
