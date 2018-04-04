<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item><i class="el-icon-menu"></i> 农场管理</el-breadcrumb-item>
        <el-breadcrumb-item>地块详情</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div id="block_detail_search">
      <p>
        <label>地块编号</label>
        <el-input v-model="search.id" size="small" class="input_small"></el-input>
        <label>批号</label>
        <el-input v-model="search.batchNo" size="small" class="input_small"></el-input>
      </p>
      <p>
        <label>用户编号</label>
        <el-input v-model="search.userId" size="small" class="input_small"></el-input>
        <label>耕种编号</label>
        <el-input v-model="search.tillId" size="small" class="input_small"></el-input>
        <label>类型</label>
        <el-select v-model="search.type" placeholder="请选择" size="small">
          <el-option
            v-for="item in options_0"
            :key="item.key"
            :label="item.value"
            :value="item.key">
          </el-option>
        </el-select>
      </p>
      <p>
        <label>状态</label>
        <el-select v-model="search.status" placeholder="请选择" size="small">
          <el-option
            v-for="item in options_1"
            :key="item.key"
            :label="item.value"
            :value="item.key">
          </el-option>
        </el-select>
        <el-button type="primary" icon="el-icon-search" size="small" @click="beginSearch">搜索</el-button>
      </p>
    </div>
    <div id="detail_table">
      <p>
        <template><span class="option_head">* 查询结果 * </span></template>
      </p>
      <el-table :data="detail_list0" width="100%;" stripe style="font-size: 12px;" border>
        <el-table-column prop="branchNo" label="批号" width="120px" align="center" fixed="left"></el-table-column>
        <el-table-column prop="id" label="地块编号" width="120px" align="center"></el-table-column>
        <el-table-column prop="userId" label="用户编号" width="120px" align="center"></el-table-column>
        <el-table-column prop="tillId" label="耕种编号" width="120px" align="center"></el-table-column>
        <el-table-column prop="type" label="种植类型" width="80px" align="center"></el-table-column>
        <el-table-column prop="updateMark" label="耕种更新" width="80px" align="center"></el-table-column>
        <el-table-column prop="lease" label="用户租期" width="80px" align="center"></el-table-column>
        <el-table-column prop="crateTime" label="创建时间" width="120px" align="center"></el-table-column>
        <el-table-column prop="updateTime" label="更新时间" width="120px" align="center"></el-table-column>
        <el-table-column prop="status" label="状态" width="80px" align="center"></el-table-column>
        <el-table-column>
          <template slot-scope="scope">
            <el-button type="text" size="mini" @click="other(scope.$index)">其它</el-button>
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
  </div>
</template>
<script>
  const options_0 = [
    {key: 0, value: '自种'},
    {key: 1, value: '代种'},
    {key: 2, value: '所有'}
  ]

  const options_1 = [
    {key: 0, value: "刚创建"},
    {key: 1, value: '已认购'},
    {key: 2, value: '已核销'},
    {key: 3, value: '所有'}
  ]

  var farmManagerId = null

  export default {
    name: 'detail',
    data() {
      return {
        options_1: options_1,
        options_0: options_0,
        search: {
          id: null,
          batchNo: null,
          userId: null,
          tillId: null,
          type: 2,
          status: 3,
          farmManagerId: null,
          currentPage: null
        },
        detail_list0: [],
        totalRow: null
      }
    },
    created: function () {
      let fm = sessionStorage.getItem("fmManager");
      let fmObj = JSON.parse(fm);
      farmManagerId = fmObj.fmId
      this.search.farmManagerId = fmObj.fmId
    },
    methods: {
      other: function (index) {
        alert(index)
      },
      handleCurrentChange: function () {
        //this.search.currentPage ++
        for (let item in this.search) {
          if (this.search[item] == null) {
            delete this.search[item]
          }
        }
        this.$sys.ajax.post('/block/loadBlockDetailList.do', this.search, function (rt, _self) {
          _self.detail_list0 = rt.data.list
          _self.totalRow = rt.data.total
          // console.log(rt)
        }, this)

      },
      beginSearch: function () {
        this.search.currentPage = 1
        for (let item in this.search) {
          if (this.search[item] == null) {
            delete this.search[item]
          }
        }
        this.$sys.ajax.post('/block/loadBlockDetailList.do', this.search, function (rt, _self) {
          _self.detail_list0 = rt.data.list
          _self.totalRow = rt.data.total
          //console.log(rt)
        }, this)
      }
    }
  }
</script>
<style scope>
  #block_detail_search .el-input {
    width: 180px;
    margin-right: 15px;
  }

  #block_detail_search label {
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
