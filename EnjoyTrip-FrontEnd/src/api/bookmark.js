import { localAxios } from "@/util/http-commons";

const local = localAxios();

async function changeBookmark(request, success, fail) {
    local.defaults.headers["Authorization"] =
        sessionStorage.getItem("accessToken");
    await local.post(`/bookmarks`, JSON.stringify(request)).then(success).catch(fail);
}

async function getBookmarkList(success, fail) {
    local.defaults.headers["Authorization"] =
        sessionStorage.getItem("accessToken");
    await local.post(`/bookmarks`).then(success).catch(fail);
}

export {
    changeBookmark,
    getBookmarkList,
};
