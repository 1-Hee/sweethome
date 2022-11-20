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

async function selectAptDataListPos(param, success, fail) {
  await api.get(`${cURL}/search/pos`, { params: param }).then(success).catch(fail);
}

// async function getCode(type, code, success, fail) {
//   await api.get(`${cURL}/box?type=${type}&code=${code}`, { params: param }).then(success).catch(fail);
// }

export { selectAptDataList, selectAptDataListDong, selectAptDataListPos };
