import { apiInstance } from "@/api/index.js";

const api = apiInstance();
const cURL= '/board'

function getBoardList(param, success, fail) {
  api.get(`${cURL}/list`,{ params: param }).then(success).catch(fail);
}

function writeBoard(board, success, fail) {
  api.post(`${cURL}/write`, JSON.stringify(board)).then(success).catch(fail);
}

async function getBoard(articleno, success, fail) {
  await api.get(`${cURL}/view/${articleno}`).then(success).catch(fail);
}

async function modifyBoard(board, success, fail) {
  await api.put(`${cURL}/modify/${board.articleNo}`, JSON.stringify(board)).then(success).catch(fail);
}

async function deleteBoard(articleno, success, fail) {
  await api.delete(`${cURL}/delete/${articleno}`).then(success).catch(fail);
}

export { getBoardList,  writeBoard, getBoard, modifyBoard, deleteBoard};

