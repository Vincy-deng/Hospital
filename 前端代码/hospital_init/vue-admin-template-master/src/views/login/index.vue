<template>
  <div class="login-container">
    <el-form
      ref="loginForm"
      :model="loginForm"
      :rules="loginRules"
      class="login-form"
      auto-complete="on"
      label-position="left"
    >
      <div class="title-container">
        <h3 class="title">医院管理系统</h3>
      </div>

      <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input
          ref="username"
          v-model="loginForm.username"
          placeholder="Username"
          name="username"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
      </el-form-item>

      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input
          :key="passwordType"
          ref="password"
          v-model="loginForm.password"
          :type="passwordType"
          placeholder="Password"
          name="password"
          tabindex="2"
          auto-complete="on"
          @keyup.enter.native="handleLogin"
        />
        <span class="show-pwd" @click="showPwd">
          <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
        </span>
      </el-form-item>
      <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:30px;" @click.native.prevent="handleLogin">登录</el-button>
      <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:20px;margin-left:0px" @click.native.prevent="AddPatient" >患者注册</el-button>
      
    </el-form>
    <el-dialog 
    title="患者注册"
    :visible.sync="AddPatientDig"
    width="30%"
    center>
        <el-row>
          <el-col :span="21" :offset="4">
            <el-form 
            ref="AddPatientDate"
            :model="AddPatientDate"
            label-width="80px"
            :inline="true"
            status-icon
            :rules="rules"
            >
              <el-form-item prop="pName" label="用户名">
                <el-input v-model="AddPatientDate.puserName" style="color: 000;"/>
              </el-form-item>
              <el-form-item prop="pPwd" label="密码">
                <el-input v-model="AddPatientDate.ppassWord" show-password/>
              </el-form-item>
              <el-form-item prop="pBirth" label="年龄">
                <el-date-picker v-model="AddPatientDate.pbirth" type="date" placeholder="选择日期"></el-date-picker>
              </el-form-item>
              <el-form-item prop="pSex" label="性别">
                <el-radio v-model="AddPatientDate.psex" label="男">男</el-radio>
                <el-radio v-model="AddPatientDate.psex" label="女">女</el-radio>
              </el-form-item>
              <el-form-item prop="pPhone" label="手机">
                <el-input v-model="AddPatientDate.pphone"/>
              </el-form-item>
              <el-form-item prop="pAddress" label="地址">
                <el-input v-model="AddPatientDate.paddress"/>
              </el-form-item>
            </el-form>
          </el-col>
        </el-row>
        <span slot="footer" class="dialog-footer">
          <el-button @click="cancelAddpatientDig">取消</el-button>
          <el-button type="primary" @click="addSubmitBtn('AddPatientDate')">确定</el-button>
        </span>
    </el-dialog>
  </div>
</template>

<script>
import { loginT } from '../../api/user'
import { setToken } from '@/utils/auth'
import { getToken } from '@/utils/auth'
import { addPatien } from '../../api/patient'
import { format } from 'path'
import { type } from "os";
export default {
  name: "Login",
  data() {
    return {
      AddPatientDig:false,
      loginForm: {
        username: "admin123",
        password: "123456"
      },
      loginRules: {
        username: [{ required: true, trigger: "blur" }],
        password: [{ required: true, trigger: "blur" }]
      },
      AddPatientDate: {
        paddress: "",
        pbirth: "",
        pid: 0,
        pname: "",
        ppassWord: "",
        pphone: "",
        psex: "",
        puid: 0,
        puserName: ""
      },
      rules: {
        puserName:[
          {required:true,message:'请输入用户名',trigger:'blur'},
          {min:3,max:12,message:'长度在3到12个字符',trigger:'blur'}
          ],
        ppassWord:[
          {required:true,message:'请输入密码',trigger:'blur'},
          {min:3,max:12,message:'长度在3到12个字符',trigger:'blur'}
        ]
      },
      loading: false,
      passwordType: "password",
      redirect: undefined
    };
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true
    }
  },
  methods: {
    showPwd() {
      if (this.passwordType === "password") {
        this.passwordType = "";
      } else {
        this.passwordType = "password";
      }
      this.$nextTick(() => {
        this.$refs.password.focus();
      });
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          loginT(this.loginForm)
            .then(Response => {
              //通过localStorage接收后台传的值来判断当前登录人的id
              localStorage.setItem("role", Response.data.uId);
              localStorage.setItem("username", Response.data.username);
              localStorage.setItem("login",'1');
              this.loading = true;
              console.log(Response.responseMsg);
              if (Response.responseMsg == "Success") {
                setToken(Response.data.role);
                this.$router.push({ path: this.redirect || "/" });
              }
            })
            .catch(error => {
              if(error.response == null){
                this.$message({
                showClose: true,
                message: "登录失败",
                type: "error"
              });
              }              
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      })
    },
    AddPatient(){
      this.AddPatientDig=true
    },
    cancelAddpatientDig() {
      this.AddPatientDig=false
      this.AddPatientDate={
        puserName:'',
        ppassword:'',
        pbirth:'',
        psex:'',
        paddress:'',
        pphone:''
      }
    },
    addSubmitBtn(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          const data={
            pname:this.AddPatientDate.puserName,
            puserName:this.AddPatientDate.puserName,
            ppassWord:this.AddPatientDate.ppassWord,
            pbirth:this.AddPatientDate.pbirth,
            psex:this.AddPatientDate.psex,
            paddress:this.AddPatientDate.paddress,
            pphone:this.AddPatientDate.pphone
          }
          addPatien(data).then(Response => {
            // this.getDate()
            console.log(Response) 
            if(Response.responseCode == 0){
              this.$message({
              showClose:true,
              message:'注册成功',
              type:'success'
            })
            }else{
              this.$message({
              showClose:true,
              message:'注册失败',
              type:'error'
            })
            }
            this.cancelAddpatientDig()//关闭对话框
          }).catch(error => {
            console.log(error)
           
           
          })
        }else {
          this.$message({
            type:'error',
            message:'提示：您填写的信息有误'
          })
        }
      })
    },
  }
};
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg: #283443;
$light_gray: #fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {
 
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #eee;

.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }
  

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
}
</style>
