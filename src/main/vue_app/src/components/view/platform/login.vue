<template>
      <el-form :model="ruleForm" status-icon  :rules="rules" ref="ruleForm"  label-width="100px" >
        <el-form-item label="账户" prop="pmId">
          <el-input type="text" v-model="ruleForm.pmId" class="input_width"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="ruleForm.password" auto-complete="off" class="input_width"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click="submit('ruleForm')">登录</el-button>
          <el-button @click="cancel('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
</template>
<script>
  var pmObj = {}
  export default {
    data() {
      var v1 = (rule, value, callback) => {
        console.log(value)
        if (!value) {
          return callback(new Error('账号不能为空'))
        } else {
          var url = "/platformManager/loadObj.do"  // http://localhost:8080/demo
          var parameters = {
            pmId: this.ruleForm.pmId.trim()
          }
          var fn = function (data, callback) {
            console.log(data.status + 'fn');
            if (data.status != 0) {
              return callback(new Error("账号不存在"))
            }
            pmObj = data.rt
            callback()
          }
          //this.getDataByHttpPost(url, parameters, fn, callback);
          this.$http.post(url, parameters, fn, callback)
        }
      }
      var v2 = (rule, value, callback) => {
        console.log(value)
          if (!value) {
              return callback(new Error('密码不能为空'))
          } else {
            /*console.log(pmObj.password + " pmObj")
            console.log(pmObj === value )*/
              if (pmObj.password && pmObj.password != value) {
                return callback(new Error("密码错误"))
              }
              callback()
          }
      }
      return {
          ruleFormStr: 'ruleForm',
        ruleForm: {
          pmId: '',
          password: '',
        },
        rules: {
          pmId: [
            {validator: v1, trigger: 'blur'}
          ],
          password: [
            {validator: v2, trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      submit(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
              console.log("success login")
            sessionStorage.setItem("pmManager", JSON.stringify(pmObj))
            this.$router.push("/pm/home")
          }
        });
      },
      cancel(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>
<style>
  .input_width {
    width: 180px;
  }
</style>
