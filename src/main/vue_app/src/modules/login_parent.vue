<template>
  <div id="login_root">
    <div id="login" >
        <el-tabs v-model="activeName" @tab-click="handleClick" type="border-card">

          <el-tab-pane label="平台管理员" name="first">
            <LoginA ref="loginA"></LoginA>
          </el-tab-pane>

          <el-tab-pane label="商家管理员" name="second">
            <LoginB></LoginB>
          </el-tab-pane>

        </el-tabs>
    </div>
  </div>
</template>

<script>
  import LoginA from '@/modules/platform/login.vue'
  import LoginB from '@/modules/farm/login.vue'

  /**
   *  系统首页
   */
  export default {
    data() {
      return {
        activeName: 'second'
      }
    },
    methods: {
      handleClick(tab, event) {
        this.$refs.loginA.cancel(this.$refs.loginA.ruleFormStr);
      }
    },
    components: {
      LoginA, LoginB
    }
  }
</script>
<style>
  #login {
    width:480px;
    height:480px;
  }
</style>
