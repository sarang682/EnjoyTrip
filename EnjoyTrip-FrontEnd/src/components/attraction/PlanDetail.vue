<script setup>
import { onMounted, ref } from 'vue';
import {attractionList} from '@/api/plan';
import KakaoMap from './KakaoMap.vue';

const props = defineProps({planid:Number});
const attractions = ref([]);

onMounted( () => {
    console.log(props.planid);
    getInfo(props.planid);
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
                    제목:
                </div>
                <!-- 카카오맵 -->
                <KakaoMap 
                :attractions="attractions"
                :isPlan=true
                :selectAttraction="attractions"
                :attractionPost=null
                />
            </div>
        </div>
    </div>
</template>