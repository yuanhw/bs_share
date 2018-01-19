import Vue from 'vue'
import App from './App'
import Router from './router/router'

/**
 *  引入elementUi组件
 */

import ElementUi from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

/**
 *  自定义全局函数
 */

import sys from '@/commons/common.js'
//import ajax from '@/commons/cus_fn.js'

Vue.use(ElementUi)

//Vue.prototype.$http = ajax
Vue.prototype.$sys = sys

new Vue({
  template: '<App/>',
  components: { App },
  router: Router
}).$mount("#app")
