<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";
import { join } from "@/api/user";


const router = useRouter();

const memberStore = useMemberStore();

const { isValidId } = storeToRefs(memberStore);
const { id_check } = memberStore;
const pwCheck = ref("");

const RegisterUser = ref({
  userId : "",
  userName : "",
  userPassword : "",
  emailId : "",
  emailDomain : ""
})

const register = async () => {
  if (RegisterUser.value.userId == "" || RegisterUser.value.userName == "" || RegisterUser.value.userPassword == "" || RegisterUser.value.emailDomain == "") {
    window.confirm("빈칸없이 입력하세요")
  } else if (RegisterUser.value.userPassword != pwCheck.value) {
    window.confirm("비밀번호를 확인해주세요")
  } else { // 아이디 중복 체크
    console.log(RegisterUser.value);
    await id_check(RegisterUser.value.userId);

    if (isValidId.value) {
      console.log("회원가입 가능")
      join(
        RegisterUser.value,
        (response) => {
          if (response.status == 201) {
            alert("회원가입이 완료되었습니다");
          }
        },
        (error) => console.log(error)
      );
      router.push("/user/login");
    } else {
      window.confirm("아이디가 중복되었습니다.")
      console.log("아이디 중복")
    }
  }
}

// const id_check = function (){
//   idCheck(
//       RegisterUser.value.userId,
//       (response) => {
//         if (response.status == httpStatusCode.OK) {
//           console.log("response값:"+response.data);
//           return response.data;
//         }
//       },
//       (error) => {
//         console.error(error);
//       }
//     )
// }
</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="orange">회원가입</mark>
        </h2>
      </div>
      <div class="col-lg-10 text-start">
        <form>
          <div class="mb-3">
            <label for="username" class="form-label">이름 : </label>
            <input type="text" class="form-control" placeholder="이름..."
              v-model="RegisterUser.userName" />
          </div>
          <div class="mb-3">
            <label v-if="isValidId" for="userid" class="form-label">아이디 : </label>
            <label v-if="!isValidId" for="userid" class="form-label" id="invalidId">아이디 : 다른 아이디를 입력해주세요</label>
            <input type="text" class="form-control" placeholder="아이디..."
              v-model="RegisterUser.userId" />
          </div>
          <div class="mb-3">
            <label for="userpwd" class="form-label">비밀번호 : </label>
            <input type="text" class="form-control" placeholder="비밀번호..." v-model="RegisterUser.userPassword" />
          </div>
          <div class="mb-3">
            <label for="pwdcheck" class="form-label">비밀번호확인 : </label>
            <input type="text" class="form-control" id="pwdcheck" placeholder="비밀번호확인..." v-model="pwCheck"/>
          </div>
          <div class="mb-3">
            <label for="emailid" class="form-label">이메일 : </label>
            <div class="input-group">
              <input type="text" class="form-control" placeholder="이메일아이디" v-model="RegisterUser.emailId"/>
              <span class="input-group-text">@</span>
              <select class="form-select" aria-label="이메일 도메인 선택" v-model="RegisterUser.emailDomain">
                <option selected>선택</option>
                <option value="ssafy.com">싸피</option>
                <option value="google.com">구글</option>
                <option value="naver.com">네이버</option>
                <option value="kakao.com">카카오</option>
              </select>
            </div>
          </div>
          <div class="col-auto text-center">
            <button type="button" class="btn btn-outline-primary mb-3" @click="register">회원가입</button>
            <button type="button" class="btn btn-outline-success ms-1 mb-3">초기화</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<style scoped>
#invalidId {
  color: red;
}
</style>
