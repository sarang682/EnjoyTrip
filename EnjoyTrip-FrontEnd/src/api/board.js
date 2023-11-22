import { localAxios } from "@/util/http-commons";

const local = localAxios();

function listArticle(success, fail) {
  local.get(`/board/articles`).then(success).catch(fail);
}

function detailArticle(articleno, success, fail) {
  local.get(`/board/articles/${articleno}`).then(success).catch(fail);
}

function registArticle(article, success, fail) {
  local.defaults.headers["Authorization"] = sessionStorage.getItem("accessToken");
  local.post(`/board/articles`, JSON.stringify(article)).then(success).catch(fail);
}

function modifyArticle(article, success, fail) {
  local.defaults.headers["Authorization"] = sessionStorage.getItem("accessToken");
  local.put(`/board/articles`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteArticle(articleno, success, fail) {
  local.delete(`/board/articles/${articleno}`).then(success).catch(fail);
}

function listComments(articleno, success, fail) {
  local.get(`/board/${articleno}/comments`).then(success).catch(fail);
}

function registComment(articleno, param, success, fail) {
  local.defaults.headers["Authorization"] = sessionStorage.getItem("accessToken");
  local.post(`/board/${articleno}/comments`,JSON.stringify(param)).then(success).catch(fail);
}

function deleteComment(commentId, success, fail) {
  local.defaults.headers["Authorization"] = sessionStorage.getItem("accessToken");
  local.delete(`/board/comments/${commentId}`).then(success).catch(fail);
}

export {
  listArticle,
  detailArticle,
  registArticle,
  modifyArticle,
  deleteArticle,
  listComments,
  registComment,
  deleteComment,
};
