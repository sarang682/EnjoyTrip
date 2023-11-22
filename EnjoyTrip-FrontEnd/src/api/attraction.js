import { localAxios } from "@/util/http-commons";

const local = localAxios();

function listSido(success, fail) {
  local.get(`/attractions/sido`).then(success).catch(fail);
}

function listGugun(param, success, fail) {
  local.get(`/attractions/gugun`, { params: param }).then(success).catch(fail);
}

function listAttractionType(success, fail) {
    local.get(`attractions/attraction-type`).then(success).catch(fail);
}

async function listAttractionInfo(param, success, fail) {
  local.defaults.headers["Authorization"] =
        sessionStorage.getItem("accessToken");
  await local.get(`/attractions/info`, { params: param }).then(success).catch(fail);
}

// function attractionInfo(attractionId, success, fail) {
//   local.get(`/attractions/info/${attractionId}`).then(success).catch(fail);
// }

async function attractionDescription(attractionId, success, fail) {
    local.defaults.headers["Authorization"] =
        sessionStorage.getItem("accessToken");
    await local.get(`/attractions/description/${attractionId}`).then(success).catch(fail);
}

export {
    listSido,
    listGugun,
    listAttractionType,
    listAttractionInfo,
    // attractionInfo,
    attractionDescription,
};
