import { apiInstance } from "@/api/index.js";

const api = apiInstance();
const cURL= '/member'


function getUserList(success, fail) {
  api.get(`${cURL}/list`).then(success).catch(fail);
}

function writeNotice(board, success, fail) {
  api.post(`${cURL}/write`, JSON.stringify(board)).then(success).catch(fail);
}

function getNotice(articleno, success, fail) {
  api.get(`${cURL}/view/${articleno}`).then(success).catch(fail);
}

function modifyNotice(board, success, fail) {
  api.put(`${cURL}/modify/${board.articleNo}`, JSON.stringify(board)).then(success).catch(fail);
}

function deleteNotice(articleno, success, fail) {
  api.delete(`${cURL}/delete/${articleno}`).then(success).catch(fail);
}

export { getNoticeList,  writeNotice, getNotice, modifyNotice, deleteNotice};

