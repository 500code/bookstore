<template>
  <div class="login-warp">
    <div class="login-main">
      <div class="title">后台管理系统</div>
      <div class="main">
        <el-form :model="form" :label-position="top" status-icon :rules="rules" ref="loginFrom" label-width="100px"
                 class="demo-ruleForm">
          <el-form-item prop="username">
            <el-input type="text" v-model="form.username" autocomplete="off" placeholder="请输入用户名" clearable></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input type="password" v-model="form.password" autocomplete="off" placeholder="请输入密码" clearable
                      show-password></el-input>
          </el-form-item>
          <el-form-item>
            <div class="login-btn">
              <el-button type="primary" @click="submitForm('loginFrom')">登录</el-button>
            </div>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>

import {ElMessage} from "element-plus";

export default {
  data() {
    return {
      form: {
        username: '',
        password: '',
      },
      rules: {
        username: [
          {required: true, message: "请输入用户名", trigger: "blur"}
        ],
        password: [
          {required: true, message: "请输入密码", trigger: 'blur'}
        ]
      }
    };
  },
  created() {

  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let that=this;
          const formData = new FormData();
          formData.append("aname", this.form.username)
          formData.append("apwd", this.form.password)
          this.$http.post("http://localhost:8081/api/admin/login", formData)
                  .then(res => {
                    localStorage.setItem("token", res.data.token)
                    localStorage.setItem("admin",res.data.admin)
                    // console.log(res.data.admin)
                    that.$store.dispatch("set_token", res.data.token)
                    ElMessage.success('登录成功')
                    that.$router.push('/admin');
                    console.log(res)
                    // console.log(that.$store.state.token)
                  })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    }
  }
}
</script>

<style>
.login-warp {
  display: flex;
  height: 100vh;
  width: 100vw;
  justify-content: center;
  align-items: center;
  background: url("../../assets/background.jpg");
}

.login-main {
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 350px;

  background: #fff;
}

.title {
  line-height: 50px;
  text-align: center;
  font-size: 20px;
  border-bottom: 1px solid #ddd;
}

.main {
  margin-top: 30px;
  display: flex;
}

.main, .el-form {
  width: 100%;
  height: 100%;
}

.el-form, .el-form-item__content {
  margin: 0 auto !important;
  width: 240px;
}

.login-btn .el-button {
  width: 240px;
}
</style>
