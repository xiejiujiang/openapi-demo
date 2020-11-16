import Vue from 'vue'
import VueRouter from 'vue-router'
import ChsList from '@/views/chsList.vue'
import ChsInfo from '@/views/chsInfo.vue'
import ChsForm from '@/views/chsForm.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/chslist',
    component: ChsList
  },
  {
    path: '/chsinfo',
    component: ChsInfo
  },
  {
    path: '/chsform',
    component: ChsForm
  }
]

const router = new VueRouter({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes
})

export default router
