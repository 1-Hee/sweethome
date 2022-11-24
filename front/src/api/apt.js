// /apt/search/apt-list

import { apiInstance } from "@/api/index.js";

const api = apiInstance();
const cURL = "/apt";

async function selectAptDataList(code, param, success, fail) {
  await api.get(`${cURL}/search/apt-list?fullCode=${code}`, { params: param }).then(success).catch(fail);
}

async function selectAptDataListDong(param, success, fail) {
  await api.get(`${cURL}/search/dong`, { params: param }).then(success).catch(fail);
}

async function selectAptDataListAptName(param, success, fail) {
  //console.dir(param);
  await api.get(`${cURL}/search/name`, { params: param }).then(success).catch(fail);
}

// async function getCode(type, code, success, fail) {
//   await api.get(`${cURL}/box?type=${type}&code=${code}`, { params: param }).then(success).catch(fail);
// }

// 메인 화면에서 아파트 좋아요 수가 많은 순서대로 매물을 불러오는 메서드
async function selectAptTOP4Items(success, fail) {
  await api.get(`${cURL}/index/recommend/like`).then(success).catch(fail);
}

// 메인 화면에서 아파트 가격이 낮은 순서대로 매물을 불러오는 메서드
async function selectAptPriceItems(success, fail) {
  await api.get(`${cURL}/index/recommend/price`).then(success).catch(fail);
}

// 아파트 위경도 값으로 매물 검색
async function selectAptListLatLng(pos, success, fail){
  await api.get(`${cURL}/around`, JSON.stringify(pos)).then(success).catch(fail);
}

// 아파트 좋아요 누르면 찜목록에 반영
async function insertAptDataLike(param, success, fail){
  await api.post(`${cURL}/like`, JSON.stringify(param)).then(success).catch(fail);
}

export { selectAptDataList, selectAptDataListDong, selectAptDataListAptName, selectAptTOP4Items, selectAptPriceItems,selectAptListLatLng, insertAptDataLike };
