<template>
  <div style="padding: 0 30px">
    <el-dialog
      title="退款申请"
      :visible.sync="dialogVisible"
      width="50%">
      <p>
        <label>订单编号：</label>
        <el-input v-model="refund.orderId" size="small" style="width:180px" :disabled="true"></el-input>
        <label>订单金额：</label>
        <el-input v-model="refund.orderAmt" size="small" style="width:180px" :disabled="true"></el-input>
      </p>
      <p>
        <label>退款金额：</label>
        <el-input v-model="refund.refundAmt" size="small" style="width:180px" :disabled="true"></el-input>
        <label>用户编号：</label>
        <el-input v-model="refund.userId" size="small" style="width:180px" :disabled="true"></el-input>
      </p>
      <p>
        <label>退款原因：</label>
        <el-input v-model="refund.refundYs" size="small" style="width:180px" :disabled="true"></el-input>
      </p>
      <p>
        <label>退款说明：</label>
        <el-input v-model="refund.refundSm" type="area" style="width:180px" :disabled="true"></el-input>
      </p>
      <p>
        <el-button type="primary" icon="el-icon-search" size="small" @click="yes">同意</el-button>
        <el-button type="primary" icon="el-icon-search" size="small" @click="no">拒绝</el-button>
      </p>
    </el-dialog>
    </p>
  </div>
</template>
<script>
  export default {
    name: 'ReFund',
    data() {
      return {
        orderId: null,
        dialogVisible: false,
        refund: {
          orderId: null,
          orderAmt: null,
          refundAmt: null,
          userId: null,
          refundYs: null,
          refundSm: null
        }
      }
    },
    methods: {
      open: function (id) {
        this.refund.orderId = id
        this.$sys.ajax.post('/block/order/getRefund.do', {orderId: id, status: 1}, function (rt, _self) {
          _self.refund = rt.data
        }, this)
        this.dialogVisible = true
      },
      no: function () {
        this.$sys.ajax.post('/block/order/processRefund.do', {
          orderId: this.refund.orderId,
          status: 0
        }, function (rt, _self) {
          _self.dialogVisible = false
          _self.$emit('refresh')
        }, this)
      },
      yes: function () {
        this.$sys.ajax.post('/block/order/processRefund.do', {
          orderId: this.refund.orderId,
          status: 1
        }, function (rt, _self) {
          _self.dialogVisible = false
          _self.$emit('refresh')
        }, this)
      }
    }
  }
</script>
<style>

</style>
