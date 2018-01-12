<template>
    <div class="header">
        <div class="logo">共享农田后台管理系统</div>
        <div class="user-info">
            <el-badge :value="12" class="el-icon-bell" style="margin-right: 25px;"></el-badge>
            <el-dropdown trigger="click" @command="handleCommand">
                <span class="el-dropdown-link">
                    <img class="user-logo" src="/static/img/img.jpg">
                    {{name}}
                </span>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command="loginout">退出</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </div>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                name: ''
            }
        },
      created: function () {
        var pmObjStr = sessionStorage.getItem("pmManager");
        var pmObj = JSON.parse(pmObjStr);
        console.log(pmObj.pmName)
        this.name = pmObj.pmName;
      },
        methods:{
            handleCommand(command) {
                if(command == 'loginout'){
                    sessionStorage.removeItem("pmManager")
                    this.$router.push('/login');
                }
            }
        }
    }
</script>
<style scoped>
    .header {
        position: relative;
        box-sizing: border-box;
        width: 100%;
        height: 70px;
        font-size: 22px;
        line-height: 70px;
        color: #fff;
    }
    .header .logo{
        float: left;
        width:250px;
        text-align: center;
    }
    .user-info {
        float: right;
        padding-right: 50px;
        font-size: 16px;
        color: #fff;
    }
    .user-info .el-dropdown-link{
        position: relative;
        display: inline-block;
        padding-left: 50px;
        color: #fff;
        cursor: pointer;
        vertical-align: middle;
    }
    .user-info .user-logo{
        position: absolute;
        left:0;
        top:15px;
        width:40px;
        height:40px;
        border-radius: 50%;
    }
    .el-dropdown-menu__item{
        text-align: center;
    }
  .item {
    margin-right: 40px;
  }
</style>
