<script setup>
import { onBeforeMount, onMounted, ref } from 'vue';
import {useMemberStore} from "@/stores/member";
import { storeToRefs } from "pinia";

const memberStore = useMemberStore();
const {getUserInfo} = memberStore;
const {userInfo} = storeToRefs(memberStore);

const user=ref({})

onMounted(()=> {
  const token=sessionStorage.getItem("accessToken");
  getUser(token);
})

const getUser= async (token) => {
  await getUserInfo(token);
  user.value=userInfo.value;
  // console.log("getUser : "+user.value.userId);
}

</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="orange">내정보</mark>
        </h2>
      </div>
      <div class="col-lg-10">
        <div class="card mt-3 m-auto" style="max-width: 700px">
          <div class="row g-0">
            <div class="col-md-4">
              <img
                src="https://source.unsplash.com/random/250x250/?food"
                class="img-fluid rounded-start"
                alt="..."
              />
            </div>
            <div class="col-md-8">
              <div class="card-body text-start">
                <ul class="list-group list-group-flush">
                  <li class="list-group-item">{{user.userId}}</li>
                  <li class="list-group-item">{{user.userName}}</li>
                  <li class="list-group-item">{{user.emailId}}@{{user.emailDomain}}</li>
                </ul>
              </div>
            </div>
          </div>
        </div>
        <div>
          <button type="button" class="btn btn-outline-secondary mt-2">수정</button>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped></style>
