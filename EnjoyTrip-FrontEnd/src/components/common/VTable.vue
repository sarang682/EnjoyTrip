<script setup>
defineProps({ heads: Array, datas: Array });
</script>

<template>
  <table class="table table-hover">
    <thead>
      <tr class="text-center">
        <th scope="col" v-for="(head, index) in heads" :key="index">{{ head }}</th>
      </tr>
    </thead>
    <tbody>
      <tr class="text-center">
        <!-- <th>{{ article.articleNo }}</th>
        <td>{{ article.subject }}</td>
        <td>{{ article.userName }}</td>
        <td>{{ article.hit }}</td>
        <td>{{ article.registerDate }}</td> -->
      </tr>
    </tbody>
  </table>
</template>

<style scoped></style>
