import { localAxios } from "@/util/http-commons";

const local = localAxios();

function plan(param, success, fail) {
    local.defaults.headers["Authorization"] = sessionStorage.getItem("accessToken");
    local.post(`/plan`, JSON.stringify(param)).then(success).catch(fail);
}

function planAttraction(planId, param, success, fail) {
    local.post(`/plan/${planId}`,JSON.stringify(param)).then(success).catch(fail);
}

function planList(success, fail) {
    local.defaults.headers["Authorization"] = sessionStorage.getItem("accessToken");
    local.get(`/plan`).then(success).catch(fail);
}

function attractionList(planId, success, fail) {
    local.get(`/plan/${planId}`).then(success).catch(fail);
}

export {
    plan,
    planAttraction,
    planList,
    attractionList,
}