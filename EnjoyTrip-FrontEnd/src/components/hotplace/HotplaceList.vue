<script setup>
import { ref, onMounted } from "vue";
import { getHotplaces } from "@/api/hotplace";

import HotplaceListItem from "@/components/hotplace/HotplaceListItem.vue";

const hotplaces = ref([]);

onMounted(() => {
    getHotplaceList();
});

const getHotplaceList = () => {
    getHotplaces(
        ({ data }) => {
            hotplaces.value = data.result;
        },
        (error) => {
            console.log(error);
        }
    );
};

</script>

<template>
    <div>
        <button
            type="button" 
            class="btn btn-outline-dark btn-sm" >
            글쓰기
        </button>
    </div>
    <!-- 여행지 리스트 -->
    <v-container id="trip-list">
        <v-row>
            <HotplaceListItem 
                v-for="hotplace in hotplaces" 
                :key="hotplace.hotplaceId"
                :hotplace="hotplace" >
            </HotplaceListItem>
        </v-row>
    </v-container>
</template>

<style scoped>
.write-btn {
 border-radius: 50%;
}
</style>