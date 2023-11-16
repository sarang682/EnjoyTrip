import axios from "axios";

const { VITE_VUE_API_URL, VITE_ATTRACTION_URL } = import.meta.env;

// local vue api axios instance
function localAxios() {
  const instance = axios.create({
    baseURL: VITE_VUE_API_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

function openApiAxios() {
  const instance = axios.create({
    baseURL: VITE_ATTRACTION_URL,
  })
  return instance;
}

export { localAxios, openApiAxios };
