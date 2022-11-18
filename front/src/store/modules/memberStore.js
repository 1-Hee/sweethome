// import jwtDecode from "jwt-decode";
// import router from "@/router";
import { selectMemberList, doLogin, updateMember, deleteMember } from "@/api/member";

const memberStore = {
  namespaced: true,
  state: {
    loginMember: null,
    memberList: [],
    token: {},
    pastListNo: 0,
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
    clearMemberInfo(state) {
      state.loginMember = null;
    },
    getPastListNo(state) {
      return state.pastListNo;
    },
  },
  actions: {
    doLoginMember({ commit }, member) {
      doLogin(
        member,
        ({ data }) => {
          // console.dir(data);
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
          console.log(data);
          console.dir(member);
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
    // addMember({ commit }, member) {
    //   createMember(
    //     member,
    //     ({ data }) => {
    //       console.log(data);
    //       commit("ADD_MEMBER");
    //     },
    //     (err) => {
    //       return this.validateStatus;
    //     }
    //   );
    // },
  },
  mutations: {
    DO_LOGIN_MEMBER(state, member) {
      state.loginMember = member;
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
    },
    // ADD_MEMBER(state) {
    //   // 로그인을 하면...? state에 등록을 해야할까..?
    // },
    EDIT_LAST_PAGE_NO(state, no) {
      state.pastListNo = no;
    },
  },
};

export default memberStore;
