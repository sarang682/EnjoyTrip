<script>
import axios from 'axios';

export default {
  data() {
    return {
      healthStatus: 'Checking...',
    };
  },
  mounted() {
    this.performHealthCheck();
  },
  methods: {
    async performHealthCheck() {
      try {
        // 여기에 실제 health check 로직을 구현
        // 예를 들면 API 호출, 서버 연결 등을 확인할 수 있습니다.

        const response = await axios.get('http://54.180.93.136:8080/enjoytrip/health'); //

        // 성공했을 때
        if (response.status === 200) {
          this.healthStatus = 'Healthy';
        } else {
          this.healthStatus = 'Unhealthy';
        }
      } catch (error) {
        // 실패했을 때
        console.error('Health check failed:', error);
        this.healthStatus = 'Unhealthy';
      }
    },
  },
};
</script>

<template>
  <div>
    <h1>Health Check</h1>
    <p>Status: {{ healthStatus }}</p>
  </div>
</template>

<style scoped></style>