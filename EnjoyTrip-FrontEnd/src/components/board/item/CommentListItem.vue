<script setup>
defineProps({ comment: Object });
</script>

<template>
  <!-- <tr class="text-center">
    <th>{{ comment.id }}</th>
    <td>{{ comment.content }}</td>
    <td>{{ comment.member.name}}</td>
    <td>{{ comment.createdAt }}</td>
  </tr> -->
  <div id="comments-list">
        <div class="card m-2" id="comments-{{id}}">
            <div class="card-body">
              {{ comment.content }}
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
</style>