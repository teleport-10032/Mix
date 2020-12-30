//导入vue
import Vue from 'vue';
import VueRouter from 'vue-router';
//导入组件

import messageIndex from "../components/board/messageIndex";
import messageList from "../components/board/messageList";
import messageSubmit from "../components/board/messageSubmit";

//使用
Vue.use(VueRouter);
//导出

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}
export default new VueRouter({
  mode:'history',history,
  routes: [
    {path:"/" , redirect: "/messageList"},
    {path:"/messageList", component: messageList},
    {path:"/messageSubmit", component: messageSubmit},
  ]
})

