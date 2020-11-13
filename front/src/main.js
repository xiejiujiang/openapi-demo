import Vue from 'vue'
import App from './App.vue'
import router from './router'
import { Form, FormItem, Input, Button, Select, Option, Pagination, Upload, Icon, Message, MessageBox, Checkbox } from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

Vue.use(Form)
Vue.use(FormItem)
Vue.use(Input)
Vue.use(Button)
Vue.use(Select)
Vue.use(Option)
Vue.use(Pagination)
Vue.use(Upload)
Vue.use(Icon)
Vue.use(Checkbox)
Vue.prototype.$message = Message
Vue.prototype.$msgbox = MessageBox
Vue.prototype.$confirm = MessageBox.confirm

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
