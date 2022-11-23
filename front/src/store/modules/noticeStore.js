import {
  selectNoticeList,
  createNotice,
  selectNotice,
  updateNotice,
  deleteNotice,
  selectTop4NoticeList,
} from "@/api/notice";

const noticeStore = {
  namespaced: true,
  state: {
    NoticeList: [],
    Top4NoticeList: [],
    Notice: {},
    pgInfo: {},
  },
  getters: {
    getNoticeList(state) {
      return state.NoticeList;
    },
    getNotice(state) {
      return state.Notice;
    },
    getPgInfo(state) {
      return state.pgInfo;
    },
    getTop4NoticeList(state) {
      return state.Top4NoticeList;
    },
  },
  actions: {
    setNoticeList(context, params) {
      selectNoticeList(
        params,
        ({ data }) => {
          context.commit("SET_NOTICE_LIST", data);
        },
        (err) => {
          console.log(err);
        }
      );
    },

    setNotice(context, articleNo) {
      selectNotice(
        articleNo,
        ({ data }) => {
          //console.dir(data);
          context.commit("SET_NOTICE", data.notice);
        },
        (err) => {
          console.log(err);
        }
      );
    },

    addNotice({ commit }, notice) {
      createNotice(
        notice,
        ({ data }) => {
          console.log(data);
          commit("ADD_NOTICE", notice);
        },
        (err) => {
          console.log(err);
        }
      );
    },

    modifyNotice(context, notice) {
      updateNotice(
        notice,
        ({ data }) => {
          // console.log(data);
          context.commit("MODIFY_NOTICE", notice);
        },
        (err) => {
          console.log(err);
        }
      );
    },

    removeNotice(context, articleNo) {
      deleteNotice(
        articleNo,
        ({ data }) => {
          // console.log(data);
          context.commit("REMOVE_NOTICE");
        },
        (err) => {
          console.log(err);
        }
      );
    },

    // 상위 4개 글을 불러오는 메서드
    setFourNoticeList(context) {
      selectTop4NoticeList(
        ({ data }) => {
          // console.dir(data);
          context.commit("SET_TOP4_NOTICE_LIST", data);
        },
        (err) => {
          console.log(err);
        }
      );
    },
  },
  mutations: {
    SET_NOTICE_LIST(state, data) {
      state.NoticeList = data.list;
      state.pgInfo = data;
    },

    SET_NOTICE(state, notice) {
      state.Notice = notice;
    },

    ADD_NOTICE(state, notice) {
      state.Notice = notice;
      state.NoticeList.push(notice);
    },

    MODIFY_NOTICE(state, notice) {
      state.Notice = notice;
    },

    REMOVE_NOTICE(state) {
      state.notice = null;
    },

    // 상위 4개글
    SET_TOP4_NOTICE_LIST(state, data) {
      state.Top4NoticeList = data;
    },
  },
};

export default noticeStore;
