import { localAxios } from "@/util/http-commons";

const local = localAxios();

function listSido(success, fail) {
  local.get(`/attractions/sido`).then(success).catch(fail);
}

function listGugun(param, success, fail) {
  local.get(`/attractions/gugun`, { params: param }).then(success).catch(fail);
}

function listContentType(success, fail) {
    local.get(`attractions/content-type`).then(success).catch(fail);
}

function listAttractionInfo(param, success, fail) {
  local.get(`/attractions/info`, { params: param }).then(success).catch(fail);
}

function attractionInfo(contentId, success, fail) {
  local.get(`/attractions/info/${contentId}`).then(success).catch(fail);
}

function attractionDescription(contentId, success, fail) {
  local.get(`/attractions/description/${contentId}`).then(success).catch(fail);
}

export {
    listSido,
    listGugun,
    listContentType,
    listAttractionInfo,
    attractionInfo,
    attractionDescription,
};
