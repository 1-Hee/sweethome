import { selectAptDataList, selectAptDataListDong } from "@/api/apt";

const aptStore = {
  namespaced: true,
  state: {
    AptDataList: [],
    AptData: {},
    pgInfo: {},
  },
  getters: {
    getAptDataList(state) {
      return state.AptDataList;
    },
    getAptData(state) {
      return state.AptData;
    },
    getPgInfo(state) {
      return state.pgInfo;
    },
  },
  actions: {
    async setAptDataList(context, code, params) {
      selectAptDataList(
        code,
        params,
        ({ data }) => {
          //console.dir(data);
          context.commit("SET_APT_DATA_LIST", data);
        },
        (err) => {
          console.log(err);
        }
      );
    },

    async setAptDataListDong(context, params) {
      selectAptDataListDong(
        params,
        ({ data }) => {
          //console.dir(data);
          context.commit("SET_APT_DATA_LIST_DONG", data);
        },
        (err) => {
          console.log(err);
        }
      );
    },
  },
  mutations: {
    SET_APT_DATA_LIST(state, data) {
      state.AptDataList = data;
      //state.pgInfo = data;
    },
    SET_APT_DATA_LIST_DONG(state, data) {
      state.AptDataList = data;
    },
  },
};

export default aptStore;
