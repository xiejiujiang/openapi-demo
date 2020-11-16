import Vue from 'vue'
import {
    Form,
    FormItem, 
    Input,
    Button,
    Select,
    Option,
    Pagination,
    Upload,
    Icon,
    Message,
    MessageBox,
    Checkbox 
 } from 'element-ui'

Vue.use(Form)
.use(FormItem)
.use(Input)
.use(Button)
.use(Select)
.use(Option)
.use(Pagination)
.use(Upload)
.use(Icon)
.use(Checkbox)

Vue.prototype.$message = Message
Vue.prototype.$msgbox = MessageBox
Vue.prototype.$confirm = MessageBox.confirm

