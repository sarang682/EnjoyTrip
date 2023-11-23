<script setup></script>

<template>
   <div class="container">
        <div class="row">
            <div class="col-md-9 mx-auto">
                <div class="alert mt-3 text-center fw-bold border-bottom" role="alert">
                    게시판
                </div>
                <router-view></router-view>   
            </div>
        </div>
    </div>
</template>

<style>
mark.sky {
  background: linear-gradient(to top, #54fff9 20%, transparent 30%);
}
</style>
