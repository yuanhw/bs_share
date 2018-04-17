<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item><i class="el-icon-menu"></i> 地块状态</el-breadcrumb-item>
        <el-breadcrumb-item>状态查看</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div id="s_o_search">
      <p>
        <label>地块编号</label>
        <el-input size="small" v-model="search.blockId"></el-input>
        <label>种植编号</label>
        <el-input size="small" v-model="search.plantId"></el-input>
        <label>类型</label>
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
    <div id="s_o_table" style="width: 988px">
      <p>
        <template><span class="option_head">* 查询结果 * </span></template>
      </p>
      <el-table :data="dataList" width="100%;" stripe style="font-size: 12px;" border>
        <el-table-column prop="plantId" label="种植编号" width="80px" align="center" fixed="left"></el-table-column>
        <el-table-column prop="blockId" label="地块编号" width="80px" align="center"></el-table-column>
        <el-table-column prop="greenTitle" label="蔬菜名称" width="100px" align="center"></el-table-column>
        <el-table-column label="蔬菜图片" width="120px" align="center">
          <template slot-scope="scope">
            <img :src="$sys.baseUri + scope.row.greenImg" alt="" style="width: 50px;height: 50px">
          </template>
        </el-table-column>
        <el-table-column prop="greenStatus" label="最近生长状态" width="100px" align="center"></el-table-column>
        <el-table-column prop="greenOperate" label="最近种植操作" width="100px" align="center"></el-table-column>
        <el-table-column prop="lastOperator" label="最近操作人" width="100px" align="center"></el-table-column>
        <el-table-column label="最近地块面貌列表" width="150px" align="center">
          <template slot-scope="scope">
            <el-button type="text" size="mini" @click="process1(scope.$index)">查看</el-button>
          </template>
        </el-table-column>
        <el-table-column label="最近操作短视频" width="150px" align="center">
          <template slot-scope="scope">
            <el-button type="text" size="mini" @click="process2(scope.$index)">观看</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="lastTime" label="更新时间" width="120px" align="center"></el-table-column>
        <el-table-column label="增加耕种记录" width="150px" align="center">
          <template slot-scope="scope">
            <el-button type="text" size="mini" @click="process3(scope.$index)">更新</el-button>
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
  </div>
</template>
<script>
  const options1 = [
    {key: 0, value: '全部'},
    {key: 1, value: '待种植'},
    {key: 2, value: '种植中'}
  ]
  export default {
    name: 'statusShow',
    data() {
      return {
        options1: options1,
        search: {
          plantId: null,
          blockId: null,
          status: 0,
          farmManagerId: null,
          currentPage: null
        },
        totalRow: 0,
        dataList: []
      }
    },
    methods: {
      loadData: function () {
        this.$sys.ajax.post('/plant/web/plantStatus.do', this.search, function (rt, _self) {
          let list = rt.data.list
          list.forEach(function (e) {
            if (e.lastTime) {
              e.lastTime = _self.$sys.dateTimeFormat(e.lastTime, true)
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
      process2: function (index) {

      },

      process1: function (index) {
      },

      process3: function (index) {
        window.open("/#/fm/tillage_add?plantId=" + this.dataList[index].plantId);
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
  #s_o_search .el-input {
    width: 180px;
    margin-right: 15px;
  }

  #s_o_search label {
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
