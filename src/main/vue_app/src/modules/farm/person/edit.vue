<template>
  <div id="person_edit" style="padding: 0 50px">
    <el-dialog id="update" title="修改信息" :visible.sync="show" width="50%">
      <p>
        <label>编号</label>
        <el-input v-model="fm.fmId" class="input_mid" size="small" :disabled="true" ></el-input>
        <label>手机号</label>
        <el-input v-model="fm.phone" class="input_mid" size="small" :disabled="true" ></el-input>
      </p>
      <p>
        <label>真实姓名</label>
        <el-input type="text" v-model="fm.fmName" class="input_mid" size="small"></el-input>
        <label>所在地</label>
        <el-cascader
          :options="options"
          change-on-select
          style="width: 160px;" v-model="value" size="small"></el-cascader>
      </p>
      <p>
        <label>详细地址</label>
        <el-input type="textarea" v-model="fm.detailedAddress" size="small"></el-input>
      </p>
      <p>
        <label>农场面积</label>
          <el-input-number v-model="fm.farmSize" controls-position="right"
                           :min="1" :max="1000" style="width:100px;" size="small"></el-input-number>
        <span style="margin-left:2px;">亩</span>
      </p>
      <div style="width: 250px; margin: 0 auto">
        <el-button type="primary" @click="onSubmit">确定</el-button>
        <el-button @click="close">取消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
const url = '/farmManager/updateFM.do'

  export default {
    name: 'edit',
    data() {
      return {
        fm: {},
        show: false,
        options: [],
        value: []
      }
    },
    methods: {
      validate() {   //验证
        return true
      },
      onSubmit() {
        if (this.validate()) {
            this.fm.province = this.value[0]
            this.fm.city = this.value[1]
            this.$sys.ajax.post(url, this.fm, function (rt, _self) {
              if (rt.status != 1) {
                  _self.$message.warning(rt.msg)
              } else {
                  _self.fm = {}
                  _self.show = false
                sessionStorage.setItem("fmManager", JSON.stringify(rt.data))
                _self.$emit('refresh')
              }
            }, this)
        }
      },
      close() {
        //console.log("close")
        this.show = false
      },
      open(row){
        this.fm = row
        this.show = true;
        this.value[0] = row.province
        this.value[1] = row.city
        //console.log(this.value)
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
  .input_mid {
    width: 120px;
    margin-right: 10px;
  }
  #person_edit label{
    font-size: 12px;
    display:inline-block;
    width: 55px;
  }
</style>
