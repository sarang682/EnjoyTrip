<script setup>

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
            <HotplaceListItem />
        </v-row>
    </v-container>
</template>

<style scoped>
.write-btn {
 border-radius: 50%;
}
</style>