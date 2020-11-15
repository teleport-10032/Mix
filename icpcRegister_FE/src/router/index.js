import Vue from 'vue'
import VueRouter from 'vue-router'
import register from "../components/register";
import list from "../components/list";
import test from "../components/test";
Vue.use(VueRouter)


const router = new VueRouter({
  routes:[
    {path:"/" , redirect: "/register"},
    {path: '/register', component: register},
    {path: '/list', component: list},
    {path: '/test', component: test},
  ]
})


export default router
