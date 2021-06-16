<template>
  <el-col :offset="15">
    <div class="handle-box">
      <el-input v-model="key" placeholder="书名" class="handle-input mr10"></el-input>
      <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
    </div>
  </el-col>
</template>
<script>
  export default {
    data(){
      return{
        key:""
      }
    },
    created() {
    },
    methods: {
//     // 获取 easy-mock 的模拟数据
      handleSearch() {
        this.$http.get("http://localhost:8081/api/search?key=" + this.key)
                .then(res => {
                  console.log(res)
                  console.log(this.query)
                })
      },
      // 分页导航
    }
  }
</script>

<style scoped>
  .handle-box {

    margin-bottom: 20px;
  }
  .handle-input {
    width: 300px;
    display: inline-block;
  }
  .mr10 {
    margin-right: 10px;
  }

</style>
