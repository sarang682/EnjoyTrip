<script setup>
import { ref, onMounted } from "vue";
import { attractionDescription } from "@/api/attraction";
import { changeBookmark } from "@/api/bookmark";
import { useRouter } from 'vue-router';

const router = useRouter();
const props = defineProps({ attraction: Object})
const attraction = props.attraction;
const isChanged = ref(false);

onMounted(() => {
    getAttractionDescription(attraction.attractionId);
});

const description = ref({});
const getAttractionDescription = (attractionId) => {
    attractionDescription(
        attractionId,
        ({ data }) => {
            description.value = data.result;
        },
        (error) => {
            console.log(error);
        }
    );
};

const changeBookmarkClickEvent = () => {
	if (description.value.logined) {
		changeBookmarkEvent(attraction.attractionId);
	} else {
		alert('로그인을 해주세요!!!');
		router.push("/user/login");
	}
}

const changeBookmarkEvent = (attractionId) => {
    changeBookmark(
        {
			"attractionId": attractionId
		},
        ({ data }) => {
			const action = data.result.action;
			if (action === 'insert') description.value.bookmarked = true;
			if (action === 'delete') description.value.bookmarked = false;
            isChanged.value = !isChanged.value;
        },
        (error) => {
            console.log(error);
        }
    );
}

</script>

<template>
    <div id="modal" class="modal-overlay">
        <div class="modal-window">
            <span class="bookmark-button" @click="changeBookmarkClickEvent">
                <font-awesome-icon
                    v-if="description.logined && description.bookmarked" 
                    icon="fa-solid fa-bookmark"
                    size="xl">
                </font-awesome-icon>
                <font-awesome-icon 
                    v-else 
                    icon="fa-regular fa-bookmark" 
                    size="xl">
                </font-awesome-icon>
            </span>

            <div class="description">
                <h5 class="title">{{attraction.title}}</h5>

                <div class="content">
                    <p v-if="description.overview">{{ description.overview }}</p>
                    <p v-else>상세 정보가 없습니다.</p>
                </div>

                <v-btn color="blue-grey" @click="$emit('closeModal', isChanged)">닫기</v-btn>
            </div>
        </div>
    </div>
</template>

<style scoped>
#modal.modal-overlay {
    width: 100%;
    height: 100%;
    position: fixed;
    left: 0;
    top:1;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    background: rgba(255, 255, 255, 0.25);
    box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37);
    backdrop-filter: blur(1.5px);
    -webkit-backdrop-filter: blur(1.5px);
    border-radius: 10px;
    border: 1px solid rgba(255, 255, 255, 0.18);
    z-index: 1000;
	/* overflow: auto; */
}

#modal .modal-window {
    width: 50%;
    margin: 80px auto;
    background: white;
    border-style: solid;
    border-color: black;
    border-radius: 10px;
    border-width: 2px;
    padding: 20px;
    box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37);
    backdrop-filter: blur(13.5px);
    -webkit-backdrop-filter: blur(13.5px);
    border-radius: 10px;
    border: 1px solid rgba(255, 255, 255, 0.18);
    /* width: 400px; */
    max-height: 400px;
    position: relative;
    /* top: -100px; */
    padding: 10px;
	/* display:flexbox; */
	/* overflow:auto; */
}

#modal .title {
    padding: 10px;
    display: inline;
}

#modal .content {
    margin-top: 20px;
    margin-bottom: 20px;
    padding: 0px 10px;
    max-height: 250px;
    overflow: auto;
    /* display: inline; */
}

.bookmark-button {
    display: inline;
    float: right;
    padding-right: 10px;
    cursor: pointer;
}
</style>