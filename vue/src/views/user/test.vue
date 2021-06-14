<template>
  <el-button @click="test">测试</el-button>
  {{ d }}
</template>

<script>
// import loginService from "@/service/loginService";

export default {
  name: "test",
  data() {
    return {
      d: ''
    }
  },
  methods: {
    test() {
      let that = this;
      // loginService.ckLogin()
      const formData = new FormData();
      formData.append("uname","肥宝")
      formData.append("upwd","123123")
      this.$http.post(
          "http://localhost:8081/api/user/login",formData).then(res => {
            that.d=res
      })
    }
  }
}
</script>

<style scoped>
.scrollBox {
  display: flex;
  flex-wrap: nowrap;
}
</style>
