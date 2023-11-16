<script setup>
defineProps({ attraction: Object });
</script>

<template>
    <tr class="text-center">
        <td><img :src="attraction.firstImage" 
                style="width:100px;"></td>
        <td>{{ attraction.title }}</td>
        <td>{{ attraction.addr1 }} {{ attraction.addr2 }}</td>
        <td>{{ attraction.latitude }}</td>
        <td>{{ attraction.longitude }}</td>
    </tr>
</template>

<style scoped></style>
