<template>
  <div id="user_root">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item><i class="el-icon-menu"></i> 用户管理</el-breadcrumb-item>
        <el-breadcrumb-item>基本信息</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div id="user_search">
      <p>
        <label style="font-size: 12px">手机号码：</label>
        <el-input v-model.trim="search.phone" size="small" style="width: 180px;"></el-input>
        <el-button type="primary" icon="el-icon-search" size="small" @click="beginSearch">查询</el-button>
      </p>
    </div>
    <div id="user_table">
      <el-table :data="userData" style="width: 100%; font-size: 14px" max-height="580">
        <el-table-column prop="id" label="编号" width="80" align="center"></el-table-column>
        <el-table-column prop="userPhone" label="手机号码" width="120" align="center"></el-table-column>
        <el-table-column prop="password" label="登录密码" width="160" align="center"></el-table-column>
        <el-table-column prop="payPassword" label="支付密码" width="160" align="center"></el-table-column>
        <el-table-column prop="userName" label="真实姓名" width="120" align="center"></el-table-column>
        <el-table-column prop="gender" label="性别" width="90" align="center"></el-table-column>
        <el-table-column prop="account" label="余额" width="90" align="center"></el-table-column>
        <el-table-column label="用户头像" width="90" align="center">
          <template slot-scope="scope">
            <img  :src="$sys.baseUri + scope.row.touImgPath" alt="" style="width: 50px;height: 50px">
          </template>
        </el-table-column>
        <el-table-column prop="regTime" label="注册时间" width="140" align="center"></el-table-column>
        <el-table-column prop="loginTime" label="最新登录时间" width="140" align="center"></el-table-column>
        <el-table-column label="操作" fixed="right" width="100px" align="center">
          <template slot-scope="scope">
            <el-button type="text" size="mini" @click="deleteRow(scope.$index)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="block" style="width: 480px; margin: 0 auto; padding-top: 10px;">
        <el-pagination
          width="100%"
          @current-change="handleCurrentChange"
          :current-page.sync="currentPage"
          :page-size="pageSize"
          layout="total, prev, pager, next"
          :total="totalRow">
        </el-pagination>
      </div>
    </div>
  </div>
</template>
<script>
  var url_getUserList0 = "/user/findAll.do"
  var url_getUserList1 = "/user/findByPhone.do"
  var url_del = "/user/delete.do"
  var dataTable = []

  export default {
      name: 'user',
      data() {
          return {
              search: {
                  phone: ''
              },
              userData: [],
              currentPage: 0,
              totalRow: 0,
              pageSize: 5
          }
      },
    methods: {
          beginSearch(){
              //console.log("user beginSearch")
            if (this.search.phone == '') {
                  this.$sys.ajax.post(url_getUserList0, null, function (data, _self){
                    //console.log(JSON.stringify(data));
                    data.forEach(function (e) {
                        if (e.regTime != null) {
                          e.regTime = _self.$sys.dateTimeFormat(e.regTime, true)
                        }
                        if (e.loginTime != null) {
                          e.loginTime = _self.$sys.dateTimeFormat(e.loginTime, true)
                        }
                    })
                    dataTable = data
                    _self.totalRow = dataTable.length
                    _self.userData = dataTable.slice(0, _self.pageSize)
                  }, this)
            } else {
              this.$sys.ajax.post(url_getUserList1, this.search, function (data, _self) {
                //console.log(data);
                var e = data
                  if (e.regTime != null) {
                    e.regTime = _self.$sys.dateTimeFormat(e.regTime, true)
                  }
                  if (e.loginTime != null) {
                    e.loginTime = _self.$sys.dateTimeFormat(e.loginTime, true)
                  }
                  dataTable = [e]
                _self.totalRow = 1
                _self.userData = [e]
              }, this)
            }
          },
         handleCurrentChange() {
              var start = (this.currentPage - 1) * 5;
            this.userData = dataTable.slice(start, start + 5)
         },
        deleteRow(index) {
          var id = this.userData[index].id
          var _self = this;
          this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$sys.ajax.post(url_del, {id: id}, function(jsObj) {
              //console.log("rt 1 " + jsObj.status)
              if (jsObj.status == 1) {
                  _self.totalRow --
                  _self.userData.splice(index, 1)
                _self.$message.success(jsObj.msg)
              } else {
                _self.$message.info(jsObj.msg)
              }
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
        }
    }
  }
</script>
<style>
  #user_search {
    margin-top: 20px;
  }
</style>
