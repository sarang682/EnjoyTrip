<script setup>
import { onMounted, ref } from 'vue';
import {attractionList} from '@/api/plan';
import KakaoMap from './KakaoMap.vue';
import AttractionListItem from "@/components/attraction/AttractionListItem.vue";

const props = defineProps({
    planid:Number,
    title:String,
});
const attractions = ref([]);
const planTitle=ref("");
onMounted( () => {
    console.log(props.planid);
    getInfo(props.planid);
    planTitle.value=props.title;
})

// 데이터 가져오기
const getInfo = (planid) => {
    attractionList(
        planid,
        (response) => {
            const data = response.data['result'];
            data.forEach((i)=>{
                console.log(i.attraction);
                attractions.value.push(i.attraction);
                planTitle.value=i.plan.title;
            })
        },
        (error) => {
            console.error(error);
        }
    )
}

</script>

<template>
    <div class="container">
        <div class="row">
            <div class="col-9 mx-auto">
                <div class="alert mt-3 text-center fw-bold border-bottom" role="alert">
                    제목: {{ planTitle }}
                </div>
                <!-- 카카오맵 -->
                <KakaoMap 
                :attractions="attractions"
                :isPlan=true
                :selectAttraction="attractions"
                :attractionPost=null
                />
            </div>
            <!-- 여행지 -->
            <div class="col-3" id="side">
                <div class="alert mt-3 text-center fw-bold border-bottom" role="alert">
                    내가 선택한 여행지
                </div>
                <v-container id="trip-list">
                    <v-row>
                        <AttractionListItem 
                            cols="12" md="4" sm="4"
                            v-for="(attraction, index) in attractions" 
                            :key="attraction.attractionId"
                            :attraction="attraction"
                            :isPlanList=true
                            :index=index
                            @show-description="showDescription"
                            @go-attraction="goAttraction"
                            />
                    </v-row>
                </v-container>
            </div>
        </div>
    </div>
</template>