import { apiInstance } from "@/api/index.js";

const api = apiInstance();
const cURL= '/notice'


function getNoticeList(param, success, fail) {
  api.get(`${cURL}/list`,{params:param}).then(success).catch(fail);
}

async function writeNotice(board, success, fail) {
  await api.post(`${cURL}/write`, JSON.stringify(board)).then(success).catch(fail);
}

async function getNotice(articleno, success, fail) {
  await api.get(`${cURL}/view/${articleno}`).then(success).catch(fail);
}

async function modifyNotice(board, success, fail) {
  await api.put(`${cURL}/modify/${board.articleNo}`, JSON.stringify(board)).then(success).catch(fail);
}

async function deleteNotice(articleno, success, fail) {
  await api.delete(`${cURL}/delete/${articleno}`).then(success).catch(fail);
}

export { getNoticeList,  writeNotice, getNotice, modifyNotice, deleteNotice};

