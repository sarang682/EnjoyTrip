<script setup>
import { onMounted, ref } from 'vue';
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";
import { deleteUser } from '@/api/user';
import { useMenuStore } from "@/stores/menu";


const memberStore = useMemberStore();
const { getUserInfo } = memberStore;
const { userInfo } = storeToRefs(memberStore);
const { changeMenuState } = useMenuStore();

const user = ref({})
const router = useRouter();

onMounted(() => {
  getUser();
})

const getUser = async () => {
  await getUserInfo();
  user.value = userInfo.value;
}

const modify = () => {
  router.push({ name: "user-modify" });
};

const deleteMember = () => {
  if (confirm("정말 탈퇴하시겠습니까?")) {
    deleteUser(
      user.value.id,
      (response) => {
        if (response.status == 200) {
          changeMenuState();
          sessionStorage.removeItem("accessToken");
          router.push("/");
        }
      },
      (error) => {
        console.log(user.value.id);
        console.log(error);
      }
    )
  }
};


</script>

<template>
  <div class="container">
    <div class="row">
      <div class="col-md-9 mx-auto">
        <div class="alert mt-3 text-center fw-bold border-bottom" role="alert">
          내 정보
        </div>




        <div class="card mt-3 m-auto" style="max-width: 700px">
          <div class="row g-0">
            <div class="col-md-4">
              <img src="https://source.unsplash.com/random/250x250/?food" class="img-fluid rounded-start" alt="..." />
            </div>
            <div class="col-md-8">
              <div class="card-body text-start">
                <ul class="list-group list-group-flush">
                  <li class="list-group-item">{{ user.id }}</li>
                  <li class="list-group-item">{{ user.name }}</li>
                  <li class="list-group-item">{{ user.emailId }}@{{ user.emailDomain }}</li>
                </ul>
              </div>
            </div>
          </div>
        </div>
        <div>
          <button type="button" class="btn btn-outline-secondary mt-2" @click="modify">수정</button>
          <button type="button" class="btn btn-outline-secondary mt-2" @click="deleteMember">탈퇴</button>
        </div>
      </div>
    </div>
  </div>
</template>
<style scoped>
button {
  margin-left: 10px;
}
</style>
