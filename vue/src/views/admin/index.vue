<template>
  <el-container>
    <el-header><vHeader></vHeader></el-header>
    <el-container>
      <el-aside width="200px"><vSidebar></vSidebar></el-aside>
      <el-main><router-view/></el-main>
    </el-container>
  </el-container>
</template>


<script>
import vHeader from "../../components/header";
import vSidebar from "../../components/sidebar";

export default {
  components: {
    vHeader,
    vSidebar
  },
  created() {
    console.log(this.$route.path)
  }
};
</script>

<style>
.el-header {
  background: #242f42;
  height: 70px !important;
}
.el-aside{
  background: #324157;
  display: block;
  position: absolute;
  left: 0;
  top: 70px;
  bottom: 0;
  width: 250px !important;
}
.el-main{
  position: absolute;
  left: 250px;
  right: 0;
  top: 70px;
  bottom: 0;
  padding-bottom: 30px;
  background: #f0f0f0
}
</style>
