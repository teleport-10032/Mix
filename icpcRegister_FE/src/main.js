import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/css/global.css'
import ElementUI from "element-ui"
import {Message} from "element-ui"
Vue.config.productionTip = false
//设置全局的
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
Vue.prototype.$http = axios
Vue.prototype.$axios = axios
Vue.use(ElementUI)
// 配置请求的跟路径
axios.defaults.baseURL = 'http://47.107.227.192:8888/'
// axios.defaults.baseURL = 'http://localhost:8888/'
// axios请求拦截
axios.interceptors.request.use(config => {
  // 添加Authorization字段
  config.headers.Authorization = window.sessionStorage.getItem('token')
  // 在最后必须 return config
  return config
})
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
