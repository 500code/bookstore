<template>
  <el-menu
      class="el-menu-demo"
      mode="horizontal"
      :router="flag"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b">
    <div class="lay head">
      <el-menu-item index="1" route="index"><i class="el-icon-s-home icon"></i>首页</el-menu-item>
      <el-menu-item index="2" ><i class="el-icon-s-data icon"></i> 排行</el-menu-item>
      <el-menu-item index="3"><i class="el-icon-edit icon"></i>借书流程</el-menu-item>
      <el-menu-item index="4" route="test"><i class="el-icon-question icon"></i>帮助</el-menu-item>
      <el-menu-item index="5"><i class="el-icon-bell icon"></i>消息</el-menu-item>
      <el-submenu index="6">
        <template #title><i class="el-icon-user icon"></i>用户</template>
        <el-menu-item index="2-1" route="center">个人中心</el-menu-item>
        <el-menu-item index="2-3" route="login" @click="signOut">退出登录
        </el-menu-item>
      </el-submenu>
    </div>
  </el-menu>

</template>

<script>
export default {
  data(){
    return{
      flag:true
    }
  },

  methods: {
    //退出登录
    signOut() {
      this.$store.dispatch("del_token")
    }
  }
}
</script>

<style>

.head {
  display: flex;
  justify-content: space-between;
  text-align: center;
}

.lay {
  width: 1200px;
  margin: 0 auto;
}

.el-menu-item, .el-submenu {
  width: 200px;
  height: 70px;
  line-height: 70px;

}

.el-submenu__title {
  height: 70px;
  line-height: 70px;
}

.el-submenu {
  margin: auto 0;
}

.head .icon {
  color: #fff;
}

.el-menu.el-menu--horizontal {
  border: 0;
}

/*中心*/
.el-main {
  left: 0;
  background-color: #E9EEF3;
}

/*卡片*/

.user-avator {
  width: 120px;
  height: 120px;
  border-radius: 50%;
}

.user-info-imp {
  font-size: 27px;
  color: #222;
}

.user-info {
  display: flex;
  align-items: center;
}

.user-info-cont {
  display: flex;
  /*padding-left: 50px;*/
  flex: 1;
  font-size: 14px;
  color: #999;
  justify-content: space-around;
}


.table {
  margin-top: 30px;
  width: 100%;
  font-size: 14px;
}

.red {
  color: #ff0000;
}

.el-tag {
  margin: 0 2px;
}


</style>
