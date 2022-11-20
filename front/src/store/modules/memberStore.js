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

const memberStore = {
  namespaced: true,
  state: {
    loginMember: null,
    memberList: [],
    token: {},
    pastListNo: 1,
    tokenError: false,
  },
  getters: {
    getMemberList(state) {
      return state.memberList;
    },
    getLoginMember(state) {
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
  },
  actions: {
    async checkMemberById({ commit, dispatch }, token) {
      let decodeToken = jwtDecode(token);
      //console.dir(decodeToken);
      confirmMemberById(
        decodeToken.userid,
        ({ data }) => {
          //console.dir(data);
          commit("SET_ACCESS_TOKEN", token);
        },
        (err) => {
          //console.log(err);
          console.log("getUserInfo() error code [토큰 만료되어 사용 불가능.] ::: ", err.response.status);
          dispatch("doTokenRegeneration");
        }
      );
    },
    async doTokenRegeneration({ commit, state }) {
      tokenRegeneration(
        state.loginMember,
        ({ data }) => {
          console.dir(data);
          commit("SET_ACCESS_TOKEN", data);
        },
        (err) => {
          console.log(err.response.status);
          commit("SET_TOKEN_ERROR");
        }
      );
    },

    doLoginMember({ commit }, member) {
      doLogin(
        member,
        ({ data }) => {
          commit("DO_LOGIN_MEMBER", data);
        },
        (err) => {
          console.log(err);
        }
      );
    },
    modifyMember({ commit }, member, isReload) {
      updateMember(
        member,
        ({ data }) => {
          // console.log(data);
          // console.dir(member);
          commit("UPDATE_MEMBER", member, isReload);
        },
        (err) => {
          console.log(err);
        }
      );
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
  },
  mutations: {
    DO_LOGIN_MEMBER(state, data) {
      state.loginMember = data["member"];
      sessionStorage.setItem("access-token", data["access-token"]);
      sessionStorage.setItem("refresh-token", data["refresh-token"]);
      state.tokenError = false;
      // state.token["access-token"] = data["access-token"];
      // state.token["refresh-token"] = data["refresh-token"];
    },
    SET_USER_TOKEN(state, data) {
      state.token["access-token"] = data["access-token"];
      sessionStorage.setItem("access-token", data["access-token"]);
    },
    SET_ACCESS_TOKEN(state, token) {
      state.token["access-token"];
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
    },

    EDIT_LAST_PAGE_NO(state, no) {
      state.pastListNo = no;
    },
  },
};

export default memberStore;
