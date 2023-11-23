<script setup>
import { useRouter } from "vue-router";

const router = useRouter();
const props = defineProps({ hotplace: Object });

const moveDetail = () => {
    router.push({ name: "hotplace-view", params: { hotplaceId: props.hotplace.hotplaceId } });
}
</script>

<template>
    <v-card 
        id="card" 
        class="mx-auto" 
        width="200" 
        height="200" 
        :image="hotplace.image" 
        :title="hotplace.title" 
        theme="dark"
        @click="moveDetail">
    </v-card>
</template>

<style scoped>
#card {
    margin-top: 5px;
    margin-bottom: 5px;
    text-shadow: 4px 4px 8px black;
}
</style>