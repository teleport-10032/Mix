import Vue from 'vue'
import App from './App'
import VueRouter from "vue-router";
//扫描路由配置
import router from "./router"
//导入elementUI
import ElementUI from "element-ui"
import {Message} from "element-ui"
//导入element css
import 'element-ui/lib/theme-chalk/index.css'
// npm install --save axios
import axios from 'axios'
import VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'

axios.defaults.baseURL = 'http://localhost:8888';
// axios.defaults.baseURL = 'http://timemeetyou.com:8889/api/private/v1/';
//设置全局的
Vue.prototype.$http = axios
Vue.prototype.$axios = axios
//使用
Vue.use(VueRouter)
Vue.use(ElementUI)
Vue.use(VueQuillEditor);
Vue.config.productionTip = false
new Vue({
  el: '#app',
  router,
  render: h => h(App),//ElementUI规定这样使用
})

