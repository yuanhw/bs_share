import Vue from 'vue'

import VueRouter from 'vue-router'
Vue.use(VueRouter)

/* 后台系统登录页 */
const login = resolve =>require(['@/modules/login_parent.vue'], resolve)
const welcome = resolve => require(['@/modules/welcome.vue'], resolve)

/* 管理员后台首页 */
const pm_index = resolve =>require(['@/modules/platform/index.vue'], resolve)
//管理员信息
const pm_info = resolve =>require(['@/modules/platform/person/pmInfo.vue'], resolve)
//商家账号管理
const pm_seller_examine = resolve => require(['@/modules/platform/seller/seller_examine.vue'], resolve)
const pm_seller_farm = resolve => require(['@/modules/platform/seller/seller_farm.vue'], resolve)
//用户信息
const pm_user = resolve => require(['@/modules/platform/user/user.vue'], resolve)

/* 商家后台首页*/
const fm_index = resolve => require(['@/modules/farm/index.vue'], resolve)
const fm_reg = resolve => require(['@/modules/farm/reg.vue'], resolve)
const fm_info = resolve => require(['@/modules/farm/person/fmInfo.vue'], resolve)
//农场生成
const fm_create = resolve => require(['@/modules/farm/farm_manage/create.vue'], resolve)
//地块生成
const block_create = resolve => require(['@/modules/farm/farm_manage/block/block_create.vue'], resolve)
//地块详情
const block_detail = resolve => require(['@/modules/farm/farm_manage/block/block_detail.vue'], resolve)
//订单管理
const block_order = resolve => require(['@/modules/farm/order/block/order.vue'], resolve)
const simple_order = resolve => require(['@/modules/farm/order/block/simple_order.vue'], resolve)
const plant_order = resolve => require(['@/modules/farm/order/plant/plant_order.vue'], resolve)
const plant_status_show = resolve => require(['@/modules/farm/status/status_show.vue'], resolve)
const tillage_add = resolve => require(['@/modules/farm/status/tillage_add'], resolve)
const cai_order = resolve => require(['@/modules/farm/order/caizhai/cai_order'], resolve)

const router = new VueRouter({
  routes: [
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/login',
      component: login
    },
    {
      path: '/pm/index',
      component: pm_index,
      redirect: '/pm/pm_welcome',
      children:
        [{
            path: '/pm/pm_welcome',
            name: 'pm_welcome',
            component: welcome
          },{
          path: '/pm/person_pmInfo',
          name: 'pmInfo',
          component: pm_info
        },
        {
          path: '/pm/seller_seller_examine',
          name: 'seller_examine',
          component: pm_seller_examine
        },
        {
          path: '/pm/pm_user',
          name: 'pm_user',
          component: pm_user
        },
          {
            path: '/pm/seller_farm',
            component: pm_seller_farm
          }]
    },
    {
      path: '/fm/reg',
      component: fm_reg
    },
    {
      path: '/fm/',
      redirect: '/fm/index'
    },
    {
      path: '/fm/index',
      component: fm_index,
      redirect: '/fm/fm_welcome',
      children: [
        {
          path: '/fm/fm_welcome',
          component: welcome
        },
        {
          path: '/fm/person_fmInfo',
          component: fm_info
        },
        {
          path: '/fm/fm_create',
          component: fm_create
        },
        {
          path: '/fm/block_create',
          component: block_create
        },
        {
          path: '/fm/block_detail',
          component: block_detail
        },
        {
          path: '/fm/block_order',
          component: block_order
        },
        {
          path: '/fm/plant_order',
          component: plant_order
        },
        {
          path: '/fm/plant_status_show',
          component: plant_status_show
        },
        {
          path: '/fm/cai_order',
          component: cai_order
        }
      ]
    },
    {
      path: '/fm/simple_detail',
      component: simple_order
    },
    {
      path: '/fm/tillage_add',
      component: tillage_add
    }
  ],
  linkActiveClass: 'active'
})

/**
 *  路由拦截  未登录跳转到登录界面
 */
router.beforeEach((to, from, next) => {
  //console.log("to:" + to.path + " * from" + from.path)
  if (to.path === '/login' || to.path === '/fm/reg') {
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
