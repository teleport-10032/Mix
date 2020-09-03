import Vue from 'vue'
import App from './App'
import VueRouter from "vue-router";
//扫描路由配置
import router from "./router"
//导入elementUI
import ElementUI from "element-ui"
//导入element css
import 'element-ui/lib/theme-chalk/index.css'
//使用
Vue.use(VueRouter)
Vue.use(ElementUI)
Vue.config.productionTip = false
new Vue({
  el: '#app',
  router,
  render: h => h(App),//ElementUI规定这样使用
})

