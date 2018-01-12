<template>
    <div class="sidebar">
        <el-menu :default-active="onRoutes" class="el-menu-vertical-demo"  unique-opened router
                 background-color="#242f42"
                 text-color="#fff"
                 active-text-color="#ffd04b">
            <template v-for="item in items">
                <template v-if="item.subs">
                    <el-submenu :index="item.index">
                        <template slot="title"><i :class="item.icon"></i>{{ item.title }}</template>
                        <el-menu-item v-for="(subItem,i) in item.subs" :key="i" :index="subItem.index">{{ subItem.title }}
                        </el-menu-item>
                    </el-submenu>
                </template>
                <template v-else>
                    <el-menu-item :index="item.index">
                        <i :class="item.icon"></i>{{ item.title }}
                    </el-menu-item>
                </template>
            </template>
        </el-menu>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                items: [
                    {
                        icon: 'el-icon-setting',
                        index: 'pmInfo',
                        title: '个人信息'
                    },
                  {
                    icon: 'el-icon-mobile-phone',
                    index: '2',
                    title: '用户管理',
                    subs: [
                      {
                          index: 'user',
                        title: '用户信息'
                      }
                    ]
                  },
                    {
                        icon: 'el-icon-menu',
                        index: '3',
                        title: '商家管理',
                      subs: [
                        {
                            index: 'seller_examine',
                            title: '账号管理'
                        }
                      ]
                    },
                    {
                        icon: 'el-icon-tickets',
                        index: '4',
                        title: '订单信息',
                        subs: [
                            {
                                index: 'baseform',
                                title: '基本表单'
                            },
                            {
                                index: 'vueeditor',
                                title: '编辑器'
                            },
                            {
                                index: 'markdown',
                                title: 'markdown'
                            },
                            {
                                index: 'upload',
                                title: '文件上传'
                            }
                        ]
                    },
                  {
                    icon: 'el-icon-info',
                    index: '5',
                    title: '交易信息',
                    subs: [
                      {
                        index: 'baseform',
                        title: '基本表单'
                      },
                      {
                        index: 'vueeditor',
                        title: '编辑器'
                      },
                      {
                        index: 'markdown',
                        title: 'markdown'
                      },
                      {
                        index: 'upload',
                        title: '文件上传'
                      }
                    ]
                  },
                    {
                        icon: 'el-icon-message',
                        index: 'basecharts',
                        title: '用户投诉'
                    }
                ]
            }
        },
        computed:{
            onRoutes(){
                return this.$route.path.replace('/','');
            }
        }
    }
</script>

<style scoped>
    .sidebar{
        display: block;
        position: absolute;
        width: 250px;
        left: 0;
        top: 70px;
        bottom:0;
        background: #2E363F;
    }
    .sidebar  ul {
        height: 100%;
    }
</style>
