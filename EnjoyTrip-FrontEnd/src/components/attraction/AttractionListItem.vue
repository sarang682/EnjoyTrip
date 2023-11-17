<script setup>
import img from '@/assets/noimage.jpg';
const props = defineProps({ attraction: Object });

const emit = defineEmits(["show-description"]);
const showDescription = () => {
    emit("show-description", props.attraction.contentId)
}
</script>

<template>
    <v-hover v-slot="{ isHovering, props }">
        <v-card id="card" class="mx-auto" width="220" height="320" v-bind="props">
            <v-img 
                class="align-end text-white" 
                height="200" 
                :src="attraction.firstImage || img" 
                cover>
            </v-img>

            <v-card-title>
                {{ attraction.title }}
            </v-card-title>

            <v-card-text>
                <div>{{ attraction.addr1 }}</div>
            </v-card-text>

            <!-- <v-card-actions>
                <v-btn color="blue-grey">
                    view detail
                </v-btn>
            </v-card-actions> -->

            <v-overlay 
                :model-value="isHovering" 
                contained 
                scrim="blue-grey" 
                class="align-center justify-center">
                <v-btn variant="flat" @click="showDescription">See more info</v-btn>
            </v-overlay>
        </v-card>
    </v-hover>
</template>

<style scoped>
#card {
    margin-top: 5px;
    margin-bottom: 5px;
}
</style>
