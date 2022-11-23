import { apiInstance } from "@/api/index.js";

const api = apiInstance();
const cURL = "/naver";

async function selectNaverNews(success, fail) {
    api.get(`${cURL}/news`).then(success).catch(fail);
}

export { selectNaverNews };