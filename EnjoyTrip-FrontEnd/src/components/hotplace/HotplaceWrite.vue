<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { postHotplace } from "@/api/hotplace";
import { getMember } from "@/api/user"
import SearchMap from "@/components/hotplace/SearchMap.vue";
import $ from 'jquery';

const router = useRouter();

const hotplace = ref({})
const image = ref();
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
    for (let key in hotplace.value) {
        if (hotplace.value[key] == '') {
            alert(key + " 내용을 확인해주세요");
            return;
        }
    }
    
    const formData = new FormData();

    const param = {
        title: hotplace.value.title,
        content: hotplace.value.content,
        latitude: hotplace.value.latitude,
        longitude: hotplace.value.longitude,
        attractionTypeId: hotplace.value.attractionTypeId
    }

    formData.append(
        "postHotplaceRequest",
        new Blob([JSON.stringify(param)], {
            type: "application/json",
        })
    );
    formData.append("image", image.value);

    writeHotplace(formData);
}

function writeHotplace(formData) {
    postHotplace(
        formData,
        (response) => {
            moveList();
        },
        (error) => {
            console.log(error);
            alert("허용되지 않은 접근입니다.");
        }
    );
}

function moveList() {
    router.push({ name: "hotplace-list" });
}

function handleFileChange(event) {
    $("#imagePreview").empty();
    // 파일 선택 시 호출되는 함수
    image.value = event.target.files[0];

    if (!image.value.type.match("image/.*")) {
        alert("이미지 확장자만 업로드 가능합니다.");
        return;
    }

    var reader = new FileReader();
    reader.onload = function (e) {
        var html = `<img src=${e.target.result} style="width: 100px; height: 100px"/>`;
        $("#imagePreview").append(html);
    };
    reader.readAsDataURL(image.value);

    console.log("file - ", image.value);
}

const changeAttraction = (param) => {
    hotplace.value.latitude = param.position.lat;
    hotplace.value.longitude = param.position.lon;
}

const changeAttractionTypeId = (param) => {
    hotplace.value.attractionTypeId = param.attractionTypeId;
}
</script>

<template>
    <div class="row">
        <div class="col-6 mx-auto">
            <SearchMap 
                @change-attraction="changeAttraction"
                @change-attraction-type-id="changeAttractionTypeId"
            />
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
            <textarea class="form-control" v-model="hotplace.content" rows="16"></textarea>
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
