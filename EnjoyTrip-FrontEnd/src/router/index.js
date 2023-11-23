import { createRouter, createWebHistory } from "vue-router";
import TheMainView from "../views/TheMainView.vue";
import TheAttractionView from "@/views/TheAttractionView.vue";
import TheBookmarkView from "@/views/TheBookmarkView.vue";
import TheHotplaceView from "@/views/TheHotplaceView.vue";

// import TheBoardView from "../views/TheBoardView.vue";

const requireAuth = () => (to,from,next) => {
  if(sessionStorage.getItem("accessToken")==null) { // 토큰이 없다
    alert("로그인이 필요합니다.");
    next('/user/login');
  }else { // 토큰이 있다
    return next();
  }
}

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "main",
      component: TheMainView,
    },
    {
      path: "/attraction",
      name: "attraction",
      component: TheAttractionView,
    },
    {
      path: "/plan/:planid",
      name: "plan-view",
      component: () => import("@/components/attraction/PlanDetail.vue"),
      props: true
    },
    {
      path: "/hotplace",
      name: "hotplace",
      component: TheHotplaceView,
    },
    {
      path: "/board",
      name: "board",
      // component: TheBoardView,
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import("../views/TheBoardView.vue"),
      redirect: { name: "article-list" },
      children: [
        {
          path: "list",
          name: "article-list",
          component: () => import("@/components/board/BoardList.vue"),
        },
        {
          path: "view/:articleno",
          name: "article-view",
          component: () => import("@/components/board/BoardDetail.vue"),
          beforeEnter: requireAuth()
        },
        {
          path: "write",
          name: "article-write",
          component: () => import("@/components/board/BoardWrite.vue"),
          beforeEnter: requireAuth()
        },
        {
          path: "modify/:articleno",
          name: "article-modify",
          component: () => import("@/components/board/BoardModify.vue"),
          beforeEnter: requireAuth(),
          props: true
        },
      ],
    },
    {
      path: "/bookmark",
      name: "bookmark",
      component: TheBookmarkView,
    },
    {
      path: "/user",
      name: "user",
      component: () => import("@/views/TheUserView.vue"),
      children: [
        {
          path: "login",
          name: "user-login",
          component: () => import("@/components/users/UserLogin.vue"),
        },
        {
          path: "join",
          name: "user-join",
          component: () => import("@/components/users/UserRegister.vue"),
        },
        {
          path: "mypage",
          name: "user-mypage",
          component: () => import("@/components/users/UserMyPage.vue"),
          beforeEnter: requireAuth(),
        },
        {
          path: "/modify",
          name: "user-modify",
          component: () => import("@/components/users/UserModify.vue"),
        },
        {
          path: "/plan",
          name: "user-plan",
          component: () => import("@/components/users/UserPlan.vue"),
          beforeEnter: requireAuth(),
        },
      ],
    },
  ],
});
export default router;
