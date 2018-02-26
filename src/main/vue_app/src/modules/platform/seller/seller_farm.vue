<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item><i class="el-icon-menu"></i> 商家管理</el-breadcrumb-item>
        <el-breadcrumb-item>农场管理</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div id="search">
      <p>
        <label>商家编号</label>
        <el-input v-model="search.sid" size="small" class="input_small"></el-input>
        <label>状态</label>
        <el-select v-model="search.status" size="small">
          <el-option v-for="item in options_check_status" :key="item.key" :label="item.value"
                     :value="item.key"></el-option>
        </el-select>
      </p>
      <p>
        <el-button type="primary" icon="el-icon-search" size="small" @click="beginSearch">搜索</el-button>
      </p>
    </div>
    <div id="tableData">
      <el-table :data="fmData" style="width: 100%; font-size: 14px" max-height="480">
        <el-table-column prop="id" label="编号" width="80" align="center"></el-table-column>
        <el-table-column prop="fmId" label="商家编号" width="80" align="center"></el-table-column>
        <el-table-column prop="fmTitle" label="农场名称" width="80" align="center"></el-table-column>
        <el-table-column prop="size" label="农场面积（亩）" width="110" align="center"></el-table-column>
        <el-table-column prop="address" label="农场地址" width="110" align="center"></el-table-column>
        <el-table-column prop="posLng" label="定位经度" width="120" align="center"></el-table-column>
        <el-table-column prop="posLat" label="定位纬度" width="80" align="center"></el-table-column>
        <el-table-column prop="keyVegetable" label="城市" width="80" align="center"></el-table-column>
        <el-table-column label="背景图片" width="90" align="center">
          <template slot-scope="scope">
            <img :src="$sys.baseUri + scope.row.fmImg" alt="" style="width: 50px;height: 50px">
          </template>
        </el-table-column>
        <el-table-column prop="fmIntroduce" label="农场介绍" width="150" align="center"></el-table-column>
        <el-table-column prop="businessBegin" label="营业时间" width="80" align="center"></el-table-column>
        <el-table-column prop="spec" label="地块规格" width="80" align="center"></el-table-column>
        <el-table-column label="操作" fixed="right" width="170px" align="center">
          <template slot-scope="scope">
            <el-button type="text" size="mini" @click="sh(scope.$index, fmData)">审核</el-button>
            <el-button type="text" size="mini" @click="xj(scope.$index, fmData)">下架</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-dialog
      title="审核账户："
      :visible.sync="show1"
      width="30%">
      <el-radio v-model="shTag" label="3">同意</el-radio>
      <el-radio v-model="shTag" label="4">拒绝</el-radio>
      <span slot="footer" class="dialog-footer">
    <el-button @click="show1 = false">取 消</el-button>
    <el-button type="primary" @click="shOk">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>
<script>
  const status = [
    {key: 0, value: "刚创建"},
    {key: 1, value: "正在审核"},
    {key: 2, value: "已下架"},
    {key: 3, value: "审核通过"},
    {key: 4, value: "审核未通过"}
  ]
  const loadFarmUrl = "/farm/loadFarmListByCondition.do"
  var gindex = 0
  export default {
    data() {
      return {
        search: {
          sid: null,
          status: null
        },
        options_check_status: status,
        fmData: [],
        show1: false,
        shTag: 1
      }
    },
    methods: {
      beginSearch: function () {
        this.$sys.ajax.post(loadFarmUrl, this.search, function (rt, _self) {
          if (rt.status == 1) {
            _self.fmData = rt.data
          }
        }, this)
      },
      sh: function (index, data) {
        if (this.fmData[index].checkStatus == 1) {
          gindex = index
          this.show1 = true
        } else {
          this.$message.warning("此状态下不能操作")
        }
      },
      xj: function (index) {
        if (this.fmData[index].checkStatus == 3) {
          let param = {
            id: this.fmData[index].id,
            code: 2
          }
          this.$sys.ajax.post('/farm/updateStatus.do', param, function (rt, _self) {
            if (rt.status == 1) {
              _self.$message.warning("操作成功")
              _self.beginSearch()
            } else {
              _self.$message.warning("操作失败")
            }
          }, this)
        } else {
          this.$message.warning("此状态下不能操作")
        }
      },
      shOk: function () {
        let param = {
          id: this.fmData[gindex].id,
          code: this.shTag
        }
        this.$sys.ajax.post('/farm/updateStatus.do', param, function (rt, _self) {
          if (rt.status == 1) {
            _self.show1 = false
            _self.$message.warning("操作成功")
            _self.beginSearch()
          } else {
            _self.$message.warning("操作失败")
          }
        }, this)
      }
    }
  }
</script>
<style scope>
  .el-input {
    width: 180px;
    margin-right: 15px;
  }

  .input_small input {
    width: 120px;
  }

  .input_mini input {
    width: 80px;
  }

  #farm_info label {
    font-size: 12px;
    display: inline-block;
    width: 75px;
  }

  #farm_info {
    font-size: 12px;
  }
</style>
