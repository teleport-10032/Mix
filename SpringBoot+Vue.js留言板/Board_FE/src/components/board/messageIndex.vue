<template>
  <el-container>
    <!--    页头-->
    <el-header>
      <el-menu class="el-menu-demo" mode="horizontal" router>
        <el-menu-item index="/messageList">留言板</el-menu-item>
        <el-menu-item index="/messageSubmit" >提交留言</el-menu-item>
      </el-menu>
    </el-header>
    <!--    页头-->

    <!--    主体-->
    <el-main>
      <transition name="fade-transform" mode="out-in">
        <router-view></router-view>
      </transition>
    </el-main>
    <!--    主体-->
    <!--    页脚-->
    <el-footer>
      <div class="footer-font">
        <br>
        Copyright © 2020-现在 Teleport. All rights reserved.
        <br>
        Powered by
        <el-link type="primary" href="https://github.com/teleport-10032/FWOJ_FE"
                 style="font-size: 12px;font-weight:normal;" target="_blank" :underline="false">
          Message Board
        </el-link>
        Version: 20201229
      </div>
    </el-footer>
    <!--    页脚-->
  </el-container>
</template>

<script>
export default {
  name: "messageIndex"
}
</script>


<style scoped>
.footer-font {
  text-align: center;
  font-size: 12px;
  color: #606266;
}
.fade-transform-leave-active, .fade-transform-enter-active {
  transition: all .2s;
}

.fade-transform-enter {
  opacity: 0;
  transform: translateZ(-30px);
}

.fade-transform-leave-to {
  opacity: 0;
  transform: translateZ(30px);
}
</style>