import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

import boardStore from "@/store/modules/boardStore";
import noticeStore from "@/store/modules/noticeStore";
import memberStore from "@/store/modules/memberStore";

import aptStore from "@/store/modules/aptStore";
import naverStore from "@/store/modules/naverStore"

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    boardStore,
    noticeStore,
    memberStore,
    aptStore,
    naverStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});
