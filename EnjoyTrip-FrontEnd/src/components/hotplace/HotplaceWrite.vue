<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { postHotplace } from "@/api/hotplace";
import { getMember } from "@/api/user"

const router = useRouter();

const hotplace = ref({})
const hotplaceId = ref();
const member = ref();

onMounted(() => {
    getMemberInfo();
});

const getMemberInfo = () => {
    getMember(
        (response) => {
            member.value=response.data['result'];
        },
        (error) => {
            console.log(error);
        }
  );
}


function onSubmit() {
    if (hotplace.value.title == "" || hotplace.value.content == "") {
        alert("내용을 확인해주세요");
    } else {
        writeHotplace();
    }
}

function writeHotplace() {
    const param = {
        title: hotplace.value.title,
        content: hotplace.value.content,
        image: hotplace.value.image,
        latitude: hotplace.value.latitude,
        longitude: hotplace.value.longitude,
        attractionTypeId: hotplace.value.attractionTypeId
    }
    postHotplace(
        param,
        (response) => {
            hotplaceId.value = response.result.hotplaceId;
            moveDetail();
        },
        (error) => alert("허용되지 않은 접근입니다.")
    );
}

function moveDetail() {
    router.push({ name: "article-view", params: { "hotplaceId": hotplaceId.value } })
}

function moveList() {
    router.push({ name: "article-list" });
}
</script>

<template>
    <input type="file" @change="handleFileChange" />
    <div >
        <img class="img-responsive center-block" id="img" src="@/assets/noimage.jpg " style="width: 50%; height: 50%" />
    </div>

    <form @submit.prevent="onSubmit">
        <div class="mb-3">
            <label for="userid" class="form-label">작성자 : </label>
            <input type="text" class="form-control" v-model="member.name" :disabled="true" placeholder="작성자 이름" />
        </div>
        <div class="mb-3">
            <label for="subject" class="form-label">제목 : </label>
            <input type="text" class="form-control" v-model="hotplace.title" placeholder="제목" />
        </div>
        <div class="mb-3">
            <label for="content" class="form-label">내용 : </label>
            <textarea class="form-control" v-model="hotplace.content" rows="10"></textarea>
        </div>
        <div class="col-auto text-center">
            <button type="submit" class="btn btn-outline-primary mb-3">
                작성
            </button>
            <button type="button" class="btn btn-outline-danger mb-3 ms-1" @click="moveList">
                목록
            </button>
        </div>
    </form>
</template>

<style scoped></style>
