<template>
  <div class="login-warp">
    <div class="login-main">
      <div class="title">注册</div>
      <div class="main">
        <el-form :model="ruleForm" :label-position="top" status-icon :rules="rules" ref="ruleForm" label-width="100px"
                 class="demo-ruleForm">
          <!--          用户名栏-->
          <el-form-item prop="uname">
            <el-input type="text" v-model="ruleForm.uname" autocomplete="off" placeholder="请输入用户名"
                      clearable></el-input>
          </el-form-item>
          <!--          密码栏-->
          <el-form-item prop="upwd">
            <el-input type="password" v-model="ruleForm.upwd" autocomplete="off" placeholder="请输入密码" clearable
                      show-password></el-input>
          </el-form-item>
          <!--确认密码栏-->
          <el-form-item prop="checkPass">
            <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off" clearable
                      placeholder="请再输入密码" show-password></el-input>
          </el-form-item>

          <el-form-item>
            <div class="login-btn">
              <el-button type="primary" @click="submitForm('ruleForm')">注册</el-button>
            </div>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
// import loginService from "../../service/loginService";
import {ElMessage} from 'element-plus'

export default {
  setup() {
    return {
      success() {
        ElMessage({
          showClose: true,
          message: '这是一条消息提示',
          type: 'success'
        });
      },
      error() {
        ElMessage({
          showClose: true,
          message: '注册失败，某处信息不通过',
          type: 'error'
        });
      },
    }
  },
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else if (value.length < 6) {
        callback(new Error('密码长度至少为6位'))
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.upwd) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        uname: '',
        upwd: '',
        checkPass: '',
      },
      rules: {
        uname: [
          {required: true, message: '请输入用户名', trigger: 'blur'}
        ],
        upwd: [
          {validator: validatePass, trigger: 'blur'}
        ],
        checkPass: [
          {validator: validatePass2, trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log(valid)
          this.success()
          localStorage.setItem("uname", this.ruleForm.uname)
          this.$router.push("/");
        } else {
          this.error()
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
