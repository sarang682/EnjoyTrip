<script setup>
const props = defineProps({ plan: Object, index:Number});
</script>

<template>
    <v-card class="col-5 card"  prepend-icon="mdi-home" v-bind="props">
         <template v-slot:title> 제목 : {{plan.title}}</template>
        <v-card-text>작성일 : {{ plan.createdAt }}</v-card-text>
    </v-card>
</template>