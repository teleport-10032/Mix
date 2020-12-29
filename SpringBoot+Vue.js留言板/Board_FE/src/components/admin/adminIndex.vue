<template>
    <el-container class="home-container">
        <!-- 头部区域 -->
        <el-header>
            <div>
                <span>Admin:</span>
                <p>以&nbsp;{{username}}&nbsp;身份登录</p>
            </div>
            <el-button type="success" @click="logout">退出</el-button>
        </el-header>
        <!-- 页面主体区域 -->
        <el-container>
            <!-- 侧边栏 -->
            <el-aside :width="isCollapse ? '64px' : '200px'" :style="setHeight">
                <div class="toggle-button" @click="toggleCollapse">|||</div>
                <el-menu :default-active="this.$route.path"
                         background-color="#333744" text-color="#fff" active-text-color="#409EFF" unique-opened
                        :collapse="isCollapse" :collapse-transition="false" router >
                    <el-menu-item index="/systemInfo">
                        <i class="el-icon-menu"></i>
                        <span slot="title">系统信息</span>
                    </el-menu-item>
                    <el-menu-item index="/annAdmin">
                        <i class="el-icon-s-order"></i>
                        <span slot="title">公告管理</span>
                    </el-menu-item>
                    <el-menu-item index="/userAdmin">
                        <i class="el-icon-user-solid"></i>
                        <span slot="title">用户管理</span>
                    </el-menu-item>
                    <el-menu-item index="/problemAdmin">
                        <i class="el-icon-tickets"></i>
                        <span slot="title">题目管理</span>
                    </el-menu-item>
                    <el-menu-item index="/contestAdmin">
                        <i class="el-icon-trophy-1"></i>
                        <span slot="title">比赛管理</span>
                    </el-menu-item>
                </el-menu>
            </el-aside>
            <!-- 右侧内容主体 -->
            <el-main :style="setHeight">
                <!-- 路由占位符 -->
                <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
    import qs from 'qs'
    export default {
        name:"adminIndex",
        data() {
            return {
                setHeight:'height:' + document.documentElement.clientHeight + 'px',
                //当前登录的用户名:
                username:"",
                // 左侧菜单数据
                menulist: [],
                // 是否折叠
                isCollapse: false,
            }
        },
        created() {
            let result =  this.$axios({
                method: 'post',
                url: '/getUserName',
                headers: { 'content-type': 'application/x-www-form-urlencoded'},
                data: qs.stringify({
                    token: window.localStorage.getItem("token")
                })
            });
            result.then(res=>{
                this.username = res.data.username

            })
            this.adminJudge()
        },
        methods: {
            async adminJudge() {
                if(window.localStorage.getItem("token") != null)
                {
                    const { data: res } = await this.$http.get('tokenIsAdmin', {
                        params: {token:window.localStorage.getItem("token")}
                    })
                    if(res.result !== "1")
                    {
                        this.$message.warning("您无权访问该页面")
                        return this.$router.push('/')
                    }
                }
                else
                {
                    this.$message.warning("请先登录")
                    return this.$router.push('/')
                }
            },
            logout() {
                window.localStorage.clear()
                this.$router.push('/')
            },
            // 点击按钮，切换菜单的折叠与展开
            toggleCollapse() {
                this.isCollapse = !this.isCollapse
            }
        }
    }
</script>

<style lang="less" scoped>
    .home-container {
        height: 100%;
    }
    .el-header {
        background-color: #333744;
        display: flex;
        justify-content: space-between;
        padding-left: 0;
        align-items: center;
        color: #fff;
        font-size: 20px;
        > div {
            display: flex;
            align-items: center;
            span {
                margin-left: 15px;
            }
        }
    }

    .el-aside {
        background-color: #333744;
        .el-menu {
            border-right: none;
        }
    }

    .el-main {
        background-color: #eaedf1;
    }

    .iconfont {
        margin-right: 10px;
    }

    .toggle-button {
        background-color: #808080;
        font-size: 10px;
        line-height: 24px;
        color: #fff;
        text-align: center;
        letter-spacing: 0.2em;
        cursor: pointer;
    }
</style>
