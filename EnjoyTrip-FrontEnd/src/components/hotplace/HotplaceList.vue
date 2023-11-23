<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from 'vue-router';
import { getHotplaces } from "@/api/hotplace";
import HotplaceListItem from "@/components/hotplace/HotplaceListItem.vue";

const router = useRouter();
const hotplaces = ref([]);
const isDeleted = router.params;
const member = ref({});

onMounted(() => {
    getHotplaceList();
});

if (isDeleted) {
    getHotplaceList();
}

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

const moveWrite = () => {
    router.push({ name: "hotplace-write", params: {writer: member.value} });
}

</script>

<template>
    <div>
        <button
            type="button" 
            class="btn btn-outline-dark btn-sm"
            @click="moveWrite" >
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
</style>