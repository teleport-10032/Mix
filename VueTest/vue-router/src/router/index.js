/**
 * vue router的配置
 */
//导入vue和vu-router
import Vue from 'vue'
import VueRouter from 'vue-router';
//导入组件
import Content from "../components/Content";
import Main from "../components/Main";
import Test from "../components/Test";
import HelloWorld from "../components/HelloWorld";
//安装路由
Vue.use(VueRouter)

//配置导出路由
export default new VueRouter({
  routes: [
    //Content组件
    {
      //  路由的路径
      path: '/content',
      name: 'content',
      //  跳转的组件
      component: Content
    },
    //Main组件
    {
      //  路由的路径
      path: '/main',
      name: 'main',
      //  跳转的组件
      component: Main
    },
    //Test组件
    {
      //  路由的路径
      path: '/test',
      name: 'test',
      //  跳转的组件
      component: Test
    },
    //HelloWorld组件
    {
      //  路由的路径
      path: '/HelloWorld',
      name: 'HelloWorld',
      //  跳转的组件
      component: HelloWorld
    }
  ]
});
