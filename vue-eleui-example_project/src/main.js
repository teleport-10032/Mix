import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/css/global.css'
Vue.config.productionTip = false

import axios from 'axios'
Vue.prototype.$http = axios
// 配置请求的跟路径
axios.defaults.baseURL = 'http://timemeetyou.com:8889/api/private/v1/'
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
