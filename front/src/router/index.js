import Vue from 'vue'
import VueRouter from 'vue-router'
import ChsList from '@/views/chsList.vue'
import ChsInfo from '@/views/chsInfo.vue'
import ChsForm from '@/views/chsForm.vue'
import error from '@/views/error.vue'
import {getCookie} from '@/util/index.js'

Vue.use(VueRouter)

const routes = [
  {
    path: '/chslist',
    component: ChsList,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/chsinfo/:id',
    component: ChsInfo
  },
  {
    path: '/chsform',
    component: ChsForm
  },
  {
    path: '/error',
    component: error
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  if(to.matched.some(record => record.meta.requiresAuth)){
    const token = getCookie("token")
    if(!token) {
      next({
        path:'/error',
      })
    } else{
      next()
    }
  } else{
    next()
  }
})

export default router
