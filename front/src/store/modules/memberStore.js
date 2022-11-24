import jwtDecode from "jwt-decode";
// import router from "@/router";
import {
  selectMemberList,
  doLogin,
  updateMember,
  deleteMember,
  confirmMemberById,
  tokenRegeneration,
} from "@/api/member";

import { selectAptDataLike } from "@/api/apt"; // select만 가져온다.

const memberStore = {
  namespaced: true,
  state: {
    loginMember: null,
    memberList: [],
    token: {},
    pastListNo: 1,
    tokenError: false,
    AptLikeList: [],
    profileURL: "",
  },
  getters: {
    getMemberList(state) {
      return state.memberList;
    },
    getLoginMember(state) {
      // console.log(sessionStorage.getItem("member"));
      return state.loginMember;
    },
    getToken(state) {
      return state.token;
    },
    getTokenError(state) {
      return state.tokenError;
    },
    getPastListNo(state) {
      return state.pastListNo;
    },
    // 좋아요 누른 아파트 정보
    getAptLikeList(state) {
      return state.AptLikeList;
    },
    // 프로필 이미지 링크 리턴
    getProfileURL(state) {
      return state.profileURL;
    },
  },
  actions: {
    async checkMemberById({ commit, dispatch }, token) {
      let decodeToken = jwtDecode(token);
      //console.dir(decodeToken);
      confirmMemberById(
        decodeToken.userid,
        ({ data }) => {
          // console.dir(data); // 여긴 유저 정보만 줌,
          //console.log(data);
          commit("SET_TOKEN_VARIABLE");
        },
        (err) => {
          console.dir("에러 :", err);
          //console.log("에러다 에러");
          // console.log("엑세스에 실패해서 리프레시로 재발급");
          // console.log("getUserInfo() error code [토큰 만료되어 사용 불가능.] ::: ", err.response.status);
          dispatch("doTokenRegeneration");
        }
      );
    },
    async doTokenRegeneration({ commit, state }) {
      tokenRegeneration(
        state.loginMember,
        ({ data }) => {
          // console.dir(data);
          // console.log("리프레시로 엑세스 발급 성공");
          commit("SET_ACCESS_TOKEN", data);
          commit("SET_TOKEN_VARIABLE");
        },
        (err) => {
          console.log("here");
          console.dir(err);
          // console.log("리프레시도 만료...");
          // console.log(err.response.status);
          commit("SET_TOKEN_ERROR");
        }
      );
    },

    doLoginMember({ commit }, member) {
      doLogin(
        member,
        ({ data }) => {
          // console.dir(data);
          commit("DO_LOGIN_MEMBER", data);
        },
        (err) => {
          // console.dir(err.response.data.message);
          alert(err.response.data.message);
        }
      );
    },
    modifyMember({ commit }, member, img) {
      console.dir(member);
      console.dir(img);
      updateMember(member, img);
    },
    removeMember({ commit }, userId) {
      deleteMember(
        userId,
        ({ data }) => {
          console.log(data);
          commit("REMOVE_MEMBER");
        },
        (err) => {
          console.log(err);
        }
      );
    },
    checkValidation() {},
    setAptLikeList({ commit }, userId) {
      selectAptDataLike(
        userId,
        ({ data }) => {
          console.dir(data);
          ok();
          commit("SET_APT_LIKE_LIST", data);
        },
        (err) => {
          console.log(err);
        }
      );
    },
  },
  mutations: {
    DO_LOGIN_MEMBER(state, data) {
      // console.dir(data);
      state.loginMember = data["member"];
      sessionStorage.setItem("member", data["member"]);
      sessionStorage.setItem("access-token", data["access-token"]);
      sessionStorage.setItem("refresh-token", data["refresh-token"]);
      state.tokenError = false;
      // state.token["access-token"] = data["access-token"];
      // state.token["refresh-token"] = data["refresh-token"];
    },
    SET_ACCESS_TOKEN(state, data) {
      state.token["access-token"] = data["access-token"];
      sessionStorage.setItem("access-token", data["access-token"]);
    },
    SET_TOKEN_VARIABLE(state) {
      state.tokenError = false;
    },
    SET_TOKEN_ERROR(state) {
      state.tokenError = true;
    },
    UPDATE_MEMBER(state, member, isReload) {
      if (!isReload) {
        state.loginMember = member;
      } else {
        state.loginMember = null;
      }
    },
    REMOVE_MEMBER(state) {
      state.loginMember = null;
      state.tokenError = false;
      state.loginMember = null;
      state.pastListNo = 1;
      sessionStorage.removeItem("access-token");
      sessionStorage.removeItem("refresh-token");
      sessionStorage.removeItem("member");
    },

    EDIT_LAST_PAGE_NO(state, no) {
      state.pastListNo = no;
    },
    SET_APT_LIKE_LIST(state, data) {
      state.AptLikeList = data;
    },
    // 프로필 이미지 링크 변경
    SET_PROFILE_URL(state, url) {
      state.profileURL = url;
    },
  },
};

function ok() {
  document.getElementById("wating-bg").classList.remove("show");
  document.getElementById("waiting-circle").classList.remove("show");
  document.getElementById("wating-bg").classList.add("hide");
  document.getElementById("waiting-circle").classList.add("hide");
}

export default memberStore;
