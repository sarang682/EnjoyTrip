import { openApiAxios } from "@/util/http-commons";
const { VITE_OPEN_API_SERVICE_KEY } = import.meta.env;
const openApi = openApiAxios();

let param = {
    serviceKey: VITE_OPEN_API_SERVICE_KEY,
    MobileOS: "ETC",
    MobileApp: "AppTest",
    _type: "json",
}

function listSido(success, fail) {
    param.numOfRows = 20;
    param.pageNo = 1;
    openApi.get(`/areaCode1`, { params: param }).then(success).catch(fail);
}

function listAttraction(areaCode, contentTypeId, word, success, fail) {
    param.numOfRows = 100;
    param.pageNo = 1;
    console.log(areaCode);
    console.log(contentTypeId);
    if (parseInt(areaCode)) param.areaCode = areaCode;
    if (parseInt(contentTypeId)) param.contentTypeId = contentTypeId;

    if (word) {
        param.keyword = word;
        openApi.get(`/searchKeyword1`, { params: param }).then(success).catch(fail);
    }
    else {
        openApi.get(`/areaBasedList1`, { params: param }).then(success).catch(fail);
    }
}

export {
    listSido,
    listAttraction,
}