<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item><i class="el-icon-menu"></i> 农场管理</el-breadcrumb-item>
        <el-breadcrumb-item>地块生成</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div id="block_search">
      <p>
        <label>批号：</label>
        <el-input v-model="search.batchNo" size="small"></el-input>
        <label>类型：</label>
        <el-select v-model="search.type" placeholder="请选择" size="small">
          <el-option
            v-for="item in options_1"
            :key="item.key"
            :label="item.value"
            :value="item.key">
          </el-option>
        </el-select>
      </p>
      <p>
        <span>app有效期开始日期：</span>
        <el-date-picker
          v-model="search.validityBegin"
          type="date"
          placeholder="选择日期" size="small">
        </el-date-picker>
        <span>app有效期结束日期：</span>
        <el-date-picker
          v-model="search.validityEnd"
          type="date"
          placeholder="选择日期" size="small">
        </el-date-picker>
      </p>
      <p>
        <el-button type="primary" icon="el-icon-search" size="small" @click="beginSearch">搜索</el-button>
        <el-button type="primary" icon="el-icon-circle-plus" size="small" @click="add">新建</el-button>
        <el-button size="small" @click="export2Excel">导出Excel</el-button>
      </p>
    </div>
    <div id="table">
      <p>
        <template><span class="option_head">* 查询结果 * </span></template>
      </p>
      <el-table :data="block_list" width="100%;" stripe style="font-size: 12px;" border>
        <el-table-column prop="batchNo" label="批号" width="80px" align="center" fixed="left"></el-table-column>
        <el-table-column prop="id" label="编号" width="80px" align="center"></el-table-column>
        <el-table-column prop="farmId" label="农场编号" width="80px" align="center"></el-table-column>
        <el-table-column prop="area" label="面积" width="80px" align="center"></el-table-column>
        <el-table-column prop="spec" label="规格" width="80px" align="center"></el-table-column>
        <el-table-column prop="unitPrice" label="租期单价" width="80px" align="center"></el-table-column>
        <el-table-column prop="proxy" label="代种单价" width="80px" align="center"></el-table-column>
        <el-table-column prop="number" label="数量" width="80px" align="center"></el-table-column>
        <el-table-column prop="type" label="类型" width="80px" align="center"></el-table-column>
        <el-table-column prop="description" label="说明" width="120px" align="center"></el-table-column>
        <el-table-column prop="leaseUnit" label="租期单位" width="120px" align="center"></el-table-column>
        <el-table-column prop="maxLease" label="最长租期" width="120px" align="center"></el-table-column>
        <el-table-column prop="validityBegin" label="有效期开始日期" width="120px" align="center"></el-table-column>
        <el-table-column prop="validityEnd" label="有效期结束日期" width="120px" align="center"></el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="120px" align="center"></el-table-column>
        <el-table-column prop="updateTime" label="更新时间" width="120px" align="center"></el-table-column>
        <el-table-column label="操作" fixed="right" width="170px" align="center">
          <template slot-scope="scope">
            <el-button type="text" size="mini" @click="delete_check(scope.$index)">删除</el-button>
            <el-button type="text" size="mini" @click="detail(scope.$index)">详情</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="block" style="width: 580px; margin: 0 auto; padding-top: 10px;">
      <el-pagination
        width="100%"
        @current-change="handleCurrentChange"
        :current-page.sync="search.currentPage"
        :page-size="5"
        :page-sizes="[5]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totalRow">
      </el-pagination>
    </div>
    <div id="block_add">
      <Add ref="dialog" v-on:refresh="addRefresh"></Add>
    </div>
  </div>
</template>
<script>
  const options_1 = [
    {key: 0, value: '仅自种'},
    {key: 1, value: '可代种'},
    {key: 2, value: '所有'}
  ]

  import Add from './block_add.vue'

  export default {
    components: {Add},
    data() {
      return {
        options_1: options_1,
        search: {
          batchNo: null,
          type: 2,
          validityBegin: null,
          validityEnd: null,
          fmManagerId: null,
          currentPage: null
        },
        block_list: [],
        totalRow: null
      }
    },
    methods: {
      validate: function () {
        return true
      },
      beginSearch: function () {
        if (this.validate()) {
          //alert('开始搜索')
          this.search.currentPage = 1
          for (let item in this.search) {
            if (this.search[item] == null) {
              delete this.search[item]
            }
          }
          //console.log(this.search)
          this.$sys.ajax.post('/block/loadBlockRuleList.do', this.search, function (rt, _self) {
            let data = rt.data.list
            data.forEach(function (e) {
              e.validityBegin = _self.$sys.dateTimeFormat(e.validityBegin, false)
              e.createTime = _self.$sys.dateTimeFormat(e.createTime, true)
              e.validityEnd = _self.$sys.dateTimeFormat(e.validityEnd, false)
              if (e.type == 0) {
                e.type = "仅自种"
              } else {
                e.type = "可代种"
              }
              if (e.updateTime) {
                e.updateTime = _self.$sys.dateTimeFormat(e.updateTime, false)
              }
            })
            //console.log(data)
            _self.block_list = data
            _self.totalRow = rt.data.total
            //console.log(rt)

          }, this)
        } else {
          alert("验证失败")
        }
      },
      add: function () {
        let url = '/farm/isHaveFarm.do'
        this.$sys.ajax.post(url, {fmId: this.search.fmManagerId}, function (rt, _self) {
          //console.log(rt)
          if (rt.data == 0) {
            _self.$message.warning('你还没有创建农场')
          } else {
            //alert('新增')
            _self.$refs.dialog.open(_self.search.fmManagerId)
          }
        }, this)
      },
      export2Excel: function () {
        alert('导出excel')
      },
      delete_check: function (index) {
        //alert(this.block_list[index].batchNo)
        this.$sys.ajax.post('/block/canDelBlackRule.do', {batchNo: this.block_list[index].batchNo}, function (rt, _self) {
          if (rt.data == 0) {
            var url = '/block/deleteBlack.do'
            _self.$sys.ajax.post(url, {batchNo: _self.block_list[index].batchNo}, function (rt, _self) {
              _self.addRefresh()
              _self.$message.info('已删除')
            }, _self)
          } else {
            _self.$message.warning('此信息不能删除，已有人认购')
          }
          //console.log(rt)
        }, this)
      },
      detail: function (index) {
        var batchNo = this.block_list[index].batchNo
        alert(batchNo)
      },
      handleCurrentChange: function () {
        //alert('下一页')
        this.$sys.ajax.post('/block/loadBlockRuleList.do', this.search, function (rt, _self) {
          let data = rt.data.list
          data.forEach(function (e) {
            e.validityBegin = _self.$sys.dateTimeFormat(e.validityBegin, false)
            e.createTime = _self.$sys.dateTimeFormat(e.createTime, true)
            e.validityEnd = _self.$sys.dateTimeFormat(e.validityEnd, false)
            if (e.updateTime) {
              e.updateTime = _self.$sys.dateTimeFormat(e.updateTime, false)
            }
            if (e.type == 0) {
              e.type = "仅自种"
            } else {
              e.type = "可代种"
            }
          })
          _self.block_list = data
          _self.totalRow = rt.data.total
          //console.log(rt)
        }, this)

      },
      addRefresh: function () {
        //alert('刷新')
        this.search.currentPage = 1
        this.$sys.ajax.post('/block/loadBlockRuleList.do', this.search, function (rt, _self) {
          let data = rt.data.list
          data.forEach(function (e) {
            e.validityBegin = _self.$sys.dateTimeFormat(e.validityBegin, false)
            e.createTime = _self.$sys.dateTimeFormat(e.createTime, true)
            e.validityEnd = _self.$sys.dateTimeFormat(e.validityEnd, false)
            if (e.updateTime) {
              e.updateTime = _self.$sys.dateTimeFormat(e.updateTime, false)
            }
            if (e.type == 0) {
              e.type = "仅自种"
            } else {
              e.type = "可代种"
            }
          })
          _self.block_list = data
          _self.totalRow = rt.data.total
          //console.log(rt)
        }, this)
      }
    },
    created: function () {
      let fm = sessionStorage.getItem("fmManager");
      let fmObj = JSON.parse(fm);
      this.search.fmManagerId = fmObj.fmId
    }
  }
</script>
<style scoped>
  #block_search .el-input {
    width: 180px;
    margin-right: 15px;
  }

  .input_small input {
    width: 120px;
  }

  .input_mini input {
    width: 80px;
  }

  #block_search label {
    font-size: 12px;
    display: inline-block;
    width: 75px;
  }

  #block_search {
    font-size: 12px;
  }

  .option_head {
    font-size: 12px;
  }
</style>
