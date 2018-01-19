<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item><i class="el-icon-menu"></i> 商家管理</el-breadcrumb-item>
        <el-breadcrumb-item>账号管理</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div id="search">
      <p>
        <label>手机号码：</label>
        <el-input v-model="search.phone" size="small" class="input"></el-input>
        <label>真实姓名：</label>
        <el-input v-model="search.fmName" size="small" class="input"></el-input>
        <label>状态：</label>
        <el-select v-model="search.status" size="small" class="input">
          <el-option v-for="item in options_check_status" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select>
      </p>
      <p>
        <el-button type="primary" icon="el-icon-search" size="small" @click="beginSearch">搜索</el-button>
      </p>
    </div>
    <div id="tableData">
      <el-table :data="fmData" style="width: 100%; font-size: 14px" max-height="480">
        <el-table-column prop="fmId" label="编号" width="50"></el-table-column>
        <el-table-column prop="regTime" label="申请日期" width="120"></el-table-column>
        <el-table-column prop="fmName" label="真实姓名" width="80"></el-table-column>
        <el-table-column prop="phone" label="手机号码" width="110"></el-table-column>
        <el-table-column prop="province" label="省份" width="80"></el-table-column>
        <el-table-column prop="city" label="城市" width="80"></el-table-column>
        <el-table-column prop="detailedAddress" label="详细地址" width="120"></el-table-column>
        <el-table-column prop="farmType" label="农场类型" width="80"></el-table-column>
        <el-table-column prop="farmSize" label="农场大小" width="80"></el-table-column>
        <el-table-column prop="checkStatus" label="状态" width="80"></el-table-column>
        <el-table-column label="操作" fixed="right" width="170px" align="center">
          <template slot-scope="scope">
            <el-button type="text" size="mini" @click="sh(scope.$index, fmData)">审核</el-button>
            <el-button type="text" size="mini" @click="jy(scope.$index, fmData)">禁用</el-button>
            <el-button type="text" size="mini" @click="deleteRow(scope.$index, fmData)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="block" style="width: 480px; margin: 0 auto; padding-top: 10px;">
        <el-pagination
          width="100%"
          @current-change="handleCurrentChange"
          :current-page.sync="search.currentPage"
          :page-size="5"
          layout="total, prev, pager, next"
          :total="totalRow">
        </el-pagination>
      </div>
    </div>
    <el-dialog
      title="审核账户："
      :visible.sync="show1"
      width="30%">
      <el-radio v-model="shTag" label="1">同意</el-radio>
      <el-radio v-model="shTag" label="2">拒绝</el-radio>
      <span slot="footer" class="dialog-footer">
    <el-button @click="show1 = false">取 消</el-button>
    <el-button type="primary" @click="shOk">确 定</el-button>
  </span>
    </el-dialog>
    <Edit ref="exit" v-on:refresh="loadDate"></Edit>
  </div>

</template>

<script>

  const urlLoadDate = "/farmManager/loadData.do"
  const urlSh = "/farmManager/sh.do"
  const urlDel ="/farmManager/del.do"

  const status = [
    {value: 0, label: '待审核'},
    {value: 1, label: '正常'},
    {value: 2, label: '审核未通过'},
    {value: 3, label: '禁用中'},
    {value: 4, label: '全部'}
  ]

  function dateFormat(times) {
    var date = new Date(parseInt(times))
    var dateStr = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
    return dateStr;
  }
  import Edit from './seller_examine_jy.vue'
  export default {
      components: { Edit },
    data() {
      return {
        options_check_status: status,
        search: {
          phone: '',
          fmName: '',
          status: 0,
          currentPage: 1
        },
        indexRow: 0,
        shTag: '1',
        show1: false,
        fmData: [],
        totalRow: 0
      }
    },
    methods: {
        shOk() {
            var _self = this
          this.$sys.ajax.post(urlSh, {phone: this.fmData[this.indexRow].phone, status: this.shTag}, function(jsObj) {
            console.log("rt 1 " + jsObj.status)
            if (jsObj.status == 1) {
                _self.loadDate()
              _self.show1 = false
              _self.$message.success("操作成功")
            } else {
              _self.show1 = false
                _self.$message.info("操作失败")
            }
          })
        },
      sh: function (index, tableData) {
            this.indexRow = index
          var obj = tableData[index];
            console.log(obj.checkStatus)
        if (obj.checkStatus == '待审核') {
          this.shTag = '1'
          this.show1 = true;
        } else {
          this.$message.warning("此状态下不能进行该操作");
        }
          //alert(tableData[index].fmName)
      },
      deleteRow(index, tableData) {
            if (tableData[index].checkStatus == '审核未通过') {
              var _self = this
              this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                this.$sys.ajax.post(urlDel, {phone: tableData[index].phone}, function(jsObj) {
                  console.log("rt 1 " + jsObj.status)
                  if (jsObj.status == 1) {
                    _self.loadDate()
                    _self.$message.success("删除成功")
                  } else {
                    _self.$message.info("操作失败")
                  }
                })
              }).catch(() => {
                this.$message({
                  type: 'info',
                  message: '已取消删除'
                });
              });
            } else {
              this.$message.warning("此状态下不能进行该操作");
            }
      },
      jy(index, tableData) {
        var obj = tableData[index];
        if (obj.checkStatus != '禁用中' && obj.checkStatus != '审核通过') {
            this.$message.warning("此状态下不能进行该操作")
        } else {
            this.$refs.exit.open(obj.phone, obj.checkStatus)
        }
      },
      loadDate() {
            //console.log("loadDate() exec");
        this.$sys.ajax.post(urlLoadDate, this.search, function(jsObj, _self) {
          if (jsObj.list.length <= 0) {
            _self.fmData = []
            _self.$message("暂无数据")
          } else {
            jsObj.list.forEach(function (e) {
              e.checkStatus = status[e.checkStatus].label
              e.regTime = dateFormat(e.regTime)
            });
            _self.totalRow = jsObj.total
            _self.fmData = jsObj.list
          }
        }, this);
      },
      beginSearch: function () {
        //console.log(JSON.stringify(this.search))
        this.search.currentPage = 1;
        this.loadDate()
      },
      handleCurrentChange: function () {
        //console.log("currentPage = " + this.search.currentPage)
        this.loadDate()
      }
    }
  }
</script>
<style>
  #search {
    margin-top:20px;
  }
  .input {
    width: 150px;
    margin-right: 39px;
  }
  #search label{
    font-size: 12px;
    display:inline-block;
    width: 65px;
  }
  #search p {
    margin-bottom:20px;
  }
</style>
