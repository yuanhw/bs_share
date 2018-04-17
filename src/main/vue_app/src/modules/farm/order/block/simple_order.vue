<template>
  <div style="padding: 0 30px">
    <h3>单个地块详情</h3>
    <p>
      <label>订单号：</label>
      <el-input v-model="orderId" size="small" style="width:180px" :disabled="true"></el-input>
    </p>
    <p>
    <div style="width: 302px">
      <el-table :data="dataList" width="100%" stripe style="font-size: 12px;" border>
        <el-table-column prop="bdetailId" label="地块编号" width="120px" align="center"></el-table-column>
        <el-table-column prop="createTime" label="创建日期" width="180px" align="center"></el-table-column>
      </el-table>
    </div>
    </p>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        orderId: null,
        dataList: []
      }
    },
    created: function () {
      this.orderId = this.$route.query.orderId
      this.$sys.ajax.post('/block/order/loadDetailSimple.do', {orderId: this.orderId}, function (rt, _self) {
        rt.data.forEach(function (e) {
          e.createTime = _self.$sys.dateTimeFormat(e.createTime, true)
        })
        _self.dataList = rt.data
      }, this)
    }
  }
</script>
<style>

</style>
