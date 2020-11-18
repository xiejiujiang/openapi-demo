import Vue from 'vue'
import App from './App.vue'
import router from './router'
import '@/plugins/elem.js'
import 'element-ui/lib/theme-chalk/index.css'

Vue.config.productionTip = false

Vue.directive('title', {
  inserted: function (el, binding) {
    document.title = el.dataset.title
  }
})

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
