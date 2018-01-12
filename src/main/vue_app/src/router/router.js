import Vue from 'vue'
import VueRouter from 'vue-router'

import Login from '@/components/view/login_parent'
import FarmReg from '@/components/view/farm/page/reg'

Vue.use(VueRouter)

const router = new VueRouter({

  routes: [
    {
      path: '/',
      redirect: '/login'
    },
    {path: '/login', component: Login},
    {path: '/pm/home',
      component: resolve => require(['../components/view/platform/common/Home.vue'], resolve),
      children:[
        {path: '/pm/pmInfo',
          component: resolve => require(['../components/view/platform/page/pmInfo.vue'], resolve)
        },
        {path: '/pm/seller_examine',
          component: resolve => require(['../components/view/platform/page/seller_examine.vue'], resolve)
        },
        {path: '/pm/seller_operate',
          component: resolve => require(['../components/view/platform/page/seller_operate.vue'], resolve)
        },
        {path: '/pm/user',
          component: resolve => require(['../components/view/platform/page/user.vue'], resolve)
        }
        ]
    },
    {path: '/fm/f_reg', component: FarmReg},
    {path: '/fm/index', component: resolve => require(['../components/view/farm/index.vue'], resolve) }
  ],
  linkActiveClass: 'active'
})
router.beforeEach((to, from, next) => {
  console.log("to:" + to.path + " * from" + from.path)
  if (to.path === '/login' || to.path === '/fm/f_reg') {
    next()
    return;
  }
  var fdStart = to.path.indexOf("/pm");
  if(fdStart === 0){
    var pmObjStr = sessionStorage.getItem("pmManager");
    if (pmObjStr && pmObjStr != null) {
      next()
      return;
    }
    next("/")
  }else {
    var fmObjStr = sessionStorage.getItem("fmManager");
    if (fmObjStr && fmObjStr != null) {
      next()
      return;
    }
    next("/")
  }
})
export default router
