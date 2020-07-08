import Vue from 'vue'
import Router from 'vue-router' 
import { getToken} from '@/utils/auth'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'
import { addDoctor } from '@/api/doctorManager'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/homePage',
    children: [{
      path: 'homePage',
      name: 'homePage',
      component: () => import('@/views/homePage/index'),
      meta: { title: '首页', icon: 'dashboard' }
    }]
  },
  {
    path: '/recordManager',
    component: Layout,
    redirect: '/recordManager',
    children: [{
      path: 'recordManager',
      name: 'recordManager',
      component: () => import('@/views/recordManager/index'),
      meta: { title: '病历管理', icon: 'dashboard' }
    }]
  },

  {
    path: '/selectappointment',
    component: Layout,
    redirect: '/selectappointment',
    children: [
      {
        path: 'selectappointment',
        name: 'selectappointment',
        component: () => import('@/views/selectappointment/index'),
        meta: { title: '查看预约', icon: 'table' }
      }
    ]
  },

  {
    path: '/appointmentManager',
    component: Layout,
    redirect: '/appointmentManager',
    children: [
      {
        path: 'appointmentManager',
        name: 'appointmentManager',
        component: () => import('@/views/appointmentManager/index'),
        meta: { title: '预约管理', icon: 'table' }
      }
    ]
  },

  {
    path: '/selectRecord',
    component: Layout,
    redirect: '/selectRecord',
    children: [
      {
        path: 'selectRecord',
        name: 'selectRecord',
        component: () => import('@/views/selectRecord/index'),
        meta: { title: '查看病历', icon: 'table' }
      }
    ]
  },
  {
    path: '/doctorManager',
    component: Layout,
    redirect: '/doctorManager',
    children: [
      {
        path: 'doctorManager',
        name: 'doctorManager',
        component: () => import('@/views/doctorManager/index'),
        meta: { title: '医生管理', icon: 'table' }
      }
    ]
  },


  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

export const Manager = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  
  {
    path: '/doctorManager',
    component: Layout,
    redirect: '/doctorManager',
    children: [
      {
        path: 'doctorManager',
        name: 'doctorManager',
        component: () => import('@/views/doctorManager/index'),
        meta: { title: '医生管理', icon: 'table' }
      }
    ]
  }
]
export const appointment = [
  {
    path: '/appointmentManager',
    component: Layout,
    redirect: '/appointmentManager',
    children: [
      {
        path: 'appointmentManager',
        name: 'appointmentManager',
        component: () => import('@/views/appointmentManager/index'),
        meta: { title: '预约管理', icon: 'table' }
      }
    ]
  },
  {
    path: '/selectRecord',
    component: Layout,
    redirect: '/selectRecord',
    children: [
      {
        path: 'selectRecord',
        name: 'selectRecord',
        component: () => import('@/views/selectRecord/index'),
        meta: { title: '查看病历', icon: 'table' }
      }
    ]
  }
]
export const doctor = [
  
  {
    path: '/recordManager',
    component: Layout,
    redirect: '/recordManager',
    children: [{
      path: 'recordManager',
      name: 'recordManager',
      component: () => import('@/views/recordManager/index'),
      meta: { title: '病历管理', icon: 'dashboard' }
    }]
  },
  {
    path: '/selectappointment',
    component: Layout,
    redirect: '/selectappointment',
    children: [
      {
        path: 'selectappointment',
        name: 'selectappointment',
        component: () => import('@/views/selectappointment/index'),
        meta: { title: '查看预约', icon: 'table' }
      }
    ]
  }
]
const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

router.beforeEach((to,from,next)=>{
  console.log(getToken()+"hhh")
  const dd = getToken()
  if (dd == 2){
    //创建路由
    router.options.routes = Manager
  }
  else if (dd == 3 ){
    router.options.routes = appointment
  }else if (dd == 1){
    router.options.routes = doctor
  }else{
    router.options.routes = constantRoutes
  }
  next();
})

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
