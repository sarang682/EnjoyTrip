<script setup>
const props = defineProps({ plan: Object, index:Number});

const setTitle = {
    title : props.plan.title
}
</script>

<!-- <template>
    <v-card class="col-5 card"  prepend-icon="mdi-home" v-bind="props">
         <template v-slot:title> 제목 : {{plan.title}}</template>
        <v-card-text>작성일 : {{ plan.createdAt }}</v-card-text>
    </v-card> -->

<template>
    <div class="col-3">
        <v-card
        class="mx-auto"
        width="200"
        height="200"
        image="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
        theme="dark"
    >
    <v-card-title>{{ plan.title }}</v-card-title>
    <v-card-subtitle>{{ plan.createdAt }}</v-card-subtitle>
    </v-card>
    </div>
</template>

<style scoped>
div {
    margin-top: 50px;
}
</style>