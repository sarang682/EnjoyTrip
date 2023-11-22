<script setup>
import { ref, onMounted } from "vue";
import { attractionDescription } from "@/api/attraction";
const props = defineProps({ attractionId: String })

onMounted(() => {
    getAttractionDescription(props.attractionId);
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

</script>

<template>
    <div id="modal" class="modal-overlay">
        <div class="modal-window">
            <div class="top-button">
                <span class="bookmark-button">
                    <font-awesome-icon icon="fa-regular fa-bookmark" size="xl" />
                </span>
                <span class="close-button" @click="$emit('close-modal')">
                    <font-awesome-icon icon="fa-solid fa-circle-xmark" size="xl" style="color: #607D8B;" />
                </span>
            </div>
            <div class="title">
                <h5>상세 정보</h5>
            </div>

            <div class="content">
                <p v-if="description.overview">{{ description.overview }}</p>
                <p v-else>상세 정보가 없습니다.</p>
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
    top: 1;
    display: flex;
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
    max-height: 500px;
    /* height: 500px; */
    position: relative;
    top: -100px;
    padding: 10px;
}

#modal .title {
    padding-left: 10px;
    display: inline;
    /* text-shadow: 1px 1px 2px #808080; */
    /* color: black; */

}

#modal .title h2 {
    display: inline;
}

#modal .top-button {
    display: inline;
    float: right;
    padding-right: 10px;
}

#modal .content {
    margin-top: 20px;
    padding: 0px 10px;
    height: 80%;
    overflow: auto
}

.bookmark-button {
    margin-right: 10px;
    cursor: pointer;
}

.close-button {

    cursor: pointer;
}
</style>