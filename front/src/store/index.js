import Vue from "vue";
import Vuex from "vuex";

import boardStore from "@/store/modules/boardStore";
import noticeStore from "@/store/modules/noticeStore";
import memberStore from "@/store/modules/memberStore";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    boardStore,
    noticeStore,
    memberStore,
  },
});
