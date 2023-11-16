<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";

// const url = import.meta.env.VITE_ELECTRIC_CHARGING_STATION_URL;
// const serviceKey = import.meta.env.VITE_OPEN_API_SERVICE_KEY;
const { VITE_ELECTRIC_CHARGING_STATION_URL } = import.meta.env;
const { VITE_OPEN_API_SERVICE_KEY } = import.meta.env;

const chargingStations = ref([]);

onMounted(() => {
  getChargingStations();
});

const getChargingStations = () => {
  axios
    .get(VITE_ELECTRIC_CHARGING_STATION_URL, {
      params: {
        serviceKey: VITE_OPEN_API_SERVICE_KEY,
        pageNo: 1,
        numOfRows: 20,
        zcode: 11,
      },
    })
    .then(({ data }) => {
      //   console.log(data.items[0].item);
      chargingStations.value = data.items[0].item;
    })
    .catch((err) => {
      console.log(err);
    });
};
</script>

<template>
  <div class="container text-center mt-3">
    <div class="alert alert-success" role="alert">전기차 충전소</div>
    <table class="table table-hover">
      <thead>
        <tr class="text-center">
          <th scope="col">충전소명</th>
          <th scope="col">충전소ID</th>
          <th scope="col">충전기상태</th>
          <th scope="col">위치</th>
          <th scope="col">위도</th>
          <th scope="col">경도</th>
        </tr>
      </thead>
      <tbody>
        <tr class="text-center" v-for="station in chargingStations" :key="station.statId">
          <th>{{ station.statNm }}</th>
          <td>{{ station.statId }}</td>
          <td>{{ station.stat }}</td>
          <td>{{ station.addr }}</td>
          <td>{{ station.lat }}</td>
          <td>{{ station.lng }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style>
mark.purple {
  background: linear-gradient(to top, #c354ff 20%, transparent 30%);
}
</style>
