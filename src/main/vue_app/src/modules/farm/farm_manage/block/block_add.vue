<template>
  <div id="create_add" style="padding: 0 50px">
    <el-dialog id="add" title="新增地块" :visible.sync="show" width="75%"
               v-loading="loadShow"
               element-loading-text="批量创建中"
               element-loading-spinner="el-icon-loading"
               element-loading-background="rgba(0, 0, 0, 0.8)">
      <p>
        <label>批号</label>
        <el-input v-model="block.batchNo" size="small" style="width: 150px"></el-input>
      </p>
      <p>
        <label>面积（亩）</label>
        <el-input-number v-model="block.area" controls-position="right"
                         :min="1" :max="100" size="small" style="width: 150px; margin-right: 20px"></el-input-number>
        <label>规格（平米）</label>
        <el-input-number v-model="block.spec" controls-position="right"
                         :min="20" :max="1000" size="small" style="width: 150px"></el-input-number>
      </p>
      <p>
        <label>租期单价（元）</label>
        <el-input v-model="block.unitPrice" size="small" style="width: 150px; margin-right: 20px"></el-input>
        <label>租期单位</label>
        <el-select v-model="block.leaseUnit" placeholder="请选择" size="small" style="width: 150px; margin-right: 20px">
          <el-option
            v-for="item in options_2"
            :key="item.key"
            :label="item.key"
            :value="item.key">
          </el-option>
        </el-select>
      </p>
      <p>
        <label>数量（个）</label>
        <el-input-number v-model="block.number" controls-position="right"
                         :min="1" :max="1000" size="small" style="width: 150px; margin-right: 20px"></el-input-number>
        <label>类型</label>
        <el-select v-model="block.type" placeholder="请选择" size="small" style="width: 150px">
          <el-option
            v-for="item in options_3"
            :key="item.key"
            :label="item.value"
            :value="item.key">
          </el-option>
        </el-select>
      </p>
      <p>
        <label>最大租期</label>
        <el-input v-model="block.maxLease" size="small" style="width: 150px; margin-right: 20px"></el-input>
      </p>
      <p>
        <span>app有效期开始日期</span>
        <el-date-picker
          v-model="block.validityBegin"
          type="date"
          placeholder="选择日期" size="small" style="width: 150px; margin-right: 20px;">
        </el-date-picker>
        <span>app有效期结束日期</span>
        <el-date-picker
          v-model="block.validityEnd"
          type="date"
          placeholder="选择日期" size="small" style="width: 150px">
        </el-date-picker>
      </p>
      <p>
        <label>说明</label>
        <el-input type="area" v-model="block.description" style="width: 480px"></el-input>
      </p>
      <div style="width: 250px; margin: 0 auto">
        <el-button type="primary" @click="onSubmit" size="small">立即创建</el-button>
        <el-button @click="close" size="small">取消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  const options_2 = [
    {key: "月"},
    {key: "年"}
  ]
  const options_3 = [
    {key: 0, value: "仅自种"},
    {key: 1, value: "可代种"}
  ]
  export default {
    name: 'blockAdd',
    data() {
      return {
        loadShow: false,
        show: false,
        options_2: options_2,
        options_3: options_3,
        block: {
          farmId: null,
          batchNo: null,
          area: null,
          spec: null,
          unitPrice: null,
          number: null,
          type: null,
          description: null,
          leaseUnit: null,
          maxLease: null,
          validityBegin: null,
          validityEnd: null
        }
      }
    },
    methods: {
      open: function (farmId) {
        for (let item in this.block) {
          this.block[item] = null
        }
        this.block.farmId = farmId
        this.block.batchNo = 'b' + new Date().getTime()
        this.show = true
      },
      validate: function () {
        for (let item in this.block) {
          if (item != 'description') {
            if (this.block[item] == null) {
              this.$message.warning('说明可以为空，其余均不能为空！')
              return false
            }
          }
        }
        return true
      },
      onSubmit: function () {
        if (this.validate()) {
          //alert('创建中。。。')
          //this.loadShow = true
          this.$sys.ajax.post('/block/createBranchBlock.do', this.block, function (rt, _self) {
            if (rt.status == 1) {
              _self.loadShow = false
              _self.$emit("refresh")
              _self.show = false
            }
          }, this)
        }
      },
      close: function () {
        this.show = false
      }
    }
  }
</script>
<style>
  label {
    font-size: 12px;
    display: inline-block;
    width: 75px;
  }

  .input_small input {
    width: 150px;
  }

  .input_mini input {
    width: 120px;
  }
</style>
