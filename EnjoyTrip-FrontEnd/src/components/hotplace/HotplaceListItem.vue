<script setup>
const props = defineProps({ hotplace: Object})
console.log(props.hotplace);
</script>

<template>
    <v-card
        class="mx-auto"
        width="200"
        height="200"
        :image="props.hotplace.image"
        :title="props.hotplace.title"
        theme="dark">
    </v-card>
</template>

<style scoped>

</style>