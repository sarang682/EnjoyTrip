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
        <button @click="$emit('close-modal')">닫기</button>
    </div>
</div>
</template>

<style scoped>
.black-bg {
	width: 100%; 
	height: 100%;
	background: rgba(0, 0, 0, 0.6);
	position: fixed;
}

.white-bg {
	width: 90%;
	margin: 80px auto;
	background: white;
	border-radius: 5px;
	padding: 20px 0;
}

.close {
	cursor: pointer;
	border: none;
	background: #6667AB;
	color: white;
	font-weight: bold;
	border-radius: 5px;
	padding: 5px 15px;
}

.close:hover {
	color:white;
	font-weight: bold;
	transform: scale(1.1);
	transition: all 0.5s;
}
</style>