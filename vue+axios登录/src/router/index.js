//导入vue
import Vue from 'vue';
import VueRouter from 'vue-router';
//导入组件
import APP from "../App";
import Login from "../components/Login";
import home from "../components/Home";
//使用
Vue.use(VueRouter);
//导出
export default new VueRouter({
  routes: [
    //首页
    {
      path: '/login', component: Login
    },
    {
      path: '/', redirect: '/login'
    },
    {
      path: '/home', component: home
    }
  ]


})

