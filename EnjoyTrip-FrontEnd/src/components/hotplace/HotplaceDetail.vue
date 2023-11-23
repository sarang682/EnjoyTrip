<script setup>
import { ref, onMounted } from "vue";
import { getHotplace, deleteHotplace } from "@/api/hotplace";
import { useRoute, useRouter } from "vue-router";
import { getMember } from "@/api/user";
import KakaoMap from "@/components/hotplace/KakaoMap.vue";
import img from '@/assets/no-image.jpeg';

const route = useRoute();
const router = useRouter();

const { hotplaceId } = route.params;

const hotplace = ref({});
const position = ref({});
const member = ref({}); // 지금 로그인한 사람 정보
const isDeleted = ref(false);

onMounted(() => {
    getMemberInfo();
    getHotplaceInfo();
});

const getMemberInfo = () => {
    getMember(
        (response) => {
            member.value = response.data['result'];
        },
        (error) => {
            console.log(error);
        }
    );
}

const getHotplaceInfo = () => {
    getHotplace(
        hotplaceId,
        (response) => {
            hotplace.value = response.data['result'];
            position.value = {
                latitude: hotplace.value.latitude,
                longitude: hotplace.value.longitude
            };
        },
        (error) => {
            console.log(error);
        }
    );
};

function onDeleteHotplace() {
    if (confirm("정말 삭제하시겠습니까?")) {
        deleteHotplace(
            hotplaceId,
            (response) => {
                isDeleted.value = true;
                alert("삭제되었습니다.");
                moveList();
            },
            (error) => {
                alert("허용되지 않은 접근입니다.");
            }
        );
    } else {
        alert("취소되었습니다.");
    }   
}

function moveList() {
    router.push({ name: "hotplace-list", params: {"isDeleted" : isDeleted.value} });
}

</script>

<template>
    <h2 class="font-weight-bold">📍 {{ hotplace.title }}</h2>
    <p class="type">&nbsp;&nbsp;{{ hotplace.attractionTypeName }}</p>
    <div class="row">
        <div class="col-6 mx-auto">
            <KakaoMap :position=position />
        </div>

        <div class="col-6" id="side">
            <img class="image" :src="hotplace.image || img">
            <div class="row">
                <div class="col-md-8">
                    <div class="divider mb-3"></div>
                    <div class="clearfix align-content-center">
                        <img class="avatar me-2 float-md-start bg-light p-2"
                            src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg" />
                        <p>
                            <span class="fw-bold">{{ hotplace.memberName }}</span> <br />
                            <span>{{ hotplace.createdAt }}</span>
                        </p>
                    </div>
                </div>

                <div class="divider mb-3"></div>

                <div>
                    {{ hotplace.content }}
                </div>
            </div>
        </div>

    </div>
    <div class="divider mt-3 mb-3"></div>
    <div class="d-flex justify-content-end">
        <button type="button" class="btn btn-outline-danger mb-3" v-if="hotplace.memberId == member.id"
            @click="onDeleteHotplace">
            삭제
        </button>
        <button type="button" class="btn btn-outline-primary mb-3 ms-1" @click="moveList">
            목록
        </button>
    </div>
</template>

<style scoped>
h3 {
    margin-left: 110px;
    text-align: left;
}

#input1 {
    margin-left: 110px;
    height: 50px;
    width: 950px;
    border-top: none;
    border-left: none;
    border-right: none;
    border-bottom: 3px solid black;
}

#btn {
    width: 100px;
    height: 50px;
    border-top: 3px solid black;
    border-left: 3px solid black;
    border-right: 3px solid black;
    border-bottom: 3px solid black;
}

.image {
    width: 100%;
    /* 이미지가 부모 컨테이너에 100%로 맞춰지도록 설정 */
    height: auto;
    /* 비율 유지를 위해 자동으로 높이 계산 */
    display: block;
    /* inline 요소 사이 간격 문제를 해결하기 위해 block으로 설정 */
}

h2 {
    display: inline;
}

.type {
    display: inline-block;
}
</style>
