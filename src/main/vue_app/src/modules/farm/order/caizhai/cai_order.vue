<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item><i class="el-icon-menu"></i> 订单管理</el-breadcrumb-item>
        <el-breadcrumb-item>采摘订单</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div id="c_o_search">
      <p>
        <label>种植编号</label>
        <el-input size="small" v-model="search.plantId"></el-input>
        <label>采摘编号</label>
        <el-input size="small" v-model="search.caiId"></el-input>
        <label>状态</label>
        <el-select v-model="search.status" placeholder="请选择" size="small">
          <el-option
            v-for="item in options1"
            :key="item.key"
            :label="item.value"
            :value="item.key">
          </el-option>
        </el-select>
        <el-button type="primary" icon="el-icon-search" size="small" @click="beginSearch">查询</el-button>
      </p>
    </div>
    <div id="c_o_table">
      <p>
        <template><span class="option_head">* 查询结果 * </span></template>
      </p>
      <el-table :data="dataList" width="100%;" stripe style="font-size: 12px;" border>
        <el-table-column prop="id" label="采摘编号" width="80px" align="center" fixed="left"></el-table-column>
        <el-table-column prop="plantId" label="种植编号" width="80px" align="center"></el-table-column>
        <el-table-column prop="userId" label="用户编号" width="80px" align="center"></el-table-column>
        <el-table-column prop="greenTitle" label="蔬菜名称" width="100px" align="center"></el-table-column>
        <el-table-column prop="greenImg" label="蔬菜图片" width="120px" align="center">
          <template slot-scope="scope">
            <img :src="$sys.baseUri + scope.row.greenImg" alt="" style="width: 50px;height: 50px">
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="生成时间" width="120px" align="center"></el-table-column>
        <el-table-column prop="resTime" label="受理时间" width="120px" align="center"></el-table-column>
        <el-table-column prop="sendTime" label="发货时间" width="120px" align="center"></el-table-column>
        <el-table-column prop="finishTime" label="完成时间" width="120px" align="center"></el-table-column>
        <el-table-column prop="operator" label="处理人" width="100px" align="center"></el-table-column>
        <el-table-column prop="phone" label="手机号" width="100px" align="center"></el-table-column>
        <el-table-column prop="statusValue" label="订单状态" width="100px" align="center"></el-table-column>
        <el-table-column label="操作" width="150px" align="center">
          <template slot-scope="scope">
            <el-button type="text" size="mini" @click="process(scope.$index)">处理</el-button>
            <el-button type="text" size="mini" @click="send(scope.$index)">发货</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div style="width: 580px; margin: 0 auto; padding-top: 10px;">
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
    <div>
      <Edit ref="edit" v-on:refresh="beginSearch"></Edit>
    </div>
  </div>
</template>
<script>
  const options1 = [
    {key: -1, value: '全部'},
    {key: 0, value: '待受理'},
    {key: 1, value: '已受理'},
    {key: 2, value: '已发货'},
    {key: 3, value: '订单完成'}
  ]
  import Edit from './cai_edit.vue'
  export default {
    components: {Edit},
    name: 'plant',
    data() {
      return {
        options1: options1,
        search: {
          plantId: null,
          caiId: null,
          status: -1,
          farmManagerId: null,
          currentPage: null
        },
        totalRow: 0,
        dataList: []
      }
    },
    methods: {
      loadData: function () {
        this.$sys.ajax.post('/cai/web/loadCaiOrderList.do', this.search, function (rt, _self) {
          let list = rt.data.list
          list.forEach(function (e) {
            e.createTime = _self.$sys.dateTimeFormat(e.createTime, true)
            if (e.resTime) {
              e.resTime = _self.$sys.dateTimeFormat(e.resTime, true)
            }
            if (e.sendTime) {
              e.sendTime = _self.$sys.dateTimeFormat(e.sendTime, true)
            }
            if (e.finishTime) {
              e.finishTime = _self.$sys.dateTimeFormat(e.finishTime, true)
            }
          })
          _self.dataList = list
          _self.totalRow = rt.data.total
        }, this);
      },
      beginSearch: function () {
        this.search.currentPage = 1
        this.loadData()
      },
      handleCurrentChange: function () {
        this.loadData()
      },
      send: function (index) {
        let code = this.dataList[index].status
        if (code == 1) {
          this.$confirm('此操作将配送蔬菜, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            let param = {id: this.dataList[index].id, status: 2}
            this.$sys.ajax.post('/cai/web/updateStatus.do', param, function (rt, _self) {
              if (rt.data == 1) {
                _self.loadData()
                _self.$message({
                  type: 'success',
                  message: '操作成功!'
                });
              } else {
                _self.$message({
                  type: 'info',
                  message: '操作失败!'
                });
              }
            }, this)
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消'
            });
          });
        } else {
          this.$message.warning("该状态下不能进行此操作")
        }
      },
      process: function (index) {
        let code = this.dataList[index].status
        if (code == 0) {
          this.$refs.edit.open(this.dataList[index])
        } else {
          this.$message.warning("该状态下不能进行此操作")
        }
      }
    },
    created() {
      let fm = sessionStorage.getItem("fmManager");
      let fmObj = JSON.parse(fm);
      this.search.farmManagerId = fmObj.fmId
    }
  }
</script>
<style scope>
  #c_o_search .el-input {
    width: 180px;
    margin-right: 15px;
  }

  #c_o_search label {
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

  .option_head {
    font-size: 12px;
  }
</style>
