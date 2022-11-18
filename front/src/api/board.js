import { apiInstance } from "@/api/index.js";

const api = apiInstance();
const cURL= '/board'

async function selectBoardList(param, success, fail) {
  api.get(`${cURL}/list`,{ params: param }).then(success).catch(fail);
}

async function createBoard(board, success, fail) {
  api.post(`${cURL}/write`, JSON.stringify(board)).then(success).catch(fail);
}

async function selectBoard(articleno, success, fail) {
  await api.get(`${cURL}/view/${articleno}`).then(success).catch(fail);
}

async function updateBoard(board, success, fail) {
  await api.put(`${cURL}/modify/${board.articleNo}`, JSON.stringify(board)).then(success).catch(fail);
}

async function deleteBoard(articleno, success, fail) {
  await api.delete(`${cURL}/delete/${articleno}`).then(success).catch(fail);
}

export {selectBoardList, selectBoard, createBoard, updateBoard, deleteBoard };

