<template>
  <el-container style="border: 1px solid #eee;">
    <el-header style="text-align: right; font-size: 12px" class="el-header">
      <div>
        <span style="font-size: 24px;margin-left: 200px;margin-right: 760px;">共享农田商家管理平台</span>
        <el-button type="text" style="font-weight: bold" @click="handleCommand">退出</el-button>
      </div>
    </el-header>
    <el-container style="height: 550px;">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)" class="el-aside">
        <el-menu  unique-opened router>
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-info"></i><span style="font-weight: bold">账号管理</span></template>
            <el-menu-item index="person_fmInfo" class="subItem">个人信息</el-menu-item>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-news"></i><span style="font-weight: bold">农场管理</span></template>
            <el-menu-item index="fm_create" class="subItem">农场生成</el-menu-item>
            <el-menu-item index="red_packet_create" class="subItem">地块生成</el-menu-item>
            <el-menu-item index="red_packet_create" class="subItem">地块详情</el-menu-item>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title"><i class="el-icon-tickets"></i><span style="font-weight: bold">订单管理</span></template>
            <el-menu-item index="red_packet_create" class="subItem">地块订单</el-menu-item>
            <el-menu-item index="red_packet_create" class="subItem">种植订单</el-menu-item>
            <el-menu-item index="red_packet_create" class="subItem">配送订单</el-menu-item>
          </el-submenu>
          <el-submenu index="4">
            <template slot="title"><i class="el-icon-more-outline"></i><span style="font-weight: bold">地块状态</span></template>
            <el-menu-item index="red_packet_create" class="subItem">状态查看</el-menu-item>
            <el-menu-item index="red_packet_create" class="subItem">更新状态</el-menu-item>
          </el-submenu>
          <el-submenu index="5">
            <template slot="title"><i class="el-icon-plus"></i><span style="font-weight: bold">交易记录</span></template>
            <el-menu-item index="red_packet_create" class="subItem">交易详情</el-menu-item>
          </el-submenu>
          <el-submenu index="6">
            <template slot="title"><i class="el-icon-loading"></i><span style="font-weight: bold">用户沟通</span></template>
            <el-menu-item index="red_packet_create" class="subItem">聊天列表</el-menu-item>
            <el-menu-item index="red_packet_create" class="subItem">聊天详情</el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <transition mode="out-in"><router-view></router-view></transition>
      </el-main>
    </el-container>

    <el-container style="height:50px">
      <p style="font-size: 12px; padding-left: 20px">
        商家名称：<span style="color: red">{{fmName}}</span>&nbsp;&nbsp;
        系统时间：<span id="clock_1" style="color:red">{{time}}</span>
      </p>
    </el-container>
  </el-container>
</template>

<script>
  export default {
    name: 'index',
    data() {
      return {
        fmName: '',
        time: null
      }
    },
    created: function () {
      this.fmName = JSON.parse(sessionStorage.getItem("fmManager")).fmName

      this.showTime()
      //this.$router.push("fm_welcome")
    },
    methods: {
        handleCommand() {
          sessionStorage.removeItem("fmManager")
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
