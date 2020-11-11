import Vue from 'vue'
import App from './App.vue'
import router from './router'
import { Form, FormItem, Input, Button, Select, Pagination, Upload, Icon } from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
// Vue.use(Layout)
Vue.use(Form)
Vue.use(FormItem)
Vue.use(Input)
Vue.use(Button)
Vue.use(Select)
Vue.use(Pagination)
Vue.use(Upload)
Vue.use(Icon)

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
