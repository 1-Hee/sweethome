import { apiInstance } from "@/api/index.js";
import { faI } from "@fortawesome/free-solid-svg-icons";
import axios from "axios";

const api = apiInstance();
const cURL = "/member";

async function selectMemberList(success, fail) {
  api.get(`${cURL}/list`).then(success).catch(fail);
}

// 회원가입
// async function createMember(member, success, fail) {
//   api.post(`${cURL}/signup`, JSON.stringify(member)).then(success).catch(fail);
// }
// store로 하게 되면 에러 발생시 에러 핸들링을 모르겠어서 회원가입 창에서 직접 axios를 부르는 방법으로 대체.

// 로그인 말고 유저정보 하나만 가져오는게 필요할 때 사용할 메서드
// async function selectMember(articleno, success, fail) {
//   api.get(`${cURL}/view/${articleno}`).then(success).catch(fail);
// }

// 로그인
async function doLogin(member, success, fail) {
  api.post(`${cURL}/login`, JSON.stringify(member)).then(success).catch(fail);
}

async function updateMember(member) {
  api
    .put(`${cURL}/update`, JSON.stringify(member))
    .then(({ data }) => {
      console.dir(data);
    })
    .catch((err) => {
      console.dir(err);
    });
}

async function uploadImgFile(file, success, fail) {
  const formData = new FormData();
  formData.append("img", file);
  // for (let i = 0; i < file.length; i++) {
  //   console.dir(file[0]);
  // }
  axios({
    method: "post",
    url: process.env.VUE_APP_API_BASE_URL + cURL + "/update/img",
    data: formData,
  })
    .then(success)
    .catch(fail);
  // api.headers["Content-Type"] = "multipart/form-data";
  // api.post(`${cURL}/update/img`, formData).then(success).catch(fail);
}

async function deleteMember(userId, success, fail) {
  api.delete(`${cURL}/delete/${userId}`).then(success).catch(fail);
}

async function confirmMemberById(userId, success, fail) {
  // console.log(userId);
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  api.get(`${cURL}/info/access/${userId}`).then(success).catch(fail);
}

async function tokenRegeneration(member, success, fail) {
  api.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token");
  api.post(`${cURL}/refresh`, JSON.stringify(member)).then(success).catch(fail);
}

export { selectMemberList, doLogin, updateMember, deleteMember, confirmMemberById, tokenRegeneration, uploadImgFile };
