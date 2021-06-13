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
      this.$http.get(
          "http://localhost:8081/api/test", ).then(res => {
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
