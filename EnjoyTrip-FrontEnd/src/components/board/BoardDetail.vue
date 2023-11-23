<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { detailArticle, deleteArticle, listComments, registComment } from "@/api/board";
import { getMember } from "@/api/user";
import CommentListItem from "@/components/board/item/CommentListItem.vue";

const route = useRoute();
const router = useRouter();

const { articleno } = route.params;

const comments = ref([]);
const article = ref({});
const writer = ref({}); // 글쓴 사람 정보
const member = ref({}); // 지금 로그인한 사람 정보

onMounted(() => {
  getMemberInfo();
  getArticle();
  getComments();
});

const getMemberInfo = () => {
  getMember(
    (response) => {
      member.value = response.data['result'];
    },
    (error) => {
      console.log(error);
    }
  );
}

const getArticle = () => {
  detailArticle(
    articleno,
    (response) => {
      article.value = response.data['result'];
      writer.value = response.data['result'].member;
    },
    (error) => {
      console.log(error);
    }
  );
};

const getComments = () => {
  listComments(
    articleno,
    (response) => {
      comments.value = response.data['result'];
    },
    (error) => console.log(error)
  );
}

function moveList() {
  router.push({ name: "article-list" });
}

function moveModify() {
  router.push({ name: "article-modify", params: { articleno } });
}

function onDeleteArticle() {
  deleteArticle(
    articleno,
    (response) => {
      if (response.status == 200) moveList();
    },
    (error) => {
      alert("허용되지 않은 접근입니다.");
    }
  );
}

// ** 댓글 쓰기 코드 **
const com = ref("");

function writeComment() {
  if (com.value == "") {
    alert("내용을 입력하세요.")
  } else {
    const param = {
      content: com.value,
    }
    registComment(
      article.value.id,
      param,
      (response) => {
        if (response.status == 200) {
          router.go(0);
        }
      },
      (error) => {
        alert("허용되지 않은 접근입니다.");
      }
    );
  }
}
</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10 text-start">
        <div class="row my-2">
          <h2 class="text px-5">{{ article.id }}. {{ article.title }}</h2>
        </div>
        <div class="row">
          <div class="col-md-8">
            <div class="clearfix align-content-center">
              <img class="avatar me-2 float-md-start bg-light p-2"
                src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg" />
              <p>
                <span class="fw-bold">{{ writer.name }}</span> <br />
                <span class="text fw-light">
                  {{ article.createdAt }} | 조회 : {{ article.hit }}
                </span>
              </p>
            </div>
          </div>
          <!-- <div class="col-md-4 align-self-center text-end">댓글 : 17</div> -->
          <div class="divider mb-3"></div>
          <div class="text">
            {{ article.content }}
          </div>
          <div class="divider mt-3 mb-3"></div>
          <div class="d-flex justify-content-end">
            <button type="button" class="btn btn-outline-primary mb-3" @click="moveList">
              글목록
            </button>
            <button type="button" class="btn btn-outline-success mb-3 ms-1" v-if="writer.id == member.id"
              @click="moveModify">
              글수정
            </button>
            <button type="button" class="btn btn-outline-danger mb-3 ms-1" v-if="writer.id == member.id"
              @click="onDeleteArticle">
              글삭제
            </button>
          </div>
          <div class="divider mt-3 mb-3"></div>
        </div>
        <h4>댓글</h4>
        <form>
          <div class="input-group input-group-sm">
            <input id="input1" type="text" placeholder="댓글 입력" v-model="com" />
            <button id="btn" type="button" class="btn btn-outline-dark mb-3 ms-1" @click="writeComment">등록</button>
          </div>
        </form>
        <table class="table table-hover">
          <tbody>
            <CommentListItem v-for="comment in comments" :key="comment.id" :comment="comment" :info="member">
            </CommentListItem>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<style scoped>
h3 {
  margin-left: 110px;
  text-align: left;
}

#input1 {
  /* margin-left: 110px; */
  height: 50px;
  width: 90%;
  border-top: none;
  border-left: none;
  border-right: none;
  border-bottom: 3px solid black;
}

#btn {
  width: 8%;
  height: 50px;
  border-top: 3px solid black;
  border-left: 3px solid black;
  border-right: 3px solid black;
  border-bottom: 3px solid black;
}
</style>
