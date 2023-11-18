<script setup>
import { ref, onMounted } from "vue";
import { attractionDescription } from "@/api/attraction";
const props = defineProps({ contentId: String })

onMounted(() => {
    console.log(props.contentId);
    getAttractionDescription(props.contentId);
});

const description = ref({});
const getAttractionDescription = (contentId) => {
    attractionDescription(
        contentId,
        ({ data }) => {
            description.value = data;
        },
        (error) => {
            console.log(error);
        }
    );
    console.log(description);
};
</script>

<template>
<div class="black-bg">
    <div class="white-bg">
        <p>{{ description.homepage }}</p>
        <p>{{ description.overview }}</p>
        <p>{{ description.telname }}</p>
        <v-btn color="blue-grey" type="button" @click="$emit('close-modal')">닫기</v-btn>
    </div>
</div>
</template>

<style scoped>
.black-bg {
	width: 100%; 
	height: 100%;
	background: rgba(0, 0, 0, 0.6);
	position: fixed;
	z-index: 1000;
}

.white-bg {
	width: 50%;
	margin: 80px auto;
	background: white;
	border-style : solid;
	border-color: black;
	border-radius: 10px;
	border-width: 2px;
	padding: 20px;
}
</style>