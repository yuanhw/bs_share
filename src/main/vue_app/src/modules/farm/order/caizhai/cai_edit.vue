<template>
  <div style="padding: 0 30px">
    <el-dialog
      title="受理采摘指令"
      :visible.sync="dialogVisible"
      width="50%">
      <p>
        <label>种植编号：</label>
        <el-input v-model="rt.plantId" size="small" style="width:180px;margin-right: 20px" :disabled="true"></el-input>
        <label>采摘编号：</label>
        <el-input v-model="rt.id" size="small" style="width:180px" :disabled="true"></el-input>
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
          caiId: null,
        },
        submit0: {
          operator: null,
          phone: null,
          caiId: null
        },
        dialogVisible: false
      }
    },
    methods: {
      open: function (data) {
        this.rt = data
        this.submit0.operator = null
        this.submit0.phone = null
        this.submit0.caiId = data.id
        this.dialogVisible = true
      },
      yes: function () {
        this.$sys.ajax.post('/cai/web/processCai.do', this.submit0, function (rt, _self) {
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
