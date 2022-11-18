import { apiInstance } from "@/api/index.js";

const api = apiInstance();
const cURL= '/notice'


async function selectNoticeList(param, success, fail) {
  api.get(`${cURL}/list`,{params:param}).then(success).catch(fail);
}

async function createNotice(board, success, fail) {
  await api.post(`${cURL}/write`, JSON.stringify(board)).then(success).catch(fail);
}

async function selectNotice(articleno, success, fail) {
  await api.get(`${cURL}/view/${articleno}`).then(success).catch(fail);
}

async function updateNotice(board, success, fail) {
  await api.put(`${cURL}/modify/${board.articleNo}`, JSON.stringify(board)).then(success).catch(fail);
}

async function deleteNotice(articleno, success, fail) {
  await api.delete(`${cURL}/delete/${articleno}`).then(success).catch(fail);
}

export { selectNoticeList,  createNotice, selectNotice, updateNotice, deleteNotice};

