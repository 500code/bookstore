<template>
  <el-button  @click="test">测试</el-button>
  {{d}}
</template>

<script>
export default {
  name: "test",
  data(){
    return{
      d:''
    }
  },
  methods: {
    test(){
      let that=this;
      this.$http.get("http://localhost:8081/api/test").then(res=>{
        that.d=res;
        console.log(res)
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
