<template>
  <div>
    <!-- 사용자 정보 영역 -->
    <hr />
    <div class="user-info-conatiner">
      <div class="user-info-head">
        <h3>프로필 수정</h3>
        <p>마이홈 대표 프로필과 별명을 수정할 수 있습니다.</p>
      </div>
      <hr />
      <div class="user-info-box">
        <ul>
          <li>
            <div class="sort">프로필</div>
            <div>
              <img src="@/assets/img/user-sample.jpg" />
            </div>
            <button id="modishow" class="modify-btn">변경하기</button>
          </li>
          <li>
            <div class="sort">사용자 정보</div>
            <div class="user-info">
              <div>ID : <span> ssafy1 </span></div>
              <div>이름 : <span> 김싸피 </span></div>
              <div>나이 : <span> 23 </span></div>
              <div>이메일 : <span> ssafy1 @ ssafy.com </span></div>
              <div>
                등급 :
                <span>일반 사용자 </span>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>

    <!-- 모달창 -->
    <div id="filter" class="background" style="display: none"></div>
    <div id="modal" class="modi-info-modal" style="display: none">
      <div class="modi-title">
        <p>회원정보 수정</p>
        <button id="modi-close-btn" class="modi-close-btn">X</button>
      </div>

      <form action="/user/modify" method="post" enctype="multipart/form-data">
        <div class="user-modi-info user-img">
          <img src="@/assets/img/user-sample.jpg" />
          <label for="file" class="upload-btn">업로드</label>
          <input id="file" style="display: none" type="file" multiple />
        </div>
        <div class="user-modi-info">
          <label for="uid">ID</label>
          <input id="uid" type="text" readonly disabled value="ssafy1" />
        </div>
        <div class="user-modi-info">
          <label for="uname">이름 </label>
          <input id="uname" type="text" value="김싸피" name="name" />
        </div>
        <div class="user-modi-info">
          <label for="age">나이</label>
          <input id="age" type="number" value="23" name="age" />
        </div>
        <div class="user-modi-info modi-email-div">
          <label for="email">이메일</label>
          <input id="email" type="text" value="ssafy1" name="email" />
          <span class="email-span"> @ </span>
          <input type="text" value="ssafy.com" name="domain" />
        </div>
        <div class="user-modi-info">
          <label for="password">비밀번호</label>
          <input id="password" type="password" name="password" />
        </div>
        <div class="submit-container">
          <button class="modify-btn" type="submit">수정하기</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
// user-info js 적용 필요.
import modal from "@/assets/js/modal";

export default {
  name: "UserInfo",
  mounted() {
    modal.init();
  },
};
</script>

<style>
@import url("../../assets/css/common.css");
@import url("../../assets/css/user-info.css");
</style>
