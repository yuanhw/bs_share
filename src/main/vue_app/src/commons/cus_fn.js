import $ from 'jquery'

const base = 'http://localhost:8080/demo'
const ajax = {
  post: function (url, parameter, callback_fn, fn_para) {
    console.log(" 1 " +url + ' ' + JSON.stringify(parameter))
    $.ajax({
      method: 'post',
      url: base + url,
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
}
export default ajax
