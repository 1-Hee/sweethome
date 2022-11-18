import { apiInstance } from "@/api/index.js";

const api = apiInstance();
const cURL= '/member'


// async function selectUserList(success, fail) {
//   api.get(`${cURL}/list`).then(success).catch(fail);
// }

// async function createNotice(board, success, fail) {
//   api.post(`${cURL}/write`, JSON.stringify(board)).then(success).catch(fail);
// }

// async function selectNotice(articleno, success, fail) {
//   api.get(`${cURL}/view/${articleno}`).then(success).catch(fail);
// }

// async function updateNotice(board, success, fail) {
//   api.put(`${cURL}/modify/${board.articleNo}`, JSON.stringify(board)).then(success).catch(fail);
// }

// async function deleteNotice(articleno, success, fail) {
//   api.delete(`${cURL}/delete/${articleno}`).then(success).catch(fail);
// }

// export { selectUserList,  createNotice, selectNotice, updateNotice, deleteNotice};

