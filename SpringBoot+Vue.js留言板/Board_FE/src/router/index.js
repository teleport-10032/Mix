//导入vue
import Vue from 'vue';
import VueRouter from 'vue-router';
//导入组件
import index from "../components/index/index";
import notFound from "../components/notFound";
import announcementList from "../components/index/announcement/announcementList";
import announcement from "../components/index/announcement/announcement";
import problemList from "../components/index/problem/problemList";
import problem from "../components/index/problem/problem";
import contestList from "../components/index/contest/contestList";
import contest from "../components/index/contest/contest";
import rank from "../components/index/rank/rank";
import test from "../components/test";
import userIndex from "../components/index/user/userIndex";
import userInfo from "../components/index/user/userInfo";
import userAccount from "../components/index/user/userAccount";
import userAvatar from "../components/index/user/userAvatar";
import userCard from "../components/index/user/userCard";
import about from "../components/index/about/about";
import stateList from "../components/index/state/stateList";
import state from "../components/index/state/state";

import adminIndex from "../components/admin/adminIndex";
import systemInfo from "../components/admin/systemInfo";
import problemAdmin from "../components/admin/problemAdmin/problemAdmin";
import userAdmin from "../components/admin/userAdmin";
import contestAdmin from "../components/admin/contestAdmin";
import problemCreate from "../components/admin/problemAdmin/problemCreate";
import problemEdit from "../components/admin/problemAdmin/problemEdit";
import annAdmin from "../components/admin/annAdmin";

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
    {path:"/admin" , redirect: "/systemInfo"},
    {path:"/userIndex" , redirect: "/userInfo"},
    // {path:"/board",component: messageIndex,
    //   children: [
    //
    //   ]
    // },
    {path:"/messageList", component: messageList},
    {path:"/messageSubmit", component: messageSubmit},
    {path:"/test",component:test},
    {
      path: '/', component: index,
      children: [
        {path:"/announcement", component: announcement},
        {path:'/announcementList',component:announcementList},
        {path:'/problemList',component:problemList},
        {path:'/problem',component:problem},
        {path:'/contestList',component:contestList},
        {path:'/contest',component:contest},
        {path:'/rank',component:rank},
        {path:'/user',component:userIndex,
        children:[
          {path:"/userInfo", component: userInfo},
          {path:"/userAccount", component: userAccount},
          {path:"/userAvatar", component: userAvatar}
        ]
        },
        {path:"/userCard/:id/", component: userCard},
        {path:'/about',component:about},
        {path:'/stateList',component:stateList},
        {path:'/state',component:state},
        {path:'/userIndex',component:userIndex},
      ],
    },
    {
      path: '/admin', component: adminIndex,
      children: [
        {path:'/problemAdmin',component:problemAdmin},
        {path:'/annAdmin',component:annAdmin},
        {path:'/systemInfo',component:systemInfo},
        {path:'/userAdmin',component:userAdmin},
        {path:'/contestAdmin',component:contestAdmin},
        {path:'/problemCreate',component:problemCreate},
        {path:'/problemEdit',component:problemEdit},
      ],
    },
    {path:"*",component:notFound},
  ]
})

