<script setup>
defineProps({ attraction: Object });
</script>

<template>
    <tr class="text-center">
        <td><img :src="attraction.firstimage" 
                style="width:100px;"></td>
        <td>{{ attraction.title }}</td>
        <td>{{ attraction.addr1 }} {{ attraction.addr2 }}</td>
        <td>{{ attraction.mapy }}</td>
        <td>{{ attraction.mapx }}</td>
    </tr>
</template>

<style scoped></style>
