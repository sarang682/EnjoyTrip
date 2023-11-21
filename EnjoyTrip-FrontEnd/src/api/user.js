import { localAxios } from "@/util/http-commons";

const local = localAxios();

async function userConfirm(user, success, fail) {
  await local.post(`/members/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function getMember(success, fail) {
  local.defaults.headers["Authorization"] = sessionStorage.getItem("accessToken");
  await local.get(`/members/info`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  local.defaults.headers["refreshToken"] = sessionStorage.getItem("refreshToken"); //axios header에 refresh-token 셋팅
  await local.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await local.get(`/user/logout/${userid}`).then(success).catch(fail);
}

async function idCheck(userid, success, fail) {
  await local.get(`/members/id-check/${userid}`).then(success).catch(fail);
}

function join(user, success, fail) {
  local.post(`/members`, JSON.stringify(user)).then(success).catch(fail);
}

function update(memberId, user, success, fail) {
  local.put(`/members/${memberId}`, JSON.stringify(user)).then(success).catch(fail);
}

function deleteUser(memberId, success, fail) {
  local.delete(`/members/${memberId}`).then(success).catch(fail);
}
export { userConfirm, getMember, tokenRegeneration, logout, idCheck, join, update , deleteUser};
