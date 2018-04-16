<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item><i class="el-icon-menu"></i> 订单管理</el-breadcrumb-item>
        <el-breadcrumb-item>地块订单</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div id="b_o_search">
      <p>
        <label>订单编号</label>
        <el-input size="small" v-model="search.orderId"></el-input>
        <label>用户手机</label>
        <el-input size="small" v-model="search.userPhone"></el-input>
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
    <div id="b_o_table">
      <p>
        <template><span class="option_head">* 查询结果 * </span></template>
      </p>
      <el-table :data="dataList" width="100%;" stripe style="font-size: 12px;" border>
        <el-table-column prop="orderId" label="编号" width="120px" align="center" fixed="left"></el-table-column>
        <el-table-column prop="userPhone" label="用户手机" width="120px" align="center"></el-table-column>
        <el-table-column prop="userName" label="用户姓名" width="120px" align="center"></el-table-column>
        <el-table-column prop="validDate" label="生效日期" width="120px" align="center"></el-table-column>
        <el-table-column prop="noValidDate" label="失效日期" width="120px" align="center"></el-table-column>
        <el-table-column prop="spec" label="规格" width="80px" align="center"></el-table-column>
        <el-table-column prop="lease" label="用户租期" width="80px" align="center"></el-table-column>
        <el-table-column prop="number" label="数量" width="80px" align="center"></el-table-column>
        <el-table-column prop="amount" label="付款" width="80px" align="center"></el-table-column>
        <el-table-column prop="statusValue" label="状态" width="80px" align="center"></el-table-column>
        <el-table-column prop="grade" label="评分" width="80px" align="center"></el-table-column>
        <el-table-column prop="comment" label="评价" width="180px" align="center"></el-table-column>
        <el-table-column label="操作" width="180px" align="center">
          <template slot-scope="scope">
            <el-button type="text" size="mini" @click="detail(scope.$index)">详情</el-button>
            <el-button type="text" size="mini" @click="refund(scope.$index)">退款</el-button>
            <el-button type="text" size="mini" @click="del(scope.$index)">删除</el-button>
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
      <ReFund ref="refund" v-on:refresh="beginSearch"></ReFund>
    </div>
  </div>
</template>
<script>
  const options1 = [
    {key: -1, value: '全部'},
    {key: 0, value: '已完成'},
    {key: 1, value: '正常'},
    {key: -2, value: '待核销'},
    {key: 2, value: '待评价'},
    {key: 3, value: '退款中'},
    {key: 4, value: '已退款'},
    {key: 5, value: '用户已删除'}
  ]
  import ReFund from './refund.vue'
  export default {
    components: {ReFund},
    name: 'order',
    data() {
      return {
        options1: options1,
        search: {
          orderId: null,
          userPhone: null,
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
        this.$sys.ajax.post('/block/order/loadOrderWeb.do', this.search, function (rt, _self) {
          let list = rt.data.list
          list.forEach(function (e) {
            e.validDate = _self.$sys.dateTimeFormat(e.validDate)
            e.noValidDate = _self.$sys.dateTimeFormat(e.noValidDate)
          })
          _self.dataList = list
          _self.totalRow = rt.data.total
        }, this);
      },
      beginSearch: function () {
        //console.log(this.search)
        this.search.currentPage = 1
        this.loadData()
      },
      handleCurrentChange: function () {
        this.loadData()
      },
      refund: function (index) {
        let code = this.dataList[index].status
        if (code == 3) {
          let id = this.dataList[index].orderId
          this.$refs.refund.open(id)
        } else {
          this.$message.warning("该状态下不能进行此操作")
        }
      },
      del: function (index) {
        let code = this.dataList[index].status
        if (code == 5) {
          this.$sys.ajax.post('/block/order/delOrderWeb.do', {orderId: this.dataList[index].orderId}, function (rt, _self) {
            if (rt.data == 1) {
              _self.$message.warning("删除成功")
              _self.beginSearch()
            } else {
              _self.$message.warning("删除失败")
            }
          }, this)
        } else {
          this.$message.warning("该状态下不能进行此操作")
        }
      },
      detail: function (index) {
        //this.$router.push({path: "/fm/simple_detail", query: {orderId: this.dataList[index].orderId}})
        window.open("/#/fm/simple_detail?orderId=" + this.dataList[index].orderId);
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
  #b_o_search .el-input {
    width: 180px;
    margin-right: 15px;
  }

  #b_o_search label {
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
