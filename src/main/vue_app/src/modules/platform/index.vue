<template>
    <el-container style="border: 1px solid #eee;" >
      <el-header style="" class="el-header">
        <div>
          <span style="font-size: 24px;margin-left: 200px;margin-right: 760px;">共享农田App后台管理系统</span>
          <el-button type="text" style="font-weight: bold" @click="handleCommand">退出</el-button>
        </div>
      </el-header>
      <el-container style="height: 550px;">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)" class="el-aside">
          <el-menu  unique-opened router>
            <el-submenu index="1">
              <template slot="title"><i class="el-icon-info"></i><span style="font-weight: bold">个人信息</span></template>
                <el-menu-item index="person_pmInfo" class="subItem">基本信息</el-menu-item>
            </el-submenu>
            <el-submenu index="2">
              <template slot="title"><i class="el-icon-mobile-phone"></i><span style="font-weight: bold">用户管理</span></template>
              <el-menu-item index="pm_user" class="subItem">用户信息</el-menu-item>
            </el-submenu>
            <el-submenu index="3">
              <template slot="title"><i class="el-icon-news"></i><span style="font-weight: bold">商家管理</span></template>
              <el-menu-item index="seller_seller_examine" class="subItem">账号管理</el-menu-item>
            </el-submenu>
            <el-submenu index="4">
              <template slot="title"><i class="el-icon-tickets"></i><span style="font-weight: bold">订单信息</span></template>

            </el-submenu>
            <el-submenu index="5">
            <template slot="title"><i class="el-icon-plus"></i><span style="font-weight: bold">交易信息</span></template>
          </el-submenu>
            <el-submenu index="6">
              <template slot="title"><i class="el-icon-setting"></i><span style="font-weight: bold">App设置</span></template>
            </el-submenu>
          </el-menu>
        </el-aside>
        <el-main>
          <transition mode="out-in"><router-view></router-view></transition>
        </el-main>
      </el-container>
      <el-container style="height:50px">
        <p style="font-size: 12px; padding-left: 20px">
          操作员：<span style="color: red">{{pmName}}</span>&nbsp;&nbsp;
          系统时间：<span id="clock_0" style="color:red">{{time}}</span>
        </p>
      </el-container>
    </el-container>
</template>

<script>
  export default {
    name: 'index',
    data() {
        return {
            pmName: 'Test',
            time: null
        }
    },
    created: function () {
      //this.fmName = JSON.parse(sessionStorage.getItem("fmManager")).fmName
      var pmObjStr = sessionStorage.getItem("pmManager");
      var pmObj = JSON.parse(pmObjStr);
      console.log(pmObj.pmName)
      this.pmName = pmObj.pmName;

      //let clock = document.getElementById("clock")
     this.showTime()
      //this.$router.push('pm_welcome')
    },
    methods:{
      handleCommand() {
          sessionStorage.removeItem("pmManager")
          this.$router.push('/login');
      },
      showTime() {
          var _self = this;
        function realSysTime(){
          var date = new Date()
          var month = date.getMonth() + 1;
          var strDate = date.getDate();
          var hours = date.getHours();
          var minutes = date.getMinutes();
          var seconds = date.getSeconds();

          var s2 = ":"

          if (month >= 1 && month <= 9) {
            month = "0" + month;
          }
          if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
          }
          if (hours >= 0 && hours <=9) {
            hours = "0" + hours;
          }
          if (minutes >=0 && minutes <= 9) {
            minutes = "0" + hours;
          }
          if (seconds >=0 && seconds <= 9) {
            seconds = "0" + seconds;
          }
          let rt = date.getFullYear() + '年' + month + '月' + strDate + "日 " + hours + s2 + minutes + s2 +seconds
          _self.time=rt;
        }
        setInterval(realSysTime, 1000)
      }
    },
    computed: {
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }
  .el-aside {
    color: #333;
  }
  .subItem {

  }
</style>
