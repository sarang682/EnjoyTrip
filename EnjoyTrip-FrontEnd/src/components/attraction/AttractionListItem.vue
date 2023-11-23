<script setup>
import { computed } from 'vue';
import img from '@/assets/noimage.jpg';
const props = defineProps({ attraction: Object, isPlanList:Boolean, index:Number});

const emit = defineEmits(["show-description"]);
const showDescription = () => {
    emit("show-description", props.attraction)
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

const addAttraction = () => {
    emit("add-attraction", props.attraction);
}

const delAttraction = () => {
    emit("del-attraction", props.attraction);
}

const goAttraction = () => {
    emit("go-attraction", props.attraction);
}

</script>

<template>
    <p v-if="isPlanList"> {{props.index + 1}} 번째 여행지  </p>
    <v-hover v-slot="{ isHovering, props }">
        <v-card id="card" class="mx-auto" width="220" height="350" v-bind="props">
            <v-img class="align-end text-white" height="200" :src="attraction.firstImage || img" cover>
            </v-img>

            <span class="bookmark-icon">
                <font-awesome-icon
                    v-if="attraction.logined && attraction.bookmarked" 
                    icon="fa-solid fa-bookmark"
                    style="color: #fdc93a;"
                    size="xl">
                </font-awesome-icon>
                <font-awesome-icon 
                    v-else 
                    icon="fa-regular fa-bookmark" 
                    size="xl">
                </font-awesome-icon>
            </span>

            <v-card-title>
                {{ attraction.title }}
            </v-card-title>

            <v-card-text>
                <div v-if="address">🏠 {{ address }}</div>
                <div v-if="attraction.tel">📞 {{ telephone }}</div>
                <div v-if="!address && !attraction.tel">정보가 없습니다.</div>
            </v-card-text>

            <v-overlay :model-value="isHovering" contained scrim="blue-grey" class="align-center justify-center">
                <div>
                    <v-btn variant="flat" @click="showDescription">See more info</v-btn>
                </div>
                <span>
                    <v-btn v-if="!isPlanList" id="btn" variant="flat" @click="addAttraction">담기</v-btn>
                    <v-btn v-if="isPlanList" id="btn" variant="flat" @click="delAttraction">삭제</v-btn>
                    <v-btn variant="flat" id="btn" @click="goAttraction">위치</v-btn>
                </span>
                
            </v-overlay>
        </v-card>
    </v-hover>
</template>

<style scoped>
#card {
    margin-top: 5px;
    margin-bottom: 5px;
}

.bookmark-icon {
    display: inline;
    float: right;
    padding-top: 10px;
    padding-right: 10px;
}

#btn {
    margin: 5px;
}

p {
    font-size: large;
    border: solid gray 3px;
    margin-left: 15px;
    margin-top: 20px;
}
</style>
