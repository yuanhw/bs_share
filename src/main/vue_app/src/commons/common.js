/**
 *  全局常量和函数
 */

import $ from 'jquery'
import crypto from 'crypto'

const baseUri = 'http://127.0.0.1:8080/demo'

const sys = {

  /* 请求根路径 */
  baseUri: baseUri,

  /**
   *  ajax函数 post请求
   */
  ajax: {
    post: function (url, parameter, callback_fn, fn_para) {
      //console.log("ajax" + this.baseUri + '_' + url)
      $.ajax({
        method: 'post',
        url: baseUri + url,
        data: parameter,
        async:false,
        cache:false,
        success: function (data) {
          //console.log("ajax success text : " + data)
          console.log("ajax success exec")
          var rt = eval("("+data+")")
          callback_fn.call(this, rt, fn_para)
        }
      })
    }
  },

  /**
   *  时间日期函数
   * @param times
   * @param tag  是否显示时间
   * @returns {string}
   */
  dateTimeFormat: function(times, tag) {
    var date = new Date(times)
    var s2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    var hours = date.getHours();
    var minutes = date.getMinutes();
    var seconds = date.getSeconds();

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
    var rt = date.getFullYear() + '年' + month + '月' + strDate + '日'
    if (tag) {
      rt += "  " + hours + s2 + minutes + s2 +seconds
    }
    return rt
  },

  /**
   *  密码加密
   * @param str
   * @returns {*}
   */
  getMd5: function (str) {
    let hash = crypto.createHash('md5');
    hash.update(str);
    return hash.digest('hex');
  }
}
export default sys
