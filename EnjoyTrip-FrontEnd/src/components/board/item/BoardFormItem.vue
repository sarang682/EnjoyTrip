<script setup>
import { ref, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
import { registArticle, detailArticle, modifyArticle } from "@/api/board";
import { getMember } from "@/api/user";

const router = useRouter();
const route = useRoute();

const props = defineProps({ type: String });

const article = ref({});
const member = ref({});

if (props.type === "modify") {
  let { articleno } = route.params;
  detailArticle(
    articleno,
    (response) => {
      article.value = response.data['result'];
      member.value = response.data['result'].member;
    },
    (error) => {
      console.log(error);
    }
  );
} else {
  getMember(
    (response) => {
      member.value=response.data['result'];
    },
    (error) => {
      console.log(error);
    }
  );
}


function onSubmit() {
  if (article.value.title=="" || article.value.content=="") {
    alert("내용을 확인해주세요");
  } else {
    props.type === "regist" ? writeArticle() : updateArticle();
  }
}

function writeArticle() {
  const param={
    title: article.value.title,
    content: article.value.content
  }
  registArticle(
    param,
    (response) => {
      let msg = "글등록 처리시 문제 발생했습니다.";
      if (response.status == 200) msg = "글등록이 완료되었습니다.";
      alert(msg);
      moveList();
    },
    (error) => alert("허용되지 않은 접근입니다.")
  );
}

function updateArticle() {
  const param = {
    articleId: article.value.id,
    title: article.value.title,
    content: article.value.content
  }
  modifyArticle(
    param,
    (response) => {
      let msg = "글수정 처리시 문제 발생했습니다.";
      if (response.status == 200) msg = "글정보 수정이 완료되었습니다.";
      alert(msg);
      moveList();
    },
    (error) => alert("허용되지 않은 접근입니다.")
  );
}

function moveList() {
  router.push({ name: "article-list" });
}
</script>

<template>
  <form @submit.prevent="onSubmit">
    <div class="mb-3">
      <label for="userid" class="form-label">작성자 : </label>
      <input
        type="text"
        class="form-control"
        v-model="member.name"
        :disabled="true"
        placeholder="작성자ID..."
      />
    </div>
    <div class="mb-3">
      <label for="subject" class="form-label">제목 : </label>
      <input type="text" class="form-control" v-model="article.title" placeholder="제목..." />
    </div>
    <div class="mb-3">
      <label for="content" class="form-label">내용 : </label>
      <textarea class="form-control" v-model="article.content" rows="10"></textarea>
    </div>
    <div class="col-auto text-center">
      <button type="submit" class="btn btn-outline-primary mb-3" v-if="type === 'regist'">
        글작성
      </button>
      <button type="submit" class="btn btn-outline-success mb-3" v-else>글수정</button>
      <button type="button" class="btn btn-outline-danger mb-3 ms-1" @click="moveList">
        목록으로이동...
      </button>
    </div>
  </form>
</template>

<style scoped></style>
