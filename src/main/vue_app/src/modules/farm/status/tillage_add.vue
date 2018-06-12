<template>
  <div style="padding: 0 30px">
    <h3>增加耕种记录</h3>
    <p>
      <label>种植编号：</label>
      <el-input v-model="rt.plantId" size="small" style="width:150px" :disabled="true"></el-input>
    </p>
    <p>
      <label>生长状态：</label>
      <el-select v-model="rt.status" placeholder="请选择" size="small" style="width: 150px;">
        <el-option
          v-for="item in options1"
          :key="item.key"
          :label="item.value"
          :value="item.value">
        </el-option>
      </el-select>
    </p>
    <p>
      <label>耕种操作：</label>
      <el-select v-model="rt.operate" placeholder="请选择" size="small" style="width: 150px;">
        <el-option
          v-for="item in options2"
          :key="item.key"
          :label="item.value"
          :value="item.value">
        </el-option>
      </el-select>
    </p>
    <p>
      <label>当前种植面貌：</label>
      <br>
      <br>
      <el-upload
        name="files"
        style="width: 286px"
        class="upload-demo"
        ref="upload"
        :on-success="successImg"
        action="http://localhost:8080/demo/plant/web/uploadImgList.do"
        :on-remove="handleRemove"
        :file-list="fileList"
        :limit="3"
        :auto-upload="false">
        <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
        <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，最多3张，且每张不超过3M</div>
      </el-upload>
    </p>
    <p>
      <label>最近种植短视频：</label>
      <br>
      <br>
      <el-upload
        name="files"
        style="width: 286px"
        class="upload-demo"
        ref="upload2"
        :on-success="successVideo"
        action="http://localhost:8080/demo/plant/web/uploadVideoList.do"
        :file-list="fileList2"
        :limit="1"
        :auto-upload="false">
        <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
        <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload2">上传到服务器</el-button>
        <div slot="tip" class="el-upload__tip">视频小于300M</div>
      </el-upload>
    </p>
    <p>
      <el-button type="primary" size="small" @click="yes">确定</el-button>
    </p>
  </div>
</template>
<script>
  const options1 = [
    {key: 1, value: "良好"},
    {key: 2, value: "部分成熟"},
    {key: 3, value: "全部成熟"},
    {key: 4, value: "完成"}
  ]
  const options2 = [
    {key: 1, value: "播种"},
    {key: 2, value: "除虫"},
    {key: 3, value: "灌溉"},
    {key: 4, value: "采摘"}
  ]
  export default {
    data() {
      return {
        options1: options1,
        options2: options2,
        fileList: [],
        fileList2: [],
        rt: {
          plantId: null,
          status: null,
          operate: null,
          imgList: new Array(),
          video: null
        }
      }
    },
    methods: {
      yes: function () {
        this.$sys.ajax.post('/plant/web/insertTillage.do', {json: JSON.stringify(this.rt)}, function (rt, _self) {
          if (rt.data == 1) {
            var msg = "更新成功！";
            if (confirm(msg) == true) {
              window.close()
            } else {
              window.close()
            }
          }
        }, this)
      },
      submitUpload() {
        this.$refs.upload.submit();
      },
      submitUpload2() {
        this.$refs.upload2.submit();
      },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      successImg(response, file, fileList) {
        this.rt.imgList[this.rt.imgList.length] = response.toString()
      },
      successVideo(response, file, fileList) {
        this.rt.video = response.toString()
      }
    },
    created: function () {
      this.rt.plantId = this.$route.query.plantId
    }
  }
</script>
<style>
  label {
    font-size: 14px;
  }
</style>
