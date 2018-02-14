<template>
  <div id="farm_add">
    <el-dialog
      title="基本信息"
      :visible.sync="dialogVisible"
      width="50%">
     <div>
       <p class="input_small">
         <label>商家编号</label>
         <el-input v-model="farmInfo.fmId" size="small" :disabled="true"/>
       </p>
       <p>
         <label>农场名称</label>
         <el-input v-model="farmInfo.fmTitle" size="small"/>
         <label>面积（亩）</label>
         <el-input v-model="farmInfo.size" size="small" style="width: 120px;"/>
       </p>
       <p>
         <label>省市</label>
         <el-cascader
           :options="options"
           change-on-select
           v-model="farmInfo.value" size="small"></el-cascader>
       </p>
       <p>
         <label>详细地址</label>
         <el-input type="textarea" v-model="farmInfo.detailedAddress" style="width: 390px;"/>
       </p>
       <p class="input_small">
         <label>百度经度</label>
         <el-input v-model="farmInfo.posLng" size="small"/>
         <label>百度纬度</label>
         <el-input v-model="farmInfo.posLat" size="small"/>
         <a href="http://api.map.baidu.com/lbsapi/getpoint/index.html" target="_blank">经纬度查询</a>
       </p>
       <p>
         <label>联系人姓名</label>
         <el-input v-model="farmInfo.contactName" size="small"/>
         <label>联系人手机</label>
         <el-input v-model="farmInfo.contactPhone" size="small"/>
       </p>
       <p>
         <label>农作物列表</label>
         <el-input type="textarea" v-model="farmInfo.keyVegetable" style="width: 390px;"/>
       </p>
       <p>
         <label>农场介绍</label>
         <el-input type="textarea" v-model="farmInfo.fmIntroduce" style="width: 390px;"/>
       </p>
       <p>
         <label>营业时间说明</label>
         <el-input type="textarea" v-model="farmInfo.businessBegin" style="width: 390px;"/>
       </p>
       <p>
         <label>规格列表</label>
         <el-select v-model="specValue" multiple placeholder="请选择">
           <el-option
             v-for="item in spec_options"
             :key="item.value"
             :label="item.value"
             :value="item.value">
           </el-option>
         </el-select>
       </p>
     </div>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="save">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>
<script>
  export default {
      name: 'fmAdd',
      data() {
          return {
            dialogVisible: false,
            options: null,
            spec_options: [
              {value: "20㎡"},
              {value: "30㎡"},
              {value: "1亩"}
            ],
            specValue: [],
            farmInfo: {
              fmId: null,
              fmTitle: null,
              size: null,
              province: null,
              city: null,
              detailedAddress: null,
              posLng: null,
              posLat: null,
              contactName: null,
              contactPhone: null,
              keyVegetable: null,
              fmIntroduce: '',
              businessBegin: '',
              spec: '',
              value: []
            }
          }
      },
    methods: {
          open(fmId) {
              for (let tmp in this.farmInfo) {
                  this.farmInfo[tmp] = null
              }
              this.farmInfo.fmId = fmId
              this.dialogVisible = true
            let url = '/const/loadProvince.do'
            this.$sys.ajax.post(url, null, function (data, _self) {
              _self.options = data
            }, this)
            this.location()
          },
        validate() {
                return 1;
        },
        save() {
          if (this.validate() == 1) {
            this.farmInfo.province = this.farmInfo.value[0];
            this.farmInfo.city = this.farmInfo.value[1];
            this.farmInfo.spec = this.specValue.join("、")
            let url = '/farm/addFarmBase.do'
            this.$sys.ajax.post(url, this.farmInfo, function (data, _self) {
              if (data.status == 1) {
                _self.$emit('refresh')
                _self.dialogVisible = false
                _self.$emit('refresh')
              } else {
                _self.$message.warning("失败")
              }
            }, this)
          }
        },
      /**
       *  定位
       */
        location() {
          var geolocation = new BMap.Geolocation();
          var _self = this
          geolocation.getCurrentPosition(function(r){
            if(this.getStatus() == BMAP_STATUS_SUCCESS){
                _self.farmInfo.posLng = r.point.lng
               _self.farmInfo.posLat = r.point.lat
            }
            else {
              alert('定位failed, code: '+this.getStatus());
            }
          });
        }
    }
  }
</script>
<style scope>
  #farm_add .el-input {
    width: 180px;
    margin-right: 15px;
  }
  .input_small input{
    width: 120px;
  }
  .input_mini input {
    width: 80px;
  }
  #farm_add label{
    font-size: 12px;
    display:inline-block;
    width: 75px;
  }
  #farm_add {
    font-size: 12px;
  }
</style>
