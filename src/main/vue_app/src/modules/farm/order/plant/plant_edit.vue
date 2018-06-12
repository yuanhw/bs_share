<template>
  <div style="padding: 0 30px">
    <el-dialog
      title="受理种植指令"
      :visible.sync="dialogVisible"
      width="50%">
      <p>
        <label>种植编号：</label>
        <el-input v-model="rt.plantId" size="small" style="width:180px;margin-right: 20px" :disabled="true"></el-input>
        <label>地块编号：</label>
        <el-input v-model="rt.blockId" size="small" style="width:180px" :disabled="true"></el-input>
      </p>
      <p>
        <label>负责人员：</label>
        <el-input v-model="submit0.operator" size="small" style="width:180px;margin-right: 20px"></el-input>
        <label>联系电话：</label>
        <el-input v-model="submit0.phone" size="small" style="width:180px"></el-input>
      </p>
      <p>
        <el-button type="primary" icon="el-icon-search" size="small" @click="yes">确定</el-button>
      </p>
    </el-dialog>
    </p>
  </div>
</template>
<script>
  export default {
    name: 'edit',
    data() {
      return {
        rt: {
          plantId: null,
          blockId: null,
        },
        submit0: {
          operator: null,
          phone: null,
          plantId: null
        },
        dialogVisible: false
      }
    },
    methods: {
      open: function (data) {
        this.rt = data
        this.submit0.operator = null
        this.submit0.phone = null
        this.submit0.plantId = data.plantId
        this.dialogVisible = true
      },
      yes: function () {
        this.$sys.ajax.post('/plant/web/processPlant.do', this.submit0, function (rt, _self) {
          if (rt.data == 1) {
            _self.dialogVisible = false
            _self.$emit('refresh')
          }
        }, this)
      }
    }
  }
</script>
<style>

</style>
