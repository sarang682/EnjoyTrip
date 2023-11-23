<script setup>
import { onMounted } from "vue";

var map;
const props = defineProps({ position: Object });

onMounted(() => {
    const script = document.createElement("script");
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${import.meta.env.VITE_KAKAO_KEY
        }&libraries=services,clusterer`;
    /* global kakao */
    script.onload = () => kakao.maps.load(() => initMap());
    document.head.appendChild(script);
});

const initMap = () => {
    const container = document.getElementById("map");

    const options = {
        center: new kakao.maps.LatLng(props.position.latitude, props.position.longitude),
        level: 6,
    };
    map = new kakao.maps.Map(container, options);

    var marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: new kakao.maps.LatLng(props.position.latitude, props.position.longitude), // 마커를 표시할 위치
        // image: markerImage, // 마커의 이미지
    });

    var infowindow = new kakao.maps.InfoWindow({
        content: '<div style="padding:5px;">'
            + '<div>x: ' + props.position.latitude + '</div>'
            + '<div>y: ' + props.position.longitude + '</div>'
    });

    // 마커에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'mouseover', function () {
        infowindow.open(map, marker);
    });

    kakao.maps.event.addListener(marker, 'mouseout', function () {
        infowindow.close();
    });
};

</script>

<template>
    <div id="map"></div>
</template>

<style>
#map {
    width: 100%;
    height: 500px;
}
</style>