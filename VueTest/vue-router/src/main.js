import Vue from 'vue'
import App from './App'
//自动扫描里面的路由配置
import router from "./router";

Vue.config.productionTip = false

new Vue({
  el: '#app',
  //配置路由
  router,
  components: {App},
  template: '<App/>'
})
