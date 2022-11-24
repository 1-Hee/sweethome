import { apiInstance } from "@/api/index.js";
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

async function updateMember(member, file) {
  // api.defaults.headers.post['Content-Type'] = 'multipart/form-data';
  api.put(`${cURL}/update`, JSON.stringify(member)).then(success).catch(fail);

  //FormData 객체선언
  // member.append(img);
  // const formData = new FormData();
  // formData.append('file', file);
  // formData.append('member', new Blob([JSON.stringify(member)], {
  //   "Content-Type":"application/json;charset=utf-8"
  // }));
//   console.dir(member);
//   console.dir(file);
//   const formData = new FormData();
  
//   //formData.append('member', JSON.stringify(member));
//   formData.append('id', member.id);
//   formData.append('date', member.date);
//   formData.append('domain', member.domain);
//   formData.append('email', member.email);
//   formData.append('grade', member.grade);
//   formData.append('name', member.name);

//   for(let i = 0; i < file.length ; i ++){
//     console.dir(file[0]);
//     formData.append("file", file[0]);
//   }

// // formData.append("member", member); // 텍스트 첨부
// axios.put('http://localhost:8080/member/update', formData, { // 요청
//   headers: {
//     'Content-Type': 'multipart/form-data'
//   }
// });

  // axios({
  //   method: "put",
  //   url: process.env.VUE_APP_API_BASE_URL+cURL+'/update',
  //   data: formData,
  //   headers: { 
  //  "Content-Type":"application/json;charset=utf-8"},
  // })
  //   .then(function (response) {
  //     //handle success
  //     console.dir(response);
  //   })
  //   .catch(function (response) {
  //     //handle error
  //     console.log(response);
  //   });
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

export { selectMemberList, doLogin, updateMember, deleteMember, confirmMemberById, tokenRegeneration };
