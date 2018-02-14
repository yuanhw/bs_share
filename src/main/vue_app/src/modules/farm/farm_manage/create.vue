<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item><i class="el-icon-menu"></i> 农场管理</el-breadcrumb-item>
        <el-breadcrumb-item>农场生成</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div>
      <p>
        <el-button type="primary" icon="el-icon-plus" size="mini" @click="add">新建</el-button>
        <el-button type="primary" icon="el-icon-edit" size="mini" @click="update">修改</el-button>
        <el-button type="primary" icon="el-icon-edit" size="mini" @click="cancel">取消</el-button>
        <el-button type="primary" icon="el-icon-edit" size="mini" @click="save">保存</el-button>
        <el-button type="primary" icon="el-icon-edit" size="mini" @click="publish">发布</el-button>
        <el-button type="primary" icon="el-icon-edit" size="mini" @click="unPublish">取消发布</el-button>
      </p>
    </div>
    <div id="farm_info" v-show="isShow">
      <el-tabs v-model="activeName">
        <el-tab-pane label="基本信息" name="first">
          <div>
            <p class="input_small">
              <label>商家编号</label>
              <el-input v-model="farmInfo.fmId" size="small" :disabled="true"/>
            </p>
            <p>
              <label>农场名称</label>
              <el-input v-model="farmInfo.fmTitle" size="small" :disabled="updateTag"/>
              <label>面积（亩）</label>
              <el-input v-model="farmInfo.size" size="small" style="width: 120px;" :disabled="updateTag"/>
            </p>
            <p class="input_small">
              <label>所在省</label>
              <el-input v-model="farmInfo.province" size="small" :disabled="true"/>
              <label>所在市</label>
              <el-input v-model="farmInfo.city" size="small" :disabled="true"/>
            </p>
            <p>
              <label>详细地址</label>
              <el-input type="textarea" v-model="farmInfo.detailedAddress" style="width: 390px;" :disabled="true"/>
            </p>
            <p class="input_small">
              <label>百度经度</label>
              <el-input v-model="farmInfo.posLng" size="small" :disabled="true"/>
              <label>百度纬度</label>
              <el-input v-model="farmInfo.posLat" size="small" :disabled="true"/>
            </p>
            <p>
              <label>联系人姓名</label>
              <el-input v-model="farmInfo.contactName" size="small" :disabled="updateTag"/>
              <label>联系人手机</label>
              <el-input v-model="farmInfo.contactPhone" size="small" :disabled="updateTag"/>
            </p>
            <p class="input_small">
              <label>状态</label>
              <el-input v-model="farmInfo.checkStatus" size="small" :disabled="true"/>
            </p>
          </div>
        </el-tab-pane>
        <el-tab-pane label="App展示信息" name="second">
          <div>
            <p class="input_mini">
              <label>用户评级</label>
              <el-input v-model="farmInfo.grade" size="small" :disabled="true"/>
              <label>当前认养人数</label>
              <el-input v-model="farmInfo.consumerNum" size="small" :disabled="true"/>
            </p>
            <p>
              <label>农作物列表</label>
              <el-input type="textarea" v-model="farmInfo.keyVegetable" style="width: 390px;" :disabled="updateTag"/>
            </p>
            <p>
              <label>农场展示图</label>
              <img :src="$sys.baseUri + farmInfo.fmImg" style="width: 300px; height: 300px">
              <div style="margin-left: 80px;">
            <input type="file" name="imgFile" id="imgFile">
            <button type="button" @click="submit()">上传</button>
              </div>
            </p>
            <br>
            <p>
              <label>农场介绍</label>
              <el-input type="textarea" v-model="farmInfo.fmIntroduce" style="width: 390px;" :disabled="updateTag"/>
            </p>
          </div>
        </el-tab-pane>
        <el-tab-pane label="App补充信息" name="third">
          <div>
            <p>
              <span style="margin-right: 20px;">农场介绍短视频</span>
              <a href="http://www.baidu.com" target="_blank">点击观看</a>
            </p>
            <p>
              <label>营业时间说明</label>
              <el-input type="textarea" v-model="farmInfo.businessBegin" style="width: 390px;" :disabled="updateTag"/>
            </p>
            <p>
              <label>规格列表</label>
              <el-select v-model="specValue" multiple placeholder="请选择" :disabled="updateTag">
                <el-option
                  v-for="item in spec_options"
                  :key="item.value"
                  :label="item.value"
                  :value="item.value">
                </el-option>
              </el-select>
            </p>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>
    <div>
      <CusAdd ref="add" v-on:refresh="reLoad"></CusAdd>
    </div>
    <div id="container"></div>
  </div>
</template>
<script>
  const loadUrl = "/farm/loadFarmById.do"
  const isHaveUrl = "/farm/isHaveFarm.do"
  const status_map = [
    {key: 0, value: "刚创建"},
    {key: 1, value: "正常发布"},
    {key: 2, value: "下架"},
    {key: 3, value: "管理员下架"}
  ]
  const bt_status = [
    {key: 'isHave', value: 0},
    {key: 'isUpdate', value: 0}
  ]
  const spec_options = [
    {value: "20㎡"},
    {value: "30㎡"},
    {value: "1亩"}
  ]

  import CusAdd from './add.vue'

  export default {
    name: 'fmCreate',
    components: { CusAdd },
    data() {
        return {
            upLoadUrl:'',
            isShow: false,
            updateTag: true,
            activeName: 'first',
          statusCode: null,
          spec_options: spec_options,
          specValue: [],
            farmInfo: {
                id: null,
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
                checkStatus: null,
                grade: null,
                consumerNum: null,
              keyVegetable: null,
              fmImg: '/farmImg/default.jpg',
              fmIntroduce: '',
              fmVideo: '',
              businessBegin: '',
              spec: ''
            }
        }
    },
    methods: {
        submit() {
          var oMyForm = new FormData();
          oMyForm.append("id", this.farmInfo.id);
          var file = document.getElementById("imgFile").files[0]
          if (file) {
            console.log(file)
            const isJPG = file.type === 'image/jpeg';
            const isLt2M = file.size / 1024 / 1024 < 2;
            if (!isJPG) {
              this.$message.error('上传头像图片只能是 JPG 格式!');
              return
            }
            if (!isLt2M) {
              this.$message.error('上传头像图片大小不能超过 2MB!');
              return
            }
            oMyForm.append("imgFile", file);
            var oReq = new XMLHttpRequest();
            oReq.open("POST", this.upLoadUrl);
            oReq.send(oMyForm);
            var _self = this
            oReq.onload = function (event) {
              if (oReq.status == 200) {
                  _self.farmInfo.fmImg = '/farmImg/' + _self.farmInfo.id + file.name;
              }
            }
          }
        },
        add() {
          let fm = sessionStorage.getItem("fmManager");
          let fmObj = JSON.parse(fm);
          this.$sys.ajax.post(isHaveUrl, {fmId: fmObj.fmId}, function (rt, _self) {
            if (rt.status == 1) {
              if (rt.data == 1) {
                  _self.$message.warning("只允许存在一个农场")
              } else {
                let fm = sessionStorage.getItem("fmManager");
                let fmObj = JSON.parse(fm);
                _self.$refs.add.open(fmObj.fmId)
              }
            }
          }, this)
        },
      reLoad() {
        let fm = sessionStorage.getItem("fmManager");
        let fmObj = JSON.parse(fm);
        this.farmInfo.fmId = fmObj.fmId
        this.$sys.ajax.post(loadUrl, {fmId: fmObj.fmId}, function (rt, _self) {
          //console.log(rt.status + " status");
          if (rt.status == 1) {
            _self.isShow = true
            _self.farmInfo = rt.data
            _self.statusCode = rt.data.checkStatus
            _self.farmInfo.checkStatus = status_map[rt.data.checkStatus].value
            _self.specValue = rt.data.spec.split("、")
          }
        }, this)
      },
      update() {
            if (bt_status[0].value == 1) {
              this.updateTag = false
              bt_status[1].value = 1
            }
      },
      save() {
            if (bt_status[1].value == 1) {
                let param = {
                  id: this.farmInfo.id,
                  fmTitle: this.farmInfo.fmTitle,
                  size: this.farmInfo.size,
                  contactName: this.farmInfo.contactName,
                  contactPhone: this.farmInfo.contactPhone,
                  keyVegetable: this.farmInfo.keyVegetable,
                  fmIntroduce: this.farmInfo.fmIntroduce,
                  businessBegin: this.farmInfo.businessBegin,
                  spec: this.specValue.join("、")
                }
              this.$sys.ajax.post('/farm/updateBase.do', param, function (rt, _self) {
                if (rt.status == 1) {
                  _self.$message.warning("修改成功")
                } else {
                  _self.$message.warning("修改失败")
                }
              }, this)
              this.updateTag = true
              bt_status[1].value = 0;
            }
      },
      publish() {
        if (this.statusCode == 0 || this.statusCode ==2) {
            let param = {
                id: this.farmInfo.id,
                code: 1
            }
          this.$sys.ajax.post('/farm/updateStatus.do', param, function (rt, _self) {
            if (rt.status == 1) {
              _self.$message.warning("发布成功")
              _self.statusCode = 1
              _self.farmInfo.checkStatus = status_map[1].value
            } else {
              _self.$message.warning("发布失败")
            }
          }, this)
        } else {
            this.$message.warning("此状态下不能操作发布")
        }
      },
      unPublish() {
        if (this.statusCode == 1) {
          let param = {
            id: this.farmInfo.id,
            code: 2
          }
          this.$sys.ajax.post('/farm/updateStatus.do', param, function (rt, _self) {
            if (rt.status == 1) {
              _self.$message.warning("取消发布成功")
              _self.statusCode = 2
              _self.farmInfo.checkStatus = status_map[2].value
            } else {
              _self.$message.warning("取消发布失败")
            }
          }, this)
        } else {
          this.$message.warning("此状态下不能取消发布")
        }
      },
      cancel() {
            this.updateTag = true
        this.reLoad()
      }
    },
    created() {
        this.upLoadUrl = this.$sys.baseUri + '/farm/upLoadImg.do'
        let fm = sessionStorage.getItem("fmManager");
        let fmObj = JSON.parse(fm);
        this.farmInfo.fmId = fmObj.fmId
        this.$sys.ajax.post(loadUrl, {fmId: fmObj.fmId}, function (rt, _self) {
            //console.log(rt.status + " status");
          if (rt.status == 1) {
              bt_status[0].value = 1
              _self.isShow = true
              _self.farmInfo = rt.data
            _self.statusCode = rt.data.checkStatus
              _self.farmInfo.checkStatus = status_map[rt.data.checkStatus].value
            _self.specValue = rt.data.spec.split("、")
          }
        }, this)

      /*
      var map = new BMap.Map("container");
      var pointA = new BMap.Point(109.483932700,34.502357980);
      var pointB = new BMap.Point(113.395469000,22.520579000);
      console.log('distance: ' + map.getDistance(pointA,pointB))
      */
    }
  }
</script>
<style scope>
  #farm_info .el-input {
    width: 180px;
    margin-right: 15px;
  }
  .input_small input{
    width: 120px;
  }
  .input_mini input {
    width: 80px;
  }
  #farm_info label{
    font-size: 12px;
    display:inline-block;
    width: 75px;
  }
  #farm_info {
    font-size: 12px;
  }
</style>
