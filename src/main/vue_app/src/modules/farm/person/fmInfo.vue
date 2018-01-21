<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item><i class="el-icon-menu"></i> 个人信息</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div id="info_bt">
      <el-button type="small" @click="update">修改</el-button>
    </div>

    <div id="info_table">
      <el-table :data="info" style=" font-size: 14px" max-height="480" border>
        <el-table-column prop="fmId" label="编号" width="80" align="center"></el-table-column>
        <el-table-column prop="regTime" label="申请日期" width="140" align="center"></el-table-column>
        <el-table-column prop="fmName" label="真实姓名" width="110" align="center"></el-table-column>
        <el-table-column prop="company" label="企业名称" width="150" align="center" v-if="tag"></el-table-column>
        <el-table-column prop="phone" label="手机号码" width="120" align="center"></el-table-column>
        <el-table-column prop="account" label="余额" width="90" align="center"></el-table-column>
        <el-table-column prop="province" label="省份" width="80" align="center"></el-table-column>
        <el-table-column prop="city" label="城市" width="80"></el-table-column>
        <el-table-column prop="detailedAddress" label="详细地址" width="150" align="center"></el-table-column>
        <el-table-column prop="farmType" label="农场类型" width="80" align="center"></el-table-column>
        <el-table-column prop="farmSize" label="农场大小" width="80" align="center"></el-table-column>
        <el-table-column prop="checkStatus" label="状态" width="80" align="center"></el-table-column>
      </el-table>
    </div>

    <div id="dialog_edit">
      <Edit ref="edit" v-on:refresh="reLoad"></Edit>
    </div>
  </div>
</template>

<script>
  import statusMap from '@/modules/status_map.vue'
  import Edit from './edit.vue'

  var url = "/farmManager/loadPerson.do";

export default {
    name: 'fmInfo',
  components: { Edit },
  data() {
        return {
            info: [],
            tag: false
        }
  },
  methods: {
        update: function () {
          this.$refs.edit.open(this.info[0])
        },
        reLoad: function () {
          let fmObjStr = sessionStorage.getItem("fmManager");
          let obj = JSON.parse(fmObjStr);
          obj.regTime = this.$sys.dateTimeFormat(obj.regTime, true)
          obj.checkStatus = statusMap.fmStatus(obj.checkStatus)
          obj.farmType = statusMap.fmType(obj.farmType)
          this.info[0] = obj
        }
  },
  created() {
    let fmObjStr = sessionStorage.getItem("fmManager");
    let fmObj = JSON.parse(fmObjStr);
    if (fmObj.farmType == 2) {
        this.tag = true
    }
    this.$sys.ajax.post(url, {"phone": fmObj.phone}, function (rt, _self) {
      let obj = rt.data
      obj.regTime = _self.$sys.dateTimeFormat(obj.regTime, true)
      obj.checkStatus = statusMap.fmStatus(obj.checkStatus)
      obj.farmType = statusMap.fmType(obj.farmType)
      _self.info.push(obj)
    }, this)
  }
}
</script>
<style>
  #info_bt {
    margin-top: 30px;
  }

  #info_table {
    margin-top: 20px;
  }
</style>
