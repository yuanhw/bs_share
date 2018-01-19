<template>
  <div>
    <el-dialog
      :title="showTitle"
      :visible.sync="show2"
      width="30%">
      <span icon="el-icon-warning">{{showText}}</span>
      <span slot="footer" class="dialog-footer">
    <el-button @click="show2 = false">取 消</el-button>
    <el-button type="primary" @click="jyOk()">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>
<script>
  const urlSh = "/farmManager/sh.do"
  export default {
      name: 'edit',
    data() {
          return {
            status: '禁用',
            show2: false,
            checked: false,
            phone: '',
          }
    },
    methods: {
      jyOk() {
          console.log("jyOk 确定")
        var status = 1;
        if (this.status != '禁用') {
              status = 3;
        }
        console.log("status d " + status)
          var _self = this
          this.$http.post(urlSh, {phone: this.phone, status: status}, function(jsObj) {
            console.log("rt 1 " + jsObj.status)
            if (jsObj.status == 1) {
              _self.show2 = false
              _self.$emit('refresh')
              _self.$message.success("操作成功")
            } else {
              _self.show1 = false
              _self.$message.info("操作失败")
            }
          })
        this.show2 = false
      },
      open: function(phone, status) {
          this.status = status
          this.show2 = true
        this.phone = phone
          console.log("phone = " + phone +" , status = " + status)
      }
    },
    computed: {
          showText: function () {
            return this.status == '禁用' ? '您即将解除禁用？': '你将要禁用该账户？'
          },
          showTitle: function () {
            return this.status == '禁用'? '解除禁用：' : '禁用：'
          }
    }
  }
</script>
