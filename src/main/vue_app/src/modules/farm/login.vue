<template>
  <el-form :model="farm" label-width="100px" >
    <el-form-item label="手机">
      <el-input type="text" v-model="farm.phone" class="input_width"></el-input>
    </el-form-item>
    <el-form-item label="密码">
      <el-input type="password" v-model="farm.password" auto-complete="off" class="input_width"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button @click="submit">登录</el-button>
      <el-button @click="reg">注册</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
  export default {
      name: 'login',
    data(){
          return {
              farm:{
                  phone: '',
                  password: ''
              }
          }
    },
    methods: {
        submit: function () {
          if (this.farm.phone === '' || this.farm.password === '') {
            this.$message('账号或密码不能为空');
          } else {
            //console.log(this.farm.phone + ' ' + this.farm.password);
            var url = "/farmManager/loadObj.do"
            var parameters = {
                phone: this.farm.phone
            }
            var fn = function (data, _self) {
              //console.log(data.status + 'fn');
              if (data.status != 0) {
                _self.$message("账号不存在")
                return;
              }
              //console.log(_self.$sys.getMd5(_self.farm.password))
              if (data.rt.password != _self.$sys.getMd5(_self.farm.password)) {
                  _self.$message("密码错误")
                return;
              }
              switch (data.rt.checkStatus) {
                case 0: _self.$message("账号审核中"); break;
                case 1:
                  sessionStorage.setItem("fmManager", JSON.stringify(data.rt));
                  _self.$router.push('/fm/index');
                  break;
                case 2: _self.$message("审核未通过，三天内删除");
                break;
                case 3: _self.$message("账号被暂停使用，请联系管理员");
                break;
              }
            }
            this.$sys.ajax.post(url, parameters, fn, this);
          }
        },
      reg: function () {
        this.$router.push("/fm/reg")
      }
    }
  }
</script>
<style>
  .input_width {
    width: 180px;
  }

</style>
