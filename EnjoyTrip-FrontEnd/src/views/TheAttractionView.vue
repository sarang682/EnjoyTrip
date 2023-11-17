<script setup>
import { ref, onMounted } from "vue";
import { listSido, listGugun, listContentType, listAttractionInfo } from "@/api/attraction";
import AttractionListItem from "@/components/attraction/AttractionListItem.vue";
import KakaoMap from "@/components/attraction/KakaoMap.vue";

import VSelect from "@/components/common/VSelect.vue";

onMounted(() => {
    getSidoList();
    getContentTypeList();
});

const attractions = ref([]);
const selectAttraction = ref({});

const sidos = ref([
    {
        text: "검색할 시도 선택",
        value: "",
    },
]);

const guguns = ref([
    {
        text: "검색할 구군 선택",
        value: "",
    },
]);

var contentTypes = ref([
    {
        text: "관광지 유형",
        value: "",
    },
]);

const param = ref({
    sidoCode: "",
    gugunCode: "",
    contentTypeId: "",
});

const changeSidoCode = (val) => {
    param.value.sidoCode = val;
    guguns.value.length = 1;
    getGugunList(val);
};

const changeGugunCode = (val) => {
    param.value.gugunCode = val;
}

const changeContentTypeId = (val) => {
    param.value.contentTypeId = val;
};

const getSidoList = () => {
    listSido(
        ({ data }) => {
            data.forEach((sido) => {
                sidos.value.push({
                    text: sido.sidoName,
                    value: sido.sidoCode
                });
            })
        },
        (error) => {
            console.log(error);
        }
    );
};

const getGugunList = (sidoCode) => {
    listGugun(
        {
            "sido-code": sidoCode
        },
        ({ data }) => {
            data.forEach((gugun) => {
                guguns.value.push({
                    text: gugun.gugunName,
                    value: gugun.gugunCode
                });
            })
        },
        (error) => {
            console.log(error);
        }
    );
};

const getContentTypeList = () => {
    listContentType(
        ({ data }) => {
            data.forEach((contentType) => {
                contentTypes.value.push({
                    text: contentType.contentTypeName,
                    value: contentType.contentTypeId
                });
            })
        },
        (error) => {
            console.log(error);
        }
    );
};

const getAttractionList = () => {
    listAttractionInfo(
        {
            "sido-code": param.value.sidoCode,
            "gugun-code": param.value.gugunCode,
            "content-type-id": param.value.contentTypeId
        },
        ({ data }) => {
            attractions.value = data;
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
                    <VSelect class="me-2" :selectOption="sidos" @onKeySelect="changeSidoCode" />
                    <VSelect class="me-2" :selectOption="guguns" @onKeySelect="changeGugunCode" />
                    <VSelect class="me-2" :selectOption="contentTypes" @onKeySelect="changeContentTypeId" />
                    <v-btn id="btn-search" color="blue-grey" type="button" @click="getAttractionList">검색</v-btn>
                </form>
                <!-- kakao map start -->
                <KakaoMap :attractions="attractions" :selectAttraction="selectAttraction" />
                <!-- kakao map end -->
                <v-container id="trip-list">
                    <v-row align="center" justify="center">
                        <AttractionListItem 
                            v-for="attraction in attractions" 
                            :key="attraction.contentId"
                            :attraction="attraction" 
                            @click="viewAttraction(attraction)" 
                            cols="12" md="4" sm="4" />
                    </v-row>
                </v-container>
            </div>
        </div>
        <!-- 중앙 content end -->
    </div>
</template>

<style scoped>
</style>