// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import ElementUi from 'element-ui'
import Router from './router/router'
//import jquery from 'jquery'
import sys from '@/commons/common.js'
import ajax from '@/commons/cus_fn.js'
import 'element-ui/lib/theme-chalk/index.css'

Vue.use(ElementUi)
Vue.prototype.$http = ajax
Vue.prototype.$sys = sys

new Vue({
  template: '<App/>',
  components: { App },
  router: Router
}).$mount("#app")
