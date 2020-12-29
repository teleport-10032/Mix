<template>
    <el-container>
        <el-aside width="200px" style="height: 500px">
            <el-row class="tac">
                <el-col>
                    <el-menu :default-active="this.$route.path" router
                            class="el-menu-vertical-demo">
                        <el-menu-item index="/userInfo">
                            <i class="el-icon-user"></i>
                            <span slot="title">个人信息设置</span>
                        </el-menu-item>
                        <el-menu-item index="/userAvatar">
                            <i class="el-icon-picture-outline-round"></i>
                            <span slot="title">头像设置</span>
                        </el-menu-item>
                        <el-menu-item index="/userAccount">
                            <i class="el-icon-cpu"></i>
                            <span slot="title">用户账号设置</span>
                        </el-menu-item>
                    </el-menu>
                </el-col>
            </el-row>
        </el-aside>
        <el-main>
                <router-view></router-view>
        </el-main>
    </el-container>
</template>

<script>
    export default {
        name: "userIndex",
        methods: {
        },
        created() {
            if(window.localStorage.getItem("token") === null)
            {
                this.$message.error("越权访问")
                return this.$router.push({ path: '/'});
            }
        }
    }
</script>

<style scoped>

</style>
