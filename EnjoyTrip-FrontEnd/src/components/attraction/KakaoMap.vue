<script setup>
import { ref, watch, onMounted } from "vue";

var map;
const positions = ref([]);
const markers = ref([]);
var polyline=ref();

const props = defineProps({
  attractions: Array,
  selectAttraction: Object,
  attractionPos: Object,
});

const togoLine = () => {
  togoDeleteLine(); // 이전 polyline 제거

  const linePath = [];
  for (let pos of props.attractionPos) {
    linePath.push(new kakao.maps.LatLng(pos.latitude, pos.longitude));
  }

  // 새로운 polyline 생성
  const newPolyline = new kakao.maps.Polyline({
    path: linePath,
    strokeWeight: 5,
    strokeColor: '#FF0000',
    strokeOpacity: 1.0,
    strokeStyle: 'solid'
  });

// 지도에 새로운 polyline 표시
  newPolyline.setMap(map);

// ref 값 업데이트
  polyline.value = newPolyline;
};

// togoDeleteLine 함수 수정
const togoDeleteLine = () => {
  if (polyline.value instanceof kakao.maps.Polyline) {
    polyline.value.setMap(null); // 이전 polyline 제거
    polyline.value = null; // 레퍼런스 제거
  }
};

watch(
  () => props.attractionPos,
  () => {
    // 선 긋기!!!!!!!!!!
    // const linePath = [];
    // for (let pos of props.attractionPos) {
    //   linePath.push(new kakao.maps.LatLng(pos.latitude, pos.longitude));
    // }
    // // 지도에 표시할 선을 생성합니다
    // polyline = new kakao.maps.Polyline({
    // path: linePath, // 선을 구성하는 좌표배열 입니다
    // strokeWeight: 5, // 선의 두께 입니다
    // strokeColor: '#FF0000', // 선의 색깔입니다
    // strokeOpacity: 1.0, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
    // strokeStyle: 'solid' // 선의 스타일입니다
    // });
    // polyline.setMap(map);
    togoLine();
  },
  { deep: true }
)

// watch(
//   () => props.selectAttraction.value,
//   () => {
//     // 이동할 위도 경도 위치를 생성합니다
//     var moveLatLon = new kakao.maps.LatLng(props.selectAttraction.latitude, props.selectAttraction.longitude);

//     // 지도 중심을 부드럽게 이동시킵니다
//     // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
//     deleteMarkers();
//     map.panTo(moveLatLon);

//     // 선택한 관광지의 마커를 생성
//     markers.value = [];
//     const marker = new kakao.maps.Marker({
//       map: map, // 마커를 표시할 지도
//       position: moveLatLon, // 마커를 표시할 위치
//       title: props.selectAttraction.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됨.
//       clickable: true, // // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
//     });
//     markers.value.push(marker);
    
//   },
//   { deep: true }
// );

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement("script");
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
      import.meta.env.VITE_KAKAO_KEY
    }&libraries=services,clusterer`;
    /* global kakao */
    script.onload = () => kakao.maps.load(() => initMap());
    document.head.appendChild(script);
  }
});

watch(
  () => props.attractions.value,
  () => {
    positions.value = [];
    props.attractions.forEach((attraction) => {
      let obj = {};
      obj.latlng = new kakao.maps.LatLng(attraction.latitude, attraction.longitude);
      obj.title = attraction.title;

      positions.value.push(obj);
    });
    loadMarkers();
  },
  { deep: true } 
);

const initMap = () => {
  const container = document.getElementById("map");
  const options = {
    center: new kakao.maps.LatLng(37.500613, 127.036431),
    level: 3,
  };
  map = new kakao.maps.Map(container, options);

  // loadMarkers();
};

const loadMarkers = () => {
  // 현재 표시되어있는 marker들이 있다면 map에 등록된 marker를 제거한다.
  deleteMarkers();

  // 마커 이미지를 생성합니다
  //   const imgSrc = require("@/assets/map/markerStar.png");
  // 마커 이미지의 이미지 크기 입니다
  //   const imgSize = new kakao.maps.Size(24, 35);
  //   const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

  // 마커를 생성합니다
  markers.value = [];
  positions.value.forEach((position) => {
    const marker = new kakao.maps.Marker({
      map: map, // 마커를 표시할 지도
      position: position.latlng, // 마커를 표시할 위치
      title: position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됨.
      clickable: true, // // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
      // image: markerImage, // 마커의 이미지
    });
    markers.value.push(marker);
  });

  // 4. 지도를 이동시켜주기
  // 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);
  const bounds = positions.value.reduce(
    (bounds, position) => bounds.extend(position.latlng),
    new kakao.maps.LatLngBounds()
  );

  map.setBounds(bounds);
};

const deleteMarkers = () => {
  if (markers.value.length > 0) {
    markers.value.forEach((marker) => marker.setMap(null));
  }
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