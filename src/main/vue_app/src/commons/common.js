
const sys = {
  dateTimeFormat: function(times, tag) {
    var date = new Date(times)
    var s1 = "-";
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
    var rt = date.getFullYear() + s1 + month + s1 + strDate
    if (tag) {
      rt += "  " + hours + s2 + minutes + s2 +seconds
    }
    return rt
  },
  baseUri: 'http://192.168.43.43:8080/demo'
}
export default sys
