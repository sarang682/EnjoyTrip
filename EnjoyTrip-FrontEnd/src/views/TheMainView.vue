<script>
import { defineComponent } from "vue";
import { Carousel, Pagination, Slide } from "vue3-carousel";
import home_1 from "@/assets/img1.jpg";
import home_2 from "@/assets/img2.jpg";
import home_3 from "@/assets/img3.jpg";

import "vue3-carousel/dist/carousel.css";

export default defineComponent({
	name: "Autoplay",
	components: {
		Carousel,
		Slide,
		Pagination,
	},
	data() {
		return {
			slides: [home_1, home_2, home_3],
		};
	},
});
</script>

<template>
	<div class="wrapper">
		<Carousel :autoplay="3000" :wrap-around="true">
			<Slide v-for="slide in slides" :key="slide">
				<div class="carousel__item">
					<img class="slideImg" :src="slide" />
				</div>
			</Slide>

			<template #addons>
				<Pagination />
			</template>
		</Carousel>
	</div>
</template>

<style scoped>
.carousel__item {
	color: var(--vc-clr-white);
	font-size: 20px;
	border-radius: 8px;
	display: flex;
	justify-content: center;
	align-items: center;
}

.carousel__slide {
	padding: 10px;
}

.carousel__prev,
.carousel__next {
	box-sizing: content-box;
	border: 5px solid white;
}

.slideImg {
	max-height: 740px;
	max-width: 100%;
}

.wrapper {
	margin-top: 3vh;
}
</style>
