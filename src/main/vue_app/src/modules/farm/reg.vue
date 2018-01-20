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
      <el-form :model="farm" :label-position="'left'" label-width="100px" >
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
        <el-form-item label="农场面积">
          <el-input-number v-model="farm.farmSize" controls-position="right"
                           :min="1" :max="1000" style="width:100px;"></el-input-number>
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
        <span>注册成功</span>
        <span slot="footer" class="dialog-footer">
           <el-button type="primary" @click="close">确 定</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  var tmp = {
    phone: '',
    password: '',
    fmName: '',
    province: '',
    city: '',
    detailedAddress: '',
    farmType: '',
    farmSize: '',
    value: []
  };
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
            value: []
          },
        options: [{
          value: '浙江省',
          label: '浙江省',
          children: [
            {value: '杭州市', label: '杭州市'},
            {value: '宁波市', label: '某某市'}
          ]
        }, {
          value: '陕西省',
          label: '陕西省'
        }, {
          value: '湖北省',
          label: '湖北省'
        }],
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
        submit: function () {
            this.farm.province = this.farm.value[0];
            this.farm.city = this.farm.value[1];
            var farm_cope = JSON.parse(JSON.stringify(this.farm))
            //console.log(JSON.stringify(this.farm))
          farm_cope.password = this.$sys.getMd5(this.farm.passwored)
          var url = "/farmManager/reg.do"  // http://localhost:8080/demo
          var fn = function (data, _self) {
            console.log(data.status + 'fn');
            if (data.status != 0) {
              _self.$message("注册失败！")
            } else {
                _self.dialogVisible = true
            }
          }
          this.$sys.ajax.post(url, farm_cope, fn, this);
        },
        cancel: function () {
          this.farm = tmp;
        },
      close: function () {
            //console.log("6666")
        this.$router.push("/");
      }
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
