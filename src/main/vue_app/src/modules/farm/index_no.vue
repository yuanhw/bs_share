<template>
  <el-container style="border: 1px solid #eee;">
    <el-header style="text-align: right; font-size: 12px; background-color: rgb(55, 61, 65)" class="el-header">
      <div>
        <span style="font-size: 24px;margin-left: 200px;margin-right: 760px;color: white">共享农田商家管理平台</span>
        <el-button type="text" style="font-weight: bold; color: #dea726" @click="handleCommand">退出</el-button>
      </div>
    </el-header>
    <el-container style="height: 550px">
      <h1 style="margin: 50px">账号审核中...</h1>
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
    name: 'index_no',
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

        function realSysTime() {
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
          if (hours >= 0 && hours <= 9) {
            hours = "0" + hours;
          }
          if (minutes >= 0 && minutes <= 9) {
            minutes = "0" + hours;
          }
          if (seconds >= 0 && seconds <= 9) {
            seconds = "0" + seconds;
          }
          let rt = date.getFullYear() + '年' + month + '月' + strDate + "日 " + hours + s2 + minutes + s2 + seconds
          _self.time = rt;
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
