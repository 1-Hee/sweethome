import { selectAptDataList, selectAptDataListDong, selectAptDataListAptName } from "@/api/apt";

const aptStore = {
  namespaced: true,
  state: {
    AptDataList: [],
    AptData: {},
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
    async setAptDataAptName(context, params) {
      selectAptDataListAptName(
        params,
        ({ data }) => {
          console.dir(data);
          context.commit("SET_APT_DATA_LIST_NAME", data);
        },
        (err) => {
          console.log(err);
        }
      );
    },
  },
  mutations: {
    SET_APT_DATA_LIST(state, data) {
      data.forEach((el) => {
        state.AptDataList.push(el);
      });
    },
    SET_APT_DATA_LIST_DONG(state, data) {
      data.forEach((el) => {
        state.AptDataList.push(el);
      });
    },
    SET_APT_DATA_LIST_NAME(state, data) {
      data.forEach((el) => {
        state.AptDataList.push(el);
      });
    },
    SET_APT_DATA_LIST_NULL(state) {
      state.AptDataList = [];
    },
  },
};

export default aptStore;
