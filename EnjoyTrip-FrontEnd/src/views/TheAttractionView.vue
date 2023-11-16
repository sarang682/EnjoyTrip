<script setup>
import { ref, onMounted } from "vue";
import { listSido, listAttraction } from "@/api/attraction";
import AttractionListItem from "@/components/attraction/AttractionListItem.vue";

import VSelect from "@/components/common/VSelect.vue";
import VKakaoMap from "@/components/common/VKakaoMap.vue";

onMounted(() => {
    getSidoList();
});

const attractions = ref([]);
const selectAttraction = ref({});

const sidos = ref([
    {
        text: "검색할 지역 선택",
        value: "",
    },
]);

const contentTypes = [
    {
        text: "관광지 유형",
        value: "",
    },
    {
        text: "관광지",
        value: "12",
    },
    {
        text: "문화시설",
        value: "14",
    },
    {
        text: "축제공연행사",
        value: "15",
    },
    {
        text: "여행코스",
        value: "25",
    },
    {
        text: "레포츠",
        value: "28",
    },
    {
        text: "숙박",
        value: "32",
    },
    {
        text: "쇼핑",
        value: "38",
    },
    {
        text: "음식점",
        value: "39",
    },
]

const param = ref({
    areaCode: "",
    contentTypeId: "",
    word: "",
});

const changeAreaCode = (val) => {
    param.value.areaCode = val;
};

const changeContentTypeId = (val) => {
    param.value.contentTypeId = val;
};

const getSidoList = () => {
    listSido(
        ({ data }) => {
            makeOption(data);
        },
        (error) => {
            console.log(error);
        }
    );
};

function makeOption(data) {
    let areas = data.response.body.items.item;
    areas.forEach((area) => {
        sidos.value.push({
            text: area.name, 
            value: area.code
        }); 
    })
}

const getAttractionList = () => {
    listAttraction(
        param.value.areaCode,
        param.value.contentTypeId,
        param.value.word,
        ({ data }) => {
            attractions.value = data.response.body.items.item;
        },
        (error) => {
            console.log(error);
        }
    );
};

const viewAttraction = (attraction) => {
    selectAttraction.value = attraction;
}
</script>


<template>
    <div class="container">
        <div style="height: 70px"></div>
        <div class="row">
            <!-- 중앙 center content end -->
            <div class="col-md-9 mx-auto">
                <div class="alert mt-3 text-center fw-bold border-bottom" role="alert">
                    전국 관광지 정보
                </div>
                <!-- 관광지 검색 start -->
                <form class="d-flex my-3" onsubmit="return false;" role="search">
                    <VSelect class="me-2" :selectOption="sidos" @onKeySelect="changeAreaCode" />
                    <VSelect class="me-2" :selectOption="contentTypes" @onKeySelect="changeContentTypeId" />
                    <input id="search-keyword" class="form-control form-control-sm me-2" type="search" 
                        placeholder="검색어" aria-label="검색어" v-model="param.word" />
                    <button id="btn-search" class="btn btn-outline-success" type="button"
                        @click="getAttractionList">검색</button>
                </form>
                <!-- kakao map start -->
                <!-- <div id="map" class="mt-3" style="width: 100%; height: 400px"></div> -->
                <VKakaoMap :attractions="attractions" :selectAttraction="selectAttraction"/>
                <!-- kakao map end -->
                <div class="row">
                    <table class="table table-striped">
                        <thead>
                            <tr v-if="attractions.length !== 0">
                                <th>대표이미지</th>
                                <th>관광지명</th>
                                <th>주소</th>
                                <th>위도</th>
                                <th>경도</th>
                            </tr>
                        </thead>
                        <tbody id="trip-list">
                            <AttractionListItem 
                                v-for="attraction in attractions"
                                :key="attraction.contentid"
                                :attraction="attraction"
                                @click="viewAttraction(attraction)"
                                />
                        </tbody>
                    </table>
                </div>
                <!-- 관광지 검색 end -->
            </div>
        </div>
        <!-- 중앙 content end -->
    </div>
</template>

<style scoped>

</style>