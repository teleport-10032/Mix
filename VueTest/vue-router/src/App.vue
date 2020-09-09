<template>
  <div id="app">
    <h1>vue-router</h1>
    <!--a标签 to就是href属性-->
    <router-link to="/main">首页</router-link>
    <router-link to="/content">内容页</router-link>
    <router-link to="/test">测试页</router-link>
    <router-link to="/HelloWorld">HelloWorld页</router-link>
    <router-view></router-view>
  </div>
</template>
<script>

    export default {
        name: 'App',
    }
</script>
<style scoped>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
</style>
