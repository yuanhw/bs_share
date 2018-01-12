<template>
    <el-container style="border: 1px solid #eee;">
      <el-header style="text-align: right; font-size: 12px" class="el-header">
        <span>{{fmName}}</span>
      </el-header>
      <el-container style="height: 540px;">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)" class="el-aside">
          <el-menu  unique-opened router>
            <el-submenu index="1">
              <template slot="title"><i class="el-icon-message"></i><span style="font-weight: bold">个人信息</span></template>
                <el-menu-item index="red_packet_create" class="subItem">基本信息</el-menu-item>
            </el-submenu>
            <el-submenu index="2">
              <template slot="title"><i class="el-icon-message"></i><span style="font-weight: bold">农场信息</span></template>
              <el-menu-item index="red_packet_create" class="subItem">农场生成</el-menu-item>
              <el-menu-item index="red_packet_create" class="subItem">地块生成</el-menu-item>
              <el-menu-item index="red_packet_create" class="subItem">地块详情</el-menu-item>
            </el-submenu>
            <el-submenu index="3">
              <template slot="title"><i class="el-icon-message"></i><span style="font-weight: bold">订单管理</span></template>
              <el-menu-item index="red_packet_create" class="subItem">地块订单</el-menu-item>
              <el-menu-item index="red_packet_create" class="subItem">种植订单</el-menu-item>
              <el-menu-item index="red_packet_create" class="subItem">配送订单</el-menu-item>
            </el-submenu>
            <el-submenu index="4">
              <template slot="title"><i class="el-icon-message"></i><span style="font-weight: bold">地块状态</span></template>
              <el-menu-item index="red_packet_create" class="subItem">状态查看</el-menu-item>
              <el-menu-item index="red_packet_create" class="subItem">更新状态</el-menu-item>
            </el-submenu>
            <el-submenu index="5">
              <template slot="title"><i class="el-icon-message"></i><span style="font-weight: bold">交易记录</span></template>
              <el-menu-item index="red_packet_create" class="subItem">交易详情</el-menu-item>
            </el-submenu>
            <el-submenu index="6">
              <template slot="title"><i class="el-icon-message"></i><span style="font-weight: bold">客服</span></template>
              <el-menu-item index="red_packet_create" class="subItem">聊天列表</el-menu-item>
              <el-menu-item index="red_packet_create" class="subItem">聊天详情</el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>
        <el-main>
          <transition mode="out-in"><router-view></router-view></transition>
        </el-main>
      </el-container>
    </el-container>
</template>

<script>
  export default {
    name: 'index',
    data() {
        return {
            fmName: ''
        }
    },
    created: function () {
      this.fmName = JSON.parse(sessionStorage.getItem("fmManager")).fmName
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
