<script setup>
import { ref, onMounted } from "vue";
import { getBookmarkList } from "@/api/bookmark";
import AttractionDetail from "@/components/attraction/AttractionDetail.vue";
import AttractionListItem from "@/components/attraction/AttractionListItem.vue";

const attractions = ref([]);

onMounted(() => {
    getBookmarks();
});

const openModal = ref(false);
const attraction = ref("");

const getBookmarks = () => {
    getBookmarkList(
            ({ data }) => {
                attractions.value = data.result.attractions;
            },
            (error) => {
                console.log(error);
            }
        );
}

const showDescription = (data) => {
    openModal.value = true;
    attraction.value = data;
}

const closeModal = (isChanged) => {
    openModal.value = false;
    if (isChanged) {
        getBookmarks();
    }
}
</script>

<template>
    <AttractionDetail 
        v-if="openModal" 
        @close-modal="closeModal" 
        class="modal-body"
        :attraction="attraction" />
    <div class="container">
        <div class="row">
            <div class="col-md-9 mx-auto">
                <div class="alert mt-3 text-center fw-bold border-bottom" role="alert">
                    즐겨찾기 정보
                </div>          
                <!-- 즐겨찾기 리스트 -->
                <v-container id="trip-list">
                    <v-row>
                        <AttractionListItem 
                            v-for="attraction in attractions" 
                            :key="attraction.attractionId"
                            :attraction="attraction" 
                            @show-description="showDescription" />
                    </v-row>
                </v-container>
            </div>
        </div>
    </div>
</template>

<style scoped>

</style>