<script setup>
import { ref, onMounted } from 'vue';
import { update } from '@/api/user';
import { useRouter } from "vue-router";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";



const memberStore = useMemberStore();
const {getUserInfo} = memberStore;
const {userInfo} = storeToRefs(memberStore);

const pwCheck = ref("");
const memberId = ref("");
const router = useRouter();

const domainList = [
  {
    text: "싸피",
    value: "ssafy.com"
  },
  {
    text: "구글",
    value: "gmail.com"
  },
  {
    text: "네이버",
    value: "naver.com"
  },
  {
    text: "카카오",
    value: "kakao.com"
  }
]

const ModifyUser = ref({
    id : "",
    name : "",
    password : "",
    emailId : "",
    emailDomain : ""
})

onMounted(() => {
  getUser();
})

const getUser= async () => {
  await getUserInfo();
  ModifyUser.value = userInfo.value;
  ModifyUser.value.password = "";
  memberId.value = ModifyUser.value.id;
}

const modify = () => {
    if (ModifyUser.value.name == "" || ModifyUser.value.password == "" || ModifyUser.value.emailDomain == ""|| ModifyUser.value.emailId == "") {
    window.confirm("빈칸없이 입력하세요")
  } else if (ModifyUser.value.password != pwCheck.value) {
    window.confirm("비밀번호를 확인해주세요")
    } else {
      update(
        memberId.value, ModifyUser.value ,
        (response) => {
        console.log("@@@@@@@@@@@@")
        console.log(memberId.value)
            if(response.status == 200) {
                alert("수정이 완료되었습니다.");
                router.push("/user/mypage");
            }
        },
        (error) => console.log(error)
    );
  }
}

</script>
<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="orange">회원 정보 수정</mark>
        </h2>
      </div>
      <div class="col-lg-10 text-start">
        <form>
            <div class="mb-3">
            <label for="userid" class="form-label">아이디 : </label>
            <input type="text" class="form-control" v-bind:value="memberId" readonly/>
          </div>
          <div class="mb-3">
            <label for="username" class="form-label">이름 : </label>
            <input type="text" class="form-control" placeholder="이름..."
              v-model="ModifyUser.name"/>
          </div>
          <div class="mb-3">
            <label for="userpwd" class="form-label">비밀번호 : </label>
            <input type="password" class="form-control" placeholder="비밀번호..." v-model="ModifyUser.password" />
          </div>
          <div class="mb-3">
            <label for="pwdcheck" class="form-label">비밀번호확인 : </label>
            <input type="text" class="form-control" id="pwdcheck" placeholder="비밀번호확인..." v-model="pwCheck"/>
          </div>
          <div class="mb-3">
            <label for="emailid" class="form-label">이메일 : </label>
            <div class="input-group">
              <input type="text" class="form-control" placeholder="이메일아이디" v-model="ModifyUser.emailId"/>
              <span class="input-group-text">@</span>
              <select class="form-select" aria-label="이메일 도메인 선택" v-model="ModifyUser.emailDomain">
                <option selected>선택</option>
                <option v-for="domain in domainList" :value="domain.value">{{ domain.text }}</option>
              </select>
            </div>
          </div>
          <div class="col-auto text-center">
            <button type="button" class="btn btn-outline-primary mb-3" @click="modify">수정</button>
            <button type="button" class="btn btn-outline-success ms-1 mb-3">초기화</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>