<template>
  <div id="user_root">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item><i class="el-icon-date"></i> 用户管理</el-breadcrumb-item>
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
      <el-table :data="userData" style="width: 100%; font-size: 14px" max-height="480">
        <el-table-column prop="id" label="编号" width="50"></el-table-column>
        <el-table-column prop="userPhone" label="手机号码" width="120"></el-table-column>
        <el-table-column prop="userName" label="真实姓名" width="120"></el-table-column>
        <el-table-column prop="gender" label="性别" width="50"></el-table-column>
        <el-table-column prop="regTime" label="注册时间" width="150"></el-table-column>
        <el-table-column prop="loginTime" label="最新登录时间" width="150"></el-table-column>
      </el-table>
      <div class="block" style="width: 480px; margin: 0 auto; padding-top: 10px;">
        <el-pagination
          width="100%"
          @current-change="handleCurrentChange"
          :current-page.sync="currentPage"
          :page-size="5"
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
              totalRow: 0
          }
      },
    methods: {
          beginSearch(){
              console.log("user beginSearch")
            if (this.search.phone == '') {
                  this.$http.post(url_getUserList0, null, function (data, _self){
                    console.log(data);
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
                    _self.userData = dataTable.slice(0, 3)
                  }, this)
            } else {
              this.$http.post(url_getUserList1, this.search, function (data, _self) {
                console.log(data);
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
         handleCurrentChange(){
              var start = (this.currentPage - 1) * 5;
            this.userData = dataTable.slice(start, start + 5)
           //console.log(start + " _ " + JSON.stringify(this.userData))
         }
    }
  }
</script>
