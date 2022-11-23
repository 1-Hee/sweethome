import { selectAptDataList, selectAptDataListDong, 
  selectAptDataListAptName, selectAptTOP4Items } from "@/api/apt";
import {getAddressByPOS} from "@/assets/js/map";


const aptStore = {
  namespaced: true,
  state: {
    AptDataList: [],
    AptData: {},
    TOP4AptList : [],
  },
  getters: {
    // 아파트 리스트 가져오는 메서드
    getAptDataList(state) {
      return state.AptDataList;
    },
    // 아파트 객체 하나만 가져오는 메서드, NOT USED... (찜목록 등에서 쓸 경우 유지)
    getAptData(state) {
      return state.AptData;
    },
    // 페이지 정보를 개선하기 위한 메서드
    getPgInfo(state) {
      return state.pgInfo;
    },
    // 메인 페이지 TOP4 매물 가져오는 메서드
    getTop4AptList(state){
      return state.TOP4AptList;
    },
  },
  actions: {
    // 셀렉트 박스에서 동코드로 입력했을 경우 매물 결과가 나오는 메서드
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
    // 메인 검색창에서 동으로 입력했을 경우 매물 결과가 나오는 메서드
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
    // 아파트 명으로 검색했을 경우 매물을 불러오는 메서드
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
    // 메인 페이지에서 MD가 추천하는 매물 4가지 불러오는 메서드
    async setAptTOP4Items(context){
      selectAptTOP4Items(
        ({data})=>{
          // console.dir(data);
          context.commit("SET_APT_TOP4_ITEMS", data);
        },
        (err)=>{
          console.log(err);
        }
      );
    }
  },
  mutations: {
    // 아파트 동코드 기준으로 매물을 뷰엑스에 저장.
    SET_APT_DATA_LIST(state, data) {
      data.forEach((el) => {
        state.AptDataList.push(el);
      });
    },
    // 아파트 검색어(주소지)를 기준으로 매물을 뷰엑스에 저장
    SET_APT_DATA_LIST_DONG(state, data) {
      data.forEach((el) => {
        state.AptDataList.push(el);
      });
    },
    // 아파트 이름을 기준으로 매물을 뷰엑스에 저장
    SET_APT_DATA_LIST_NAME(state, data) {
      data.forEach((el) => {
        state.AptDataList.push(el);
      });
    },
    // 아파트 정보를 초기화해주는 메서드(뷰엑스 상에서 매물 제거)
    SET_APT_DATA_LIST_NULL(state) {
      state.AptDataList = [];
    },
    // 초기 페이지에서 매물 4개를 불러오는 메서드
    SET_APT_TOP4_ITEMS(state, data){
      
      //console.dir(data[0]);
      // console.log(data[0].lng, data[0].lat);
      //getAddressByPOS(data[0].lng, data[0].lat);

      state.TOP4AptList = data;
    },
  },
};

export default aptStore;