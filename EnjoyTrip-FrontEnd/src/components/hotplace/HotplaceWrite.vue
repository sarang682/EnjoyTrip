<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { postHotplace } from "@/api/hotplace";
import { getMember } from "@/api/user"
import SearchMap from "@/components/hotplace/SearchMap.vue";
import $ from 'jquery';

const router = useRouter();

const hotplace = ref({})
const hotplaceId = ref();
const writer = ref();

onMounted(() => {
    getMemberInfo();
});

const getMemberInfo = () => {
    getMember(
        (response) => {
            writer.value = response.data.result.name;
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
    router.push({ name: "hotplace-view", params: { "hotplaceId": hotplaceId.value } })
}

function moveList() {
    router.push({ name: "hotplace-list" });
}

function handleFileChange(event) {
    $("#imagePreview").empty();
    // 파일 선택 시 호출되는 함수
    hotplace.value.image = event.target.files[0];

    if (!hotplace.value.image.type.match("image/.*")) {
        alert("이미지 확장자만 업로드 가능합니다.");
        return;
    }

    var reader = new FileReader();
    reader.onload = function (e) {
        var html = `<img src=${e.target.result} style="width: 100px; height: 100px"/>`;
        $("#imagePreview").append(html);
    };
    reader.readAsDataURL(hotplace.value.image);

    console.log("file - ", hotplace.value.image);
}
</script>

<template>
    <div class="row">
        <div class="col-6 mx-auto">
            <SearchMap />
        </div>
        <div class="col-6" id="side">
            <input type="file" @change="handleFileChange" />
            <div id="imagePreview">
                <img class="img-responsive center-block" id="img" src="@/assets/no-image.jpeg" style="width: 100px; height: 100px" />
            </div>
            <form @submit.prevent="onSubmit">
        <div>
            <label for="userid" class="form-label">작성자</label>
            <input type="text" class="form-control" v-model="writer" :disabled="true" placeholder="작성자 이름" />
        </div>
        <div>
            <label for="subject" class="form-label">제목</label>
            <input type="text" class="form-control" v-model="hotplace.title" placeholder="제목" />
        </div>
        <div>
            <label for="content" class="form-label">내용</label>
            <textarea class="form-control" v-model="hotplace.content" rows="10"></textarea>
        </div>
        <div class="divider mt-3 mb-3"></div>
        <div class="d-flex justify-content-end">
            <button type="submit" class="btn btn-outline-primary mb-3">
                작성
            </button>
            <button type="button" class="btn btn-outline-danger mb-3 ms-1" @click="moveList">
                목록
            </button>
        </div>
    </form>
        </div>
    </div>
</template>

<style scoped></style>
