<template>
  <div>
    <!-- 공지사항 뷰 영역-->
    <hr />
    <div class="view-form-container flex-def">
      <div class="left-menu">
        <a href="#" class="defbtn btn-back">뒤로가기</a>
      </div>

      <div class="right-menu">
        <div class="modify-div">
          <button type="button" id="modify-btn" class="modify defbtn" onclick="showModify()">글 수정</button>
          <button
            type="button"
            id="modify-cancel-btn"
            style="display: none"
            class="modify defbtn"
            onclick="modifyCacel()"
          >
            수정 취소
          </button>
        </div>
        <div class="cancel-div">
          <a class="cancel defbtn" id="delete-btn" onclick="deleteAlert(localStorage.getItem('articleNo'))">글 삭제</a>
        </div>
      </div>
    </div>
    <div id="view" class="view-container">
      <div class="title-section">
        <h1 id="category-text">공지사항</h1>
      </div>
      <div class="content-box">
        <hr />
        <div class="title" id="title">제목 : {{ Notice.title }}</div>
        <div class="article-info-section">
          <span class="writer" id="writer">{{ Notice.userId }}</span>
          <span class="date" id="date">{{ Notice.registDate }}</span>
          <span class="hit" id="hit">({{ Notice.hit }})</span>
        </div>
        <hr />
        <textarea class="content-textarea content" id="content" readonly :value="Notice.content"> </textarea>
      </div>
    </div>

    <form id="modiform" action="#" method="post">
      <div id="modify-view" style="display: none" class="view-container">
        <div class="title-section">
          <h3 id="mcategory-text"></h3>
        </div>
        <hr />
        <div class="content-box">
          <input id="articleNoHidden" type="hidden" name="articleNo" />
          <div class="title">
            <input type="text" id="mtitle" name="title" class="default-input modi-title" />
          </div>
          <div class="article-info-section">
            <span class="writer" id="mwriter"></span>
            <span class="date" id="mdate"></span>
            <span class="hit" id="mhit"></span>
          </div>
          <hr />
          <div class="content">
            <textarea class="content-input default-input" id="mcontent" name="content"> </textarea>
          </div>
        </div>
        <div class="send-modify">
          <button type="submit" class="defbtn submit-btn">수정하기</button>
        </div>
      </div>
    </form>
    <!-- 공지사항 뷰 영역-->
  </div>
</template>

<script>
export default {
  name: "NoticeView",
  data() {
    return {
      Notice: {},
    };
  },
  created() {
    this.Notice = JSON.parse(localStorage.getItem("notice"));
  },
  methods: {},
};
</script>

<style>
@import url("../../assets/css/common.css");
@import url("../../assets/css/board-view.css");
</style>
