import { localAxios } from "@/util/http-commons";

const local = localAxios();

async function postHotplace(request, success, fail) {
    local.defaults.headers["Authorization"] =
        sessionStorage.getItem("accessToken");
    await local.post(`/hotplaces`, JSON.stringify(request)).then(success).catch(fail);
}

function getHotplaces(success, fail) {
    local.get(`/hotplaces`).then(success).catch(fail);
}

function getHotplace(hotplaceId, success, fail) {
    local.get(`/hotplaces/${hotplaceId}}`).then(success).catch(fail);
}

async function deleteHotplace(hotplaceId, success, fail) {
    local.defaults.headers["Authorization"] =
        sessionStorage.getItem("accessToken");
    await local.get(`/bookmarks/${hotplaceId}`).then(success).catch(fail);
}

export {
    postHotplace,
    getHotplaces,
    getHotplace,
    deleteHotplace
};
