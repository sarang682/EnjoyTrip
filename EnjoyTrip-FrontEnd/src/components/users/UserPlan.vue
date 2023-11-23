<script setup>
import { onMounted, ref } from 'vue';
import PlanListItem from '@/components/attraction/PlanListItem.vue';
import { planList } from '@/api/plan';
import { useRouter } from "vue-router";

onMounted(() => {
    getPlanList();
});

const plans = ref([]);
const router = useRouter();

const getPlanList = () => { // 계획 목록 가져오기
    planList(
        (response) => {
            plans.value=response.data['result'];
            console.log(plans.value);
        },
        (error) => {
            console.error(error);
        }
    );
}

const clickPlan = (planId,title) => {
    router.push({
        name:"plan-view", 
        params: {
          planid: planId,
          title: title,
        }
    });
}


</script>

<template>
    <div class="container col-12">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="orange">나의 여행 계획</mark>
        </h2>
      </div>
      <v-row>
        <PlanListItem
            v-for="(plan, index) in plans"
            :key="plan.id"
            :plan="plan"
            :index=index
            @click="clickPlan(plan.id,plan.title)"
        />
      </v-row>
    </div>
  </div>
</template>

<style scoped>
.card {
    margin: 50px;
}
</style>