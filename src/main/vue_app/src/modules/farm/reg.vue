<template>
  <div id="root">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item><i class="el-icon-menu"></i> 商家信息</el-breadcrumb-item>
        <el-breadcrumb-item>注册</el-breadcrumb-item>
        <el-breadcrumb-item><a @click="close">返回</a></el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div  class="form-box" style="margin-top: 20px">
      <el-form :model="farm" :label-position="'left'" label-width="100px">
        <el-form-item label="电话">
          <el-input type="text" v-model="farm.phone" class="input_width"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input type="password" v-model="farm.password" auto-complete="off" class="input_width"></el-input>
        </el-form-item>
        <el-form-item label="真实姓名">
          <el-input type="text" v-model="farm.fmName" class="input_width"></el-input>
        </el-form-item>
        <el-form-item label="省市">
          <el-cascader
            :options="options"
            change-on-select
            class="input_width" v-model="farm.value"></el-cascader>
        </el-form-item>
        <el-form-item label="详细地址">
          <el-input type="text" v-model="farm.detailedAddress" class="input_width"></el-input>
        </el-form-item>
        <el-form-item  label="农场类型">
          <el-select v-model="farm.farmType" placeholder="请选择" class="input_width">
            <el-option
              v-for="item in options1"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="企业名称" v-if="isShow">
          <el-input type="text" v-model="farm.company" class="input_width"></el-input>
        </el-form-item>
        <el-form-item label="农场面积">
          <el-input-number v-model="farm.farmSize" controls-position="right"
                           :min="1" :max="1000" style="width:100px;"></el-input-number><span style="margin-left:2px;">亩</span>
        </el-form-item>
        <el-form-item>
          <el-button @click="submit">确定</el-button>
          <el-button @click="close">取消</el-button>
        </el-form-item>
      </el-form>
      <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="30%">
        <span>注册成功，请登录</span>
        <span slot="footer" class="dialog-footer">
           <el-button type="primary" @click="close">确 定</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  var url = "/farmManager/reg.do"

  export default {
    data() {
      return {
          dialogVisible: false,
          farm: {
            phone: '',
            password: '',
            fmName: '',
            province: '',
            city: '',
            detailedAddress: '',
            farmType: '',
            farmSize: '',
            company: '',
            value: []
          },
        options: [],
        options1: [{
          value: 1,
          label: '个人'
        }, {
          value: 2,
          label: '企业'
        }]
      };
    },
    methods: {
        validate: function () {
          return true
        },
        submit: function () {
            if (this.validate()) {
              this.farm.province = this.farm.value[0];
              this.farm.city = this.farm.value[1];
              var farm_cope = JSON.parse(JSON.stringify(this.farm))
              farm_cope.password = this.$sys.getMd5(this.farm.password)

              var fn = function (data, _self) {
                //console.log(data.status + 'fn');
                if (data.status != 0) {
                  _self.$message("注册失败！")
                } else {
                  _self.dialogVisible = true
                }
              }

              this.$sys.ajax.post(url, farm_cope, fn, this);
            }
        },
      close: function () {
            //console.log("6666")
        this.$router.push("/");
      }
    },
    computed: {
        isShow() {
            return this.farm.farmType == '2' ? true : false
        }
    },
    created() {
        let url = '/const/loadProvince.do'
        this.$sys.ajax.post(url, null, function (data, _self) {
          _self.options = data
        }, this)
    }
  }
</script>
<style>
  .input_width {
    width: 200px;
  }
  #root {
    padding: 10px;
  }
</style>
