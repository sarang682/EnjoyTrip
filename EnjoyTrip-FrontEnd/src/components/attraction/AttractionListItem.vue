<script setup>
defineProps({ attraction: Object });
import img from '@/assets/noimage.jpg'
</script>

<template>
    <v-card id="card" class="mx-auto" width="220" height="350">
        <v-img 
        class="align-end text-white" 
        height="200" 
        :src="attraction.firstImage || img"  cover>
        </v-img>

        <v-card-title>
            {{ attraction.title }}
        </v-card-title>

        <v-card-text>
            <div>{{ attraction.addr1 }}</div>
        </v-card-text>

        <v-card-actions>
            <v-btn color="blue-grey">
                위치보기
            </v-btn>

            <v-btn color="blue-grey">
                상세보기
            </v-btn>
        </v-card-actions>
    </v-card>
</template>

<style scoped>
#card{
    margin-top: 5px;
    margin-bottom: 5px;
}
</style>
