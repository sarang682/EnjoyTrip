<script setup>
import { computed } from 'vue';
import img from '@/assets/noimage.jpg';
const props = defineProps({ attraction: Object });

const emit = defineEmits(["show-description"]);
const showDescription = () => {
    emit("show-description", props.attraction.attractionId)
}

const telephone = computed(() => {
    return props.attraction.tel.replace("<br />", " / ");
})

const address = computed(() => {
    var addr = "";
    if (props.attraction.addr1) {
        addr = props.attraction.addr1 + " ";
    }
    return addr + props.attraction.addr2;
})

console.log(telephone);

</script>

<template>
    <v-hover v-slot="{ isHovering, props }">
        <v-card id="card" class="mx-auto" width="220" height="350" v-bind="props">
            <v-img class="align-end text-white" height="200" :src="attraction.firstImage || img" cover>
            </v-img>

            <v-card-title>
                {{ attraction.title }}
            </v-card-title>

            <v-card-text>
                <div v-if="address">🏠 {{ address }}</div>
                <div v-if="attraction.tel">📞 {{ telephone }}</div>
                <div v-if="!address && !attraction.tel">정보가 없습니다.</div>
            </v-card-text>

            <v-card-actions>
                <v-btn size="small" color="surface-variant" variant="text" icon="mdi-heart"></v-btn>
            </v-card-actions>

            <!-- <v-card-actions>
                    <v-btn color="blue-grey">
                        view detail
                    </v-btn>
                </v-card-actions> -->

            <v-overlay :model-value="isHovering" contained scrim="blue-grey" class="align-center justify-center">
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
