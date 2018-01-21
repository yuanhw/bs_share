<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item><i class="el-icon-menu"></i> 个人信息</el-breadcrumb-item>
        <el-breadcrumb-item>修改密码</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="form-box" id="pmInfo_form">
      <el-form ref="form" :model="form" label-width="80px" :label-position="'left'" :rules="rules" status-icon>
        <el-form-item label="账号" >
          <el-input v-model="form.pmId"  class="input_width" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="form.pmName"  class="input_width" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="旧密码" prop="old_password">
          <el-input type="password" v-model="form.old_password" auto-complete="off" class="input_width"></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="new_password">
          <el-input type="password" v-model="form.new_password" auto-complete="off" class="input_width"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="check_new_password">
          <el-input type="password" v-model="form.check_new_password" auto-complete="off" class="input_width"></el-input>
        </el-form-item>
        <el-form-item label="修改时间">
            <el-input v-model="form.time" class="input_width" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">提交</el-button>
          <el-button @click="reset">取消</el-button>
        </el-form-item>
      </el-form>
      <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="30%">
        <span>修改成功</span>
        <span slot="footer" class="dialog-footer">
           <el-button type="primary" @click="close">确 定</el-button>
        </span>
      </el-dialog>
    </div>

  </div>
</template>

<script>
  var pmObjStr = sessionStorage.getItem("pmManager");
  var pmObj = JSON.parse(pmObjStr);
  const url = "/platformManager/updatePass.do"  // http://localhost:8080/demo

  export default {
    data: function(){
        var validateOld = (rule, value, callback) => {
            //console.log(value+' 666666')
            if (value === '') {
              callback(new Error('请输入密码'));
            } else {
              if (value !== '' && value === pmObj.password) {
                callback();
              }
              callback(new Error("密码错误"));
            }
        };
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.form.check_new_password !== '') {
            this.$refs.form.validateField('check_new_password');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.form.new_password) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        dialogVisible: false,
        form: {
          pmId: '',
          pmName: '',
          old_password: '',
          new_password: '',
          check_new_password: '',
          time: ''
        },
        rules: {
            old_password: [
              { validator: validateOld, trigger: 'blur'}
            ],
          new_password: [
            { validator: validatePass, trigger: 'blur' }
          ],
          check_new_password: [
            { validator: validatePass2, trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      onSubmit() {
        //this.$message.success('提交成功！');
        var parameters = {
          pmId: this.form.pmId.trim(),
          newPass: this.form.new_password
        }
        var fn = function (data, _self) {
          //console.log(data.status + 'fn');
          if (data.status == 0) {
            _self.dialogVisible = true;
          } else {
            this.$message.success('修改失败！');
          }
        }
        this.$sys.ajax.post(url, parameters, fn, this);
      },
      close() {
        this.dialogVisible = false;
        sessionStorage.removeItem("pmManager")
        this.$router.push("/login");
      },
      reset() {
        this.$refs['form'].resetFields();
      }
    },
    created: function () {
      //console.log(pmObjStr + " pmInfo")
      this.form.pmId = pmObj.pmId;
      this.form.pmName = pmObj.pmName;
      this.form.time = this.$sys.dateTimeFormat(pmObj.riseTime);
      pmObjStr = sessionStorage.getItem("pmManager");
      pmObj = JSON.parse(pmObjStr);
    }
  }
</script>
<style>
  .input_width {
    width: 200px;
  }

  #pmInfo_form {
    margin-top: 20px ;
  }
</style>
