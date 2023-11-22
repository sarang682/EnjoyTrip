<script setup>
import { ref, onMounted, watch } from "vue";
import { listSido, listGugun, listAttractionType, listAttractionInfo } from "@/api/attraction";

import AttractionDetail from "@/components/attraction/AttractionDetail.vue";
import AttractionListItem from "@/components/attraction/AttractionListItem.vue";
import KakaoMap from "@/components/attraction/KakaoMap.vue";
import VSelect from "@/components/common/VSelect.vue";

onMounted(() => {
    getSidoList();
    getAttractionTypeList();
});

const attractions = ref([]); // 지도에 표시된 관광지
const selectAttraction = ref({}); // 사용자가 누른 관광지
const attractionPlan = ref([]); // 여행 계획에 넣을 관광지

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

var attractionTypes = ref([
    {
        text: "관광지 유형",
        value: "",
    },
]);

const param = ref({
    sidoCode: "",
    gugunCode: "",
    attractionTypeId: "",
});

const changeSidoCode = (val) => {
    param.value.sidoCode = val;
    guguns.value.length = 1;
    getGugunList(val);
};

const changeGugunCode = (val) => {
    param.value.gugunCode = val;
}

const changeAttractionTypeId = (val) => {
    param.value.attractionTypeId = val;
};

const getSidoList = () => {
    listSido(
        ({ data }) => {
            data.result.forEach((sido) => {
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
            data.result.forEach((gugun) => {
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

const getAttractionTypeList = () => {
    listAttractionType(
        ({ data }) => {
            data.result.forEach((attractionType) => {
                attractionTypes.value.push({
                    text: attractionType.attractionTypeName,
                    value: attractionType.attractionTypeId
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
            "attraction-type-id": param.value.attractionTypeId
        },
        ({ data }) => {
            attractions.value = data.result;
        },
        (error) => {
            console.log(error);
        }
    );
};

// const viewAttraction = (attraction) => {
//     selectAttraction.value = attraction;
// }

const openModal = ref(false);
const attractionId = ref("");

const showDescription = (data) => {
    openModal.value = true;
    attractionId.value = data;
}

// *** 여행 계획하기 *** //

const addAttraction = (data) => {
    attractionPlan.value.push(data);
}

const delAttraction = (data) => {
    attractionPlan.value = attractionPlan.value.filter((obj) => obj !== data);
}
</script>


<template>
    <AttractionDetail 
        v-if="openModal" 
        @close-modal="openModal = false" 
        class="modal-body"
        :attractionId="attractionId" />
    <div class="container">
        <div class="row">
            <div class="col-9 mx-auto">
                <div class="alert mt-3 text-center fw-bold border-bottom" role="alert">
                    전국 관광지 정보
                </div>
                <!-- 관광지 검색 -->
                <form class="d-flex my-3" onsubmit="return false;" role="search">
                    <VSelect class="me-2" :selectOption="sidos" @onKeySelect="changeSidoCode" />
                    <VSelect class="me-2" :selectOption="guguns" @onKeySelect="changeGugunCode" />
                    <VSelect class="me-2" :selectOption="attractionTypes" @onKeySelect="changeAttractionTypeId" />
                    <v-btn id="btn-search" color="blue-grey" type="button" @click="getAttractionList">검색</v-btn>
                </form>
                <!-- kakao map -->
                <KakaoMap :attractions="attractions" :selectAttraction="selectAttraction" />
                <!-- 관광지 리스트 -->
                <v-container id="trip-list">
                    <v-row>
                        <AttractionListItem 
                            cols="12" md="4" sm="4"
                            v-for="attraction in attractions" 
                            :key="attraction.attractionId"
                            :attraction="attraction" 
                            :isPlanList=false
                            @show-description="showDescription"
                            @add-attraction="addAttraction"
                            />
                    </v-row>
                </v-container>
            </div>

            <!-- 여행 계획 코너 !-->
            <div class="col-3" id="side">
                
                <div class="alert mt-3 text-center fw-bold border-bottom" role="alert">
                    여행 계획 하기
                </div>
                <v-container id="trip-list">
                    <v-row>
                        <AttractionListItem 
                            cols="12" md="4" sm="4"
                            v-for="attraction in attractionPlan" 
                            :key="attraction.attractionId"
                            :attraction="attraction"
                            :isPlanList=true
                            @show-description="showDescription"
                            @del-attraction="delAttraction"
                            />
                    </v-row>
                </v-container>

            </div>
        </div>
    </div>
</template>

<style scoped>
/* #side {
    background-color: aqua;
} */
</style>