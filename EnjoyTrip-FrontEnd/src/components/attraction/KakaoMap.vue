<script setup>
import { ref, watch, onMounted } from "vue";

var map;
const positions = ref([]);
const markers = ref([]);
const orderedMarkers = ref([]);
var polyline = ref();

const props = defineProps({
  attractions: Array,
  selectAttraction: Object,
  attractionPos: Object,
  isPlan: Boolean,
});

// 순서있는 마커 만들기 !!
const togoLine = (point) => {

  if (orderedMarkers.value.length > 0) {
    orderedMarkers.value.forEach((marker) => marker.setMap(null));
  }
  togoDeleteLine(); // 이전 polyline 제거

  const linePath = [];
  for (let pos of point) {
    linePath.push(new kakao.maps.LatLng(pos.latitude, pos.longitude));
  }

  orderedMarkers.value = [];
  for (var i = 0; i < linePath.length; i++) {
    var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png';
    var imageSize = new kakao.maps.Size(36, 37);
    var imgOptions = {
      spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
      spriteOrigin: new kakao.maps.Point(0, (i * 46) + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
      offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
    }

    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions);
    var markerPosition = linePath[i];
    var m = new kakao.maps.Marker({
      map: map, // 마커를 표시할 지도
      position: markerPosition,
      image: markerImage // 마커이미지 설정 
    });
    orderedMarkers.value.push(m);
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
    // 선 긋기
    togoLine(props.attractionPos);
  },
  { deep: true }
)

watch(
  () => props.selectAttraction.value,
  () => {
    // 이동할 위도 경도 위치를 생성합니다
    var moveLatLon = new kakao.maps.LatLng(props.selectAttraction.latitude, props.selectAttraction.longitude);
    map.setLevel(2);
    map.panTo(moveLatLon);
  },
  { deep: true }
);

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement("script");
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${import.meta.env.VITE_KAKAO_KEY
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
      obj.latitude=attraction.latitude,
      obj.longitude=attraction.longitude,
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
    center: new kakao.maps.LatLng(36.5, 127.5),
    level: 13,
  };
  map = new kakao.maps.Map(container, options);
  if(props.isPlan) {
    console.log("계획 맵 실행..@@@@")

    positions.value = [];
    props.attractions.forEach((attraction) => {
      let obj = {};
      obj.latlng = new kakao.maps.LatLng(attraction.latitude, attraction.longitude);
      obj.latitude=attraction.latitude,
      obj.longitude=attraction.longitude,
      obj.title = attraction.title;

      positions.value.push(obj);
    });
    togoLine(positions.value);
    const bounds = positions.value.reduce(
    (bounds, position) => bounds.extend(position.latlng),
    new kakao.maps.LatLngBounds()
    );
    map.setBounds(bounds);
  }

};

const loadMarkers = () => {
  deleteMarkers();

  markers.value = [];
  positions.value.forEach((position) => {
    const marker = new kakao.maps.Marker({
      map: map,
      position: position.latlng,
      title: position.title,
      clickable: true,
    });
    markers.value.push(marker);
  });

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