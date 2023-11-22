<script setup>
import { deleteComment } from "@/api/board";
import { useRouter } from "vue-router";

defineProps({
  comment: Object,
  info: Object,
});

const router = useRouter();

function deleteEvent(id) {
  deleteComment(
    id,
    (response) => {
      router.go(0);
    },
    (error) => console.error(error)
  );
}
</script>

<template>
  <div id="comments-list">
        <div class="card m-2" id="comments-{{id}}">
            <div class="card-body">
              {{ comment.content }}
              <button
                v-if="comment.member.id==info.id"
                @click="deleteEvent(comment.id)">삭제</button>
            </div>
            <div class="card-header">
              {{ comment.member.name}}
            </div>
            <div class="card-footer">
              {{ comment.createdAt }}
            </div>
        </div>
  </div>
</template>

<style scoped>
#comments-list {
  margin-left: 100px;
  margin-right: 100px;
  text-align: left;
}

button {
  position: absolute;
  right: 50px;
}
</style>