import router from './router'
import store from './store'
import { Message } from 'element-ui'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import { getToken } from '@/utils/auth' // get token from cookie
import getPageTitle from '@/utils/get-page-title'

NProgress.configure({ showSpinner: false }) // NProgress Configuration

const whiteList = ['/login'] // no redirect whitelist

router.beforeEach(async(to, from, next) => {
  // start progress bar
  NProgress.start()

  // set page title
  document.title = getPageTitle(to.meta.title)

  // determine whether the user has logged in
  const hasToken = getToken()

  if (hasToken) {
    if (to.path === '/login') {
      // if is logged in, redirect to the home page
      next({ path: '/' })
      NProgress.done()
      // console.log("gg")
    } else {
      const hasGetUserInfo = store.getters.name
      // console.log("gg")
      if (hasGetUserInfo) {
        next()
        // console.log("gg")
      } else {
        // console.log("gg")
        try {
          // get user info
          // await store.dispatch('user/getInfo')

          next()
        } catch (error) {
          // console.log("gg")
          // remove token and go to login page to re-login
          await store.dispatch('user/resetToken')
          Message.error(error || 'Has Error')
          next(`/login?redirect=${to.path}`)
          NProgress.done()
        }
      }
    }
  } else {
    next();

  
  }
})

router.afterEach(() => {
  // finish progress bar
  NProgress.done()
})
